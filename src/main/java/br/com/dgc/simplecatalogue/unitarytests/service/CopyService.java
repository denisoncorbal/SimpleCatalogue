package br.com.dgc.simplecatalogue.unitarytests.service;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Copy;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Interface who provides basic business logic for Entity Copy.
 *
 * @see Copy
 * @since 1.0
 */
@Service
public interface CopyService {
  Copy create(Copy work);

  List<Copy> read();

  Optional<Copy> readById(Long id);

  Copy update(Copy work);

  void delete();
}
