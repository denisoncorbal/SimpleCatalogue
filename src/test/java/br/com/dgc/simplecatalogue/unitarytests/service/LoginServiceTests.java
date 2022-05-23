package br.com.dgc.simplecatalogue.unitarytests.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.security.JwtUserDetailsService;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.LoginService;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTests {

  final private SecurityConfig securityConfig = new SecurityConfig(
      "Bearer",
      "2e7a58d9-1d28-421a-a2c9-75dd0490c1b3-bf9e7d16-1851-4e25-b98d-3360680da07a",
      60000L);

  @Mock
  private JwtUserDetailsService jwtUserDetailsService;

  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private LoginService loginService = new LoginService(securityConfig, authenticationManager);

  @Test
  public void createSession(){

    Session session = loginService.createSessionFromUser(
        new User("admin", "admin", Collections.emptySet())
    );

    assertNotNull(session);
    assertFalse(session.getLogin().isEmpty());
    assertFalse(session.getToken().isEmpty());
  }

}
