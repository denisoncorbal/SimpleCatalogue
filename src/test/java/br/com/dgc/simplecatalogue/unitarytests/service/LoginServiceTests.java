package br.com.dgc.simplecatalogue.unitarytests.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.LoginService;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class LoginServiceTests {

  final private SecurityConfig securityConfig = new SecurityConfig(
      "Bearer",
      "2e7a58d9-1d28-421a-a2c9-75dd0490c1b3-bf9e7d16-1851-4e25-b98d-3360680da07a",
      60000L, null);

  final private User user = new User(0L, "username", "password", "name", Collections.emptySet());

  final private LoginService loginService = new LoginService(securityConfig);

  @Test
  public void createSession(){
    Session session = loginService.createSessionFromUser(user);
    assertNotNull(session);
    assertFalse(session.getLogin().isEmpty());
    assertFalse(session.getToken().isEmpty());
  }

}
