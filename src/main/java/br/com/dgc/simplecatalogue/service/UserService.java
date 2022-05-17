package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.User;
import br.com.dgc.simplecatalogue.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Encapsulates the business logic for Entity User.
 *
 * @see User
 * @since 1.0
 */
@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User readByName(String name) {
    return repository.findByUsername(name);
  }
}
