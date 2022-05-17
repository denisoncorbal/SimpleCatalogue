package br.com.dgc.simplecatalogue.unitarytests.controller;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Work;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface for all controllers who handle HttpRequest for Entities who inherit from Work.
 *
 * @param <T> Entity who the controller implementation will handle HttpRequests for.
 * @see Work
 * @since 1.0
 */
public interface WorkController<T extends Work> {

  String path = "${spring.data.rest.base-path}" + "/works";

  // Create
  ResponseEntity<T> create(@Valid @RequestBody T work);

  // Read
  ResponseEntity<List<T>> read();

  ResponseEntity<T> readById(Long id);

  // Update
  ResponseEntity<T> update(@Valid @PathVariable Long id, @Valid @RequestBody T work);

  // Delete
  ResponseEntity<T> delete();
}
