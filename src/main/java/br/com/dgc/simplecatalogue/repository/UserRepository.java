package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = (:username)")
  User findByUsername(@Param("username") String username);

  boolean existsByUsername(String username);
}
