package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.service.LoginService;
import br.com.dgc.simplecatalogue.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller who captures HttpRequests on base-path/login. Responsible for attempt of login in
 * API.
 *
 * @see Login
 * @see Session
 * @since 1.0
 */
@RestController
@RequestMapping("${api.base-path}/login")
public class LoginController {
  private final PasswordEncoder encoder;

  private final UserService userService;

  private final LoginService loginService;

  LoginController(PasswordEncoder encoder, UserService userService, LoginService loginService) {
    this.encoder = encoder;
    this.userService = userService;
    this.loginService = loginService;
  }

  /**
   * Responsible to handle the attempt of login.
   *
   * @param login User information to attempt of login on API.
   * @return Return a ResponseEntity with Session containing token and status Success if successful
   *     login and void token and Unauthorized status if unsuccessful.
   * @since 1.0
   * @see Login
   * @see Session
   * @see ResponseEntity
   */
  @PostMapping("")
  public ResponseEntity<Session> login(@Valid @RequestBody Login login) {
    User user = userService.readByName(login.getUsername());

    if (user == null) {
      return ResponseEntity.badRequest().body(new Session());
    }

    boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());

    if (!passwordOk) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .header("WWW-Authenticate", "Basic realm=login")
          .body(new Session());
    }

    return ResponseEntity.ok(loginService.createSessionFromUser(user));
  }
}
