package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService<T extends Author> {

  // CREATE
  T create(T author);

  // READ
  List<T> read();

  Optional<T> readById(Long id);

  // UPDATE
  T update(T author);

  // DELETE
  void delete();

}
