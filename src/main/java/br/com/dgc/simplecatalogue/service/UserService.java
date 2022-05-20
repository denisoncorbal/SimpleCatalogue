package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Encapsulates the business logic for Entity User.
 *
 * @see User
 * @since 1.0
 */
@Service
public class UserService {

  private final PasswordEncoder encoder;

  private final UserRepository repository;

  public UserService(PasswordEncoder encoder, UserRepository repository) {
    this.encoder = encoder;
    this.repository = repository;
  }

  public User readByName(String name) {
    return repository.findByUsername(name);
  }

  public User createUser(User user){
    user.setPassword(encoder.encode(user.getPassword()));
    return repository.save(user);
  }
}
