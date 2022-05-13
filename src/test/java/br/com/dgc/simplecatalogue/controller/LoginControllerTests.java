package br.com.dgc.simplecatalogue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JWTCreator;
import br.com.dgc.simplecatalogue.security.JWTObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.UserService;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTests {

  @Mock
  private PasswordEncoder encoder;

  @Mock private SecurityConfig securityConfig;

  @Mock private UserService service;

  @InjectMocks
  private LoginController loginController;

  @Test
  public void givenUserNotInDatabase_whenPostToLogin_thenRespondUnauthorizedAndVoidSession(){
    when(service.readByName(any())).thenReturn(new User());
    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));
    assertEquals(HttpStatus.UNAUTHORIZED, session.getStatusCode());
    assertEquals("",session.getBody().getLogin());
    assertEquals("",session.getBody().getToken());
  }

  @Test
  public void givenCorrectUser_whenPostToLogin_thenRespondSuccess() {
    when(service.readByName(any())).thenReturn(new User(0L, "admin", "admin", "name", Collections.emptySet()));
    when(encoder.matches(any(), any())).thenReturn(true);
    MockedStatic<JWTCreator> jwtCreatorMockedStatic = Mockito.mockStatic(JWTCreator.class);
    jwtCreatorMockedStatic.when(() -> JWTCreator.create(any(), any(), (JWTObject) any())).thenReturn("token");
    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));
    assertEquals(HttpStatus.OK, session.getStatusCode());
    assertFalse(session.getBody().getLogin().isEmpty());
    assertFalse(session.getBody().getToken().isEmpty());
  }
}
