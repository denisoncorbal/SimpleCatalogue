package br.com.dgc.simplecatalogue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JWTCreator;
import br.com.dgc.simplecatalogue.security.JWTObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.UserService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
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

  static MockedStatic<JWTCreator> jwtCreatorMockedStatic;

  @BeforeAll
  public static void init(){
    jwtCreatorMockedStatic = Mockito.mockStatic(JWTCreator.class);
    jwtCreatorMockedStatic.when(() -> JWTCreator.create(any(), any(), (JWTObject) any())).thenReturn("token");
  }

  @Test
  public void givenUserNotInDatabase_whenPostToLogin_thenRespondUnauthorized(){
    given(service.readByName(any())).willReturn(new User());

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(service).should().readByName("admin");

    assertEquals(HttpStatus.UNAUTHORIZED, session.getStatusCode());
  }

  @Test
  public void givenUserNotInDatabase_whenPostToLogin_thenRespondVoidSession(){
    given(service.readByName(any())).willReturn(new User());

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(service).should().readByName("admin");

    assertEquals("",session.getBody().getLogin());
    assertEquals("",session.getBody().getToken());
  }

  @Test
  public void givenCorrectUser_whenPostToLogin_thenRespondSuccess() {
    given(service.readByName((any()))).willReturn(new User(0L, "admin", "admin", "name", Collections.emptySet()));
    given(encoder.matches(any(), any())).willReturn(true);

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(service).should().readByName("admin");
    then(encoder).should().matches("admin", "admin");

    assertEquals(HttpStatus.OK, session.getStatusCode());
  }

  @Test
  public void givenCorrectUser_whenPostToLogin_thenRespondNotVoidSession() {
    given(service.readByName((any()))).willReturn(new User(0L, "admin", "admin", "name", Collections.emptySet()));
    given(encoder.matches(any(), any())).willReturn(true);

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(service).should().readByName("admin");
    then(encoder).should().matches("admin", "admin");

    assertFalse(session.getBody().getLogin().isEmpty());
    assertFalse(session.getBody().getToken().isEmpty());
  }
}
