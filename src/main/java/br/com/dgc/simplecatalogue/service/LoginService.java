package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.Role;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JwtCreator;
import br.com.dgc.simplecatalogue.security.JwtObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Implements the Business logic of attempt of login on API.
 *
 * @see br.com.dgc.simplecatalogue.controller.LoginController
 * @see br.com.dgc.simplecatalogue.model.dto.Login
 * @since 1.0
 */
@Service
public class LoginService {

  private final SecurityConfig securityConfig;

  public LoginService(SecurityConfig securityConfig) {
    this.securityConfig = securityConfig;
  }

  /**
   * Take the User alredy validated and generate the valid token with his information.
   *
   * @param user Valid user existent in database.
   * @return Session with the valid token information.
   * @since 1.0
   * @see JwtObject
   * @see JwtCreator
   */
  public Session createSessionFromUser(User user) {
    Session session = new Session();
    session.setLogin(user.getUsername());
    JwtObject jwtObject = createJwtObjectFromUser(user);
    session.setToken(
        JwtCreator.create(securityConfig.prefix, securityConfig.getSecretKey(), jwtObject));
    return session;
  }

  private JwtObject createJwtObjectFromUser(User user) {
    return JwtObject.builder()
        .subject(String.valueOf(user.getId()))
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + securityConfig.expiration))
        .roles(user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList()))
        .build();
  }
}
