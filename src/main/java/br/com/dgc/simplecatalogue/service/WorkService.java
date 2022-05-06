package br.com.dgc.simplecatalogue.service;

import br.com.dgc.simplecatalogue.model.entity.Work;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WorkService<T extends Work> {
    // CREATE
    T create(T work);
    // READ
    List<T> read();

    T readById(Long id);
    // UPDATE
    T update(T work);
    // DELETE
    void delete();
}
