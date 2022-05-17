package br.com.dgc.simplecatalogue.unitarytests.service;

import br.com.dgc.simplecatalogue.unitarytests.model.dto.Session;
import br.com.dgc.simplecatalogue.unitarytests.model.entity.Role;
import br.com.dgc.simplecatalogue.unitarytests.model.entity.User;
import br.com.dgc.simplecatalogue.unitarytests.security.JWTCreator;
import br.com.dgc.simplecatalogue.unitarytests.security.JWTObject;
import br.com.dgc.simplecatalogue.unitarytests.security.SecurityConfig;
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
        .issuedAt(new Date((System.currentTimeMillis())))
        .expiration((new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION())))
        .roles(user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList()))
        .build();
  }

}
