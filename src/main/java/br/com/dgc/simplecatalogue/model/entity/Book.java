package br.com.dgc.simplecatalogue.model.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Define a specific type of Work referenced as book.
 *
 * @see Work
 * @since 1.0
 */
@Entity
@Table(name = "tb_books", indexes = {
    @Index(columnList = "isbn"),
    @Index(columnList = "cdd")
})
public class Book extends Work {

  @Column(length = 13)
  private String isbn;

  @Column(length = 8)
  private String issn;

  @Column(length = 3)
  private String language;

  @Column(length = 7)
  private String cdd;


  public Book() {
    super();
  }

  public Book(Long idWork, String name, Set<Copy> copies, Set<Author> authors, String isbn, String issn, String language, String cdd) {
    super(idWork, name, copies, authors);
    this.isbn = isbn;
    this.issn = issn;
    this.language = language;
    this.cdd = cdd;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getIssn() {
    return issn;
  }

  public void setIssn(String issn) {
    this.issn = issn;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCdd() {
    return cdd;
  }

  public void setCdd(String cdd) {
    this.cdd = cdd;
  }
}
