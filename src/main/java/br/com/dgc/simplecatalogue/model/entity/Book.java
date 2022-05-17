package br.com.dgc.simplecatalogue.model.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Define a specific type of Work referenced as book.
 *
 * @see Work
 * @since 1.0
 */
@Entity
@Table(name = "tb_books")
public class Book extends Work {
  public Book() {
    super();
  }

  public Book(Long idWork, String name, Set<Copy> copies) {
    super(idWork, name, copies);
  }
}
