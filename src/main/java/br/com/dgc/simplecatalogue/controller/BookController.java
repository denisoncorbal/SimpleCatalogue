package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookServiceImpl service;

    public BookController(BookServiceImpl service){
        this.service = service;
    }

    // Create
    @PostMapping("")
    public ResponseEntity<Book> create(@Valid @RequestBody Book book){
        return ResponseEntity.ok(service.create(book));
    }
    // Read
    @GetMapping("")
    public ResponseEntity<List<Book>> read(){
        return ResponseEntity.ok(service.read());
    }
    // Update
    @PostMapping("/{id}")
    public ResponseEntity<Book> update(@Valid @PathVariable Long id, @Valid @RequestBody Book book){
        if(service.readById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Book());
        return ResponseEntity.ok(service.create(book));
    }
    // Delete
    @DeleteMapping("")
    public ResponseEntity<Book> delete(){
        service.delete();
        return ResponseEntity.ok(new Book());
    }
}