package br.com.dgc.simplecatalogue.service.impl;

import br.com.dgc.simplecatalogue.model.entity.BookAuthor;
import br.com.dgc.simplecatalogue.repository.impl.BookAuthorRepositoryImpl;
import br.com.dgc.simplecatalogue.service.AuthorService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorServiceImpl implements AuthorService<BookAuthor> {

  private final BookAuthorRepositoryImpl repository;

  public BookAuthorServiceImpl(BookAuthorRepositoryImpl repository){
    this.repository = repository;
  }


  @Override
  public BookAuthor create(BookAuthor author) {
    return repository.save(author);
  }

  @Override
  public List<BookAuthor> read() {
    return repository.findAll();
  }

  @Override
  public Optional<BookAuthor> readById(Long id) {
    return repository.findById(id);
  }

  @Override
  public BookAuthor update(BookAuthor author) {
    return repository.save(author);
  }

  @Override
  public void delete() {
    repository.deleteAll();
  }
}
