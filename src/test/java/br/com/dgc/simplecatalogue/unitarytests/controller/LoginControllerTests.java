package br.com.dgc.simplecatalogue.unitarytests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import br.com.dgc.simplecatalogue.controller.LoginController;
import br.com.dgc.simplecatalogue.model.dto.Login;
import br.com.dgc.simplecatalogue.model.dto.Session;
import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.security.JwtCreator;
import br.com.dgc.simplecatalogue.security.JwtObject;
import br.com.dgc.simplecatalogue.security.JwtUserDetailsService;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import br.com.dgc.simplecatalogue.service.LoginService;
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
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTests {

  @Mock private LoginService loginService;

  @Mock private JwtUserDetailsService jwtUserDetailsService;

  @InjectMocks private LoginController loginController;

  static MockedStatic<JwtCreator> jwtCreatorMockedStatic;

  @BeforeTestClass
  public static void init() {
    jwtCreatorMockedStatic = Mockito.mockStatic(JwtCreator.class);
    jwtCreatorMockedStatic
        .when(() -> JwtCreator.create(any(), any(), (JwtObject) any()))
        .thenReturn("token");
  }

  @Test
  public void givenUserNotInDatabase_whenPostToLogin_thenRespondUnauthorized() throws Exception {
    doThrow(new Exception()).when(loginService).authenticate(any(), any());

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    assertEquals(HttpStatus.UNAUTHORIZED, session.getStatusCode());

  }

  @Test
  public void givenUserNotInDatabase_whenPostToLogin_thenRespondVoidSession() throws Exception {
    doThrow(new Exception()).when(loginService).authenticate(any(), any());

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    assertEquals("", session.getBody().getLogin());
    assertEquals("", session.getBody().getToken());
  }

  @Test
  public void givenCorrectUser_whenPostToLogin_thenRespondSuccess() {
    given(loginService.createSessionFromUser(any())).willReturn(new Session());

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(loginService).should().createSessionFromUser(any());

    assertEquals(HttpStatus.OK, session.getStatusCode());
  }

  @Test
  public void givenCorrectUser_whenPostToLogin_thenRespondNotVoidSession() {
    given(loginService.createSessionFromUser(any())).willReturn(new Session("login", "token"));

    ResponseEntity<Session> session = loginController.login(new Login("admin", "admin"));

    then(loginService).should().createSessionFromUser(any());

    assertFalse(session.getBody().getLogin().isEmpty());
    assertFalse(session.getBody().getToken().isEmpty());
  }
}
