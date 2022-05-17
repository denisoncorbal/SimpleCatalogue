package br.com.dgc.simplecatalogue.unitarytests.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.repository.UserRepository;
import br.com.dgc.simplecatalogue.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void givenName_whenUserExistsInDatabase_thenReturnUser(){
    given(userRepository.findByUsername(any())).willReturn(new User());

    User user = userService.readByName("name");

    then(userRepository).should().findByUsername(any());
    assertNotNull(user);
  }

}
