package br.com.dgc.simplecatalogue.unitarytests.repository.impl;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Book;
import br.com.dgc.simplecatalogue.unitarytests.repository.WorkRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for abstraction of transactions with database for Entity Book.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see Book
 * @since 1.0
 */
@Repository
public interface BookRepositoryImpl extends WorkRepository<Book> {}
