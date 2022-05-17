package br.com.dgc.simplecatalogue.unitarytests.service;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.User;
import br.com.dgc.simplecatalogue.unitarytests.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  final private UserRepository repository;
  public UserService(UserRepository repository){
    this.repository = repository;
  }

  public User readByName(String name){
    return repository.findByUsername(name);
  }
}
