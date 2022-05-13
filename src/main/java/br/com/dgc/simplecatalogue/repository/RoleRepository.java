package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  boolean existsByRolename(String rolename);
}
