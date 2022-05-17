package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for abstraction of transactions with database for Entity Copy.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see Copy
 * @since 1.0
 */
@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
}
