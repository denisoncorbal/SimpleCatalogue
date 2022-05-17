package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for abstraction of transactions with database for Role.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see Role
 * @since 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  boolean existsByRolename(String rolename);
}
