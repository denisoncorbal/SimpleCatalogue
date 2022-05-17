package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.Role;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JWTCreator;
import br.com.dgc.simplecatalogue.security.JWTObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  final private SecurityConfig securityConfig;

  public LoginService(SecurityConfig securityConfig){
    this.securityConfig = securityConfig;
  }

  public Session createSessionFromUser(User user){
    Session session = new Session();
    session.setLogin(user.getUsername());
    JWTObject jwtObject = createJWTObjectFromUser(user);
    session.setToken(
        JWTCreator.create(securityConfig.getPREFIX(), securityConfig.getSECRET_KEY(), jwtObject));
    return session;
  }

  private JWTObject createJWTObjectFromUser(User user){
    return JWTObject.builder()
        .subject(String.valueOf(user.getId()))
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION()))
        .roles(user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList()))
        .build();
  }

}
