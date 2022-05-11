package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import java.util.List;
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

  Copy readById(Long id);

  Copy update(Copy work);

  void delete();
}
