package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.entity.Author;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorController<T extends Author> {
  String path = "${api.base-path}" + "/authors";

  // Create
  ResponseEntity<T> create(@Valid @RequestBody T author);

  // Read
  ResponseEntity<List<T>> read();

  ResponseEntity<T> readById(Long id);

  // Update
  ResponseEntity<T> update(@Valid @PathVariable Long id, @Valid @RequestBody T author);

  // Delete
  ResponseEntity<T> delete();
}
