package br.com.dgc.simplecatalogue.controller.impl;

import br.com.dgc.simplecatalogue.controller.WorkController;
import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller who captures HttpRequests on base-path/books.
 *
 * @see Book
 * @since 1.0
 */
@RestController
@RequestMapping(WorkController.path + "/books")
public class BookControllerImpl implements WorkController<Book> {
  private final BookServiceImpl service;

  public BookControllerImpl(BookServiceImpl service) {
    this.service = service;
  }

  // Create
  @Override
  @PostMapping("")
  public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(book));
  }
  // Read

  @Override
  @GetMapping("")
  public ResponseEntity<List<Book>> read() {
    List<Book> books = service.read();
    if (books.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(books);
    }
    return ResponseEntity.ok(books);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Book> readById(@Valid @PathVariable Long id) {
    return ResponseEntity.ok(service.readById(id).orElse(new Book()));
  }
  // Update

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Book> update(@Valid @PathVariable Long id, @Valid @RequestBody Book book) {
    if (service.readById(id).isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Book());
    }

    return ResponseEntity.ok(service.create(book));
  }
  // Delete

  @Override
  @DeleteMapping("")
  public ResponseEntity<Book> delete() {
    service.delete();
    return ResponseEntity.ok(new Book());
  }
}
