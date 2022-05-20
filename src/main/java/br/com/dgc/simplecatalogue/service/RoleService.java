package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.Role;
import br.com.dgc.simplecatalogue.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private final RoleRepository repository;

  public RoleService(RoleRepository repository){
    this.repository = repository;
  }

  boolean existsByRolename(String rolename){
    return repository.existsByRolename(rolename);
  }

  public Role createRole(Role role){
    return repository.save(role);
  }

}
