package br.com.dgc.simplecatalogue.unitarytests.repository;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for abstraction of transactions with database for all Entities who inherits from Work.
 *
 * @param <T> Entity that will be transacted to the database.
 * @see Work
 * @since 1.0
 */
@Repository
public interface WorkRepository<T extends Work> extends JpaRepository<T, Long> {}
