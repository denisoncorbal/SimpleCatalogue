package br.com.dgc.simplecatalogue.unitarytests.service.impl;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Book;
import br.com.dgc.simplecatalogue.unitarytests.repository.impl.BookRepositoryImpl;
import br.com.dgc.simplecatalogue.unitarytests.service.WorkService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of Interface WorkService encapsulating business logic for Entity Book.
 *
 * @see WorkService
 * @see Book
 * @since 1.0
 */
@Service
public class BookServiceImpl implements WorkService<Book> {

  private final BookRepositoryImpl repository;

  public BookServiceImpl(BookRepositoryImpl repository) {
    this.repository = repository;
  }

  @Override
  public Book create(Book book) {
    return repository.save(book);
  }

  @Override
  public List<Book> read() {
    return repository.findAll();
  }

  @Override
  public Optional<Book> readById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Book update(Book book) {
    return repository.save(book);
  }

  @Override
  public void delete() {
    repository.deleteAll();
  }
}
