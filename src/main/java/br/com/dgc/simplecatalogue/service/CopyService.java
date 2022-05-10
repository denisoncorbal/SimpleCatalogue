package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CopyService {
    Copy create(Copy work);

    List<Copy> read();

    Copy readById(Long id);

    Copy update(Copy work);

    void delete();
}
