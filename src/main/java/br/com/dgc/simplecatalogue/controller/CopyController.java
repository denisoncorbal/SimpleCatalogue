package br.com.dgc.simplecatalogue.controller;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.service.impl.CopyServiceImpl;
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
 * Rest Controller who captures HttpRequests on base-path/copies.
 *
 * @see Copy
 * @since 1.0
 */
@RestController
@RequestMapping("${api.base-path}/copies")
public class CopyController {
  private final CopyServiceImpl service;

  public CopyController(CopyServiceImpl service) {
    this.service = service;
  }

  // Create
  @PostMapping("")
  public ResponseEntity<Copy> create(@Valid @RequestBody Copy copy) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(copy));
  }

  // Read

  /**
   * Captures GET requisitions on copies and return all copies stored in database.
   *
   * @return ResponseEntity with body containing all copies stored in database and status NO_CONTENT
   *     if has none e SUCESS if has any.
   * @since 1.0
   */
  @GetMapping("")
  public ResponseEntity<List<Copy>> read() {
    List<Copy> copies = service.read();
    if (copies.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(copies);
    }

    return ResponseEntity.ok(copies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Copy> readById(@Valid @PathVariable Long id) {
    return ResponseEntity.ok(service.readById(id).orElse(new Copy()));
  }

  // Update

  /**
   * Updates a Copy already saved on database.
   *
   * @param id Unique identifier from Copy to be updated.
   * @param copy Copy with data to be updated into database.
   * @return ResponseEntity with HttpStatus not found and empty Copy as body or HttpStatus ok and
   *     updated Copy as body.
   * @see ResponseEntity
   * @see Copy
   * @see HttpStatus
   */
  @PutMapping("/{id}")
  public ResponseEntity<Copy> update(@Valid @PathVariable Long id, @Valid @RequestBody Copy copy) {
    if (service.readById(id).isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Copy());
    }

    return ResponseEntity.ok(service.create(copy));
  }

  // Delete
  @DeleteMapping("")
  public ResponseEntity<Copy> delete() {
    service.delete();
    return ResponseEntity.ok(new Copy());
  }
}
