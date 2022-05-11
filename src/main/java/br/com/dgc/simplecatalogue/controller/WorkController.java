package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.model.entity.Work;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
