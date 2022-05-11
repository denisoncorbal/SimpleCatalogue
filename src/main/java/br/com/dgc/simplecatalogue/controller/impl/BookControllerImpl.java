package br.com.dgc.simplecatalogue.controller.impl;

import br.com.dgc.simplecatalogue.controller.WorkController;
import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WorkController.path + "/books")
public class BookControllerImpl implements WorkController<Book> {
    private final BookServiceImpl service;

    public BookControllerImpl(BookServiceImpl service){
        this.service = service;
    }

    // Create
    @Override
    @PostMapping("")
    public ResponseEntity<Book> create(@Valid @RequestBody Book book){
        return ResponseEntity.ok(service.create(book));
    }
    // Read
    @Override
    @GetMapping("")
    public ResponseEntity<List<Book>> read(){
        return ResponseEntity.ok(service.read());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Book> readById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(service.readById(id));
    }
    // Update
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@Valid @PathVariable Long id, @Valid @RequestBody Book book){
        if(service.readById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Book());
        return ResponseEntity.ok(service.create(book));
    }
    // Delete
    @Override
    @DeleteMapping("")
    public ResponseEntity<Book> delete(){
        service.delete();
        return ResponseEntity.ok(new Book());
    }
}