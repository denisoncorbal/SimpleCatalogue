package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.Role;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JWTCreator;
import br.com.dgc.simplecatalogue.security.JWTObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.UserService;
import java.util.Date;
import java.util.stream.Collectors;
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

  final private UserService service;

  final private SecurityConfig securityConfig;

  LoginController(PasswordEncoder encoder, UserService service, SecurityConfig securityConfig) {
    this.encoder = encoder;
    this.service = service;
    this.securityConfig = securityConfig;
  }

  @PostMapping("")
  public ResponseEntity<Session> login(@Valid @RequestBody Login login) {
    User user = service.readByName(login.getUsername());

    if (user == null) {
      return ResponseEntity.badRequest().body(new Session());
    }

    boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());

    if (!passwordOk) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .header("WWW-Authenticate", "Basic realm=login")
          .body(new Session());
    }

    Session session = new Session();
    session.setLogin(user.getUsername());

    JWTObject jwtObject = new JWTObject();
    jwtObject.setSubject(String.valueOf(user.getId()));
    jwtObject.setIssuedAt(new Date((System.currentTimeMillis())));
    jwtObject.setExpiration(
        (new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION())));
    jwtObject.setRoles(
        user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList()));
    session.setToken(
        JWTCreator.create(securityConfig.getPREFIX(), securityConfig.getKEY(), jwtObject));

    return ResponseEntity.ok(session);
  }
}
