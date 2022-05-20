package br.com.dgc.simplecatalogue.controller.impl;

import br.com.dgc.simplecatalogue.controller.AuthorController;
import br.com.dgc.simplecatalogue.model.entity.BookAuthor;
import br.com.dgc.simplecatalogue.service.impl.BookAuthorServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthorController.path + "/book_authors")
public class BookAuthorControllerImpl implements AuthorController<BookAuthor> {

  private final BookAuthorServiceImpl service;

  public BookAuthorControllerImpl(BookAuthorServiceImpl service){
    this.service = service;
  }

  @Override
  @PostMapping("")
  public ResponseEntity<BookAuthor> create(@Valid @RequestBody BookAuthor author) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(author));
  }

  @Override
  @GetMapping("")
  public ResponseEntity<List<BookAuthor>> read() {
    List<BookAuthor> bookAuthors = service.read();
    if(bookAuthors.isEmpty()){
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookAuthors);
    }
    return ResponseEntity.ok(bookAuthors);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<BookAuthor> readById(@Valid @PathVariable Long id) {
    return ResponseEntity.ok(service.readById(id).orElse(new BookAuthor()));
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<BookAuthor> update(@Valid @PathVariable Long id, @Valid @RequestBody BookAuthor author) {
    if(service.readById(id).isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookAuthor());
    }
    return ResponseEntity.ok(service.create(author));
  }

  @Override
  public ResponseEntity<BookAuthor> delete() {
    service.delete();
    return ResponseEntity.ok(new BookAuthor());
  }
}
