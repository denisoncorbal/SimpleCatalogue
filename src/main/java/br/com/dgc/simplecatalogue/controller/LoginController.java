package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JwtUserDetailsService;
import br.com.dgc.simplecatalogue.service.LoginService;
import br.com.dgc.simplecatalogue.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

  private final JwtUserDetailsService jwtUserDetailsService;

  private final LoginService loginService;

  private final AuthenticationManager authenticationManager;

  LoginController(PasswordEncoder encoder, JwtUserDetailsService jwtUserDetailsService, LoginService loginService, AuthenticationManager authenticationManager) {
    this.encoder = encoder;
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.loginService = loginService;
    this.authenticationManager = authenticationManager;
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
  public ResponseEntity<Session> login(@Valid @RequestBody Login login) throws Exception{
    authenticate(login.getUsername(), login.getPassword());

    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(login.getUsername());

    return ResponseEntity.ok(loginService.createSessionFromUser(userDetails));
  }

  private void authenticate(String username, String password) throws Exception{
    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
    catch (DisabledException e){
      throw new Exception("USER_DISABLED", e);
    }
    catch (BadCredentialsException e){
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
