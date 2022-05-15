package br.com.dgc.simplecatalogue.service.impl;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.repository.CopyRepository;
import br.com.dgc.simplecatalogue.service.CopyService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of Interface CopyService encapsulating business logic for Entity Copy.
 *
 * @see CopyService
 * @see Copy
 * @since 1.0
 */
@Service
public class CopyServiceImpl implements CopyService {

  private final CopyRepository repository;

  public CopyServiceImpl(CopyRepository repository) {
    this.repository = repository;
  }

  @Override
  public Copy create(Copy work) {
    return repository.save(work);
  }

  @Override
  public List<Copy> read() {
    return repository.findAll();
  }

  @Override
  public Optional<Copy> readById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Copy update(Copy work) {
    return repository.save(work);
  }

  @Override
  public void delete() {
    repository.deleteAll();
  }
}
