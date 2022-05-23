package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.security.JwtUserDetailsService;
import br.com.dgc.simplecatalogue.service.LoginService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
  private final JwtUserDetailsService jwtUserDetailsService;

  private final LoginService loginService;

  LoginController(JwtUserDetailsService jwtUserDetailsService, LoginService loginService) {
    this.jwtUserDetailsService = jwtUserDetailsService;
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
  public ResponseEntity<Session> login(@Valid @RequestBody Login login){
    try{
      loginService.authenticate(login.getUsername(), login.getPassword());
    }
    catch (Exception e){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Session());
    }

    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(login.getUsername());

    return ResponseEntity.ok(loginService.createSessionFromUser(userDetails));
  }


}
