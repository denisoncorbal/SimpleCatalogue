package br.com.dgc.simplecatalogue.unitarytests.service;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Work;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Interface who provides basic contract for class who will handle business logic for Entities who
 * inherit form Work.
 *
 * @param <T> Entity for business logic handle.
 * @see Work
 * @since 1.0
 */
@Service
public interface WorkService<T extends Work> {
  // CREATE
  T create(T work);

  // READ
  List<T> read();

  Optional<T> readById(Long id);

  // UPDATE
  T update(T work);

  // DELETE
  void delete();
}
