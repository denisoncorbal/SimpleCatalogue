package br.com.dgc.simplecatalogue.repository.impl;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.repository.WorkRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryImpl extends WorkRepository<Book> {
}
