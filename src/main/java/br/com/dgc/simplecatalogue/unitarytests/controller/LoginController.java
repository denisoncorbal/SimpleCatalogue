package br.com.dgc.simplecatalogue.unitarytests.controller;

import br.com.dgc.simplecatalogue.unitarytests.model.dto.Login;
import br.com.dgc.simplecatalogue.unitarytests.model.dto.Session;
import br.com.dgc.simplecatalogue.unitarytests.model.entity.User;
import br.com.dgc.simplecatalogue.unitarytests.service.LoginService;
import br.com.dgc.simplecatalogue.unitarytests.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.data.rest.base-path}/login")
public class LoginController {
  final private PasswordEncoder encoder;

  final private UserService userService;

  final private LoginService loginService;

  LoginController(PasswordEncoder encoder, UserService userService, LoginService loginService) {
    this.encoder = encoder;
    this.userService = userService;
    this.loginService = loginService;
  }

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
