package br.com.dgc.simplecatalogue.service.impl;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.repository.CopyRepository;
import br.com.dgc.simplecatalogue.service.CopyService;
import br.com.dgc.simplecatalogue.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CopyServiceImpl implements CopyService {

    private final CopyRepository repository;

    public CopyServiceImpl(CopyRepository repository){
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
    public Copy readById(Long id) {
        return repository.findById(id).orElse(new Copy());
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
