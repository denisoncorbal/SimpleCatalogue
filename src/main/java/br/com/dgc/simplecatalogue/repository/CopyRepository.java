package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
}
