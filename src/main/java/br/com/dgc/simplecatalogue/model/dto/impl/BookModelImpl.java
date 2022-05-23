package br.com.dgc.simplecatalogue.model.dto.impl;

import br.com.dgc.simplecatalogue.model.dto.AuthorModel;
import br.com.dgc.simplecatalogue.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import br.com.dgc.simplecatalogue.model.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Set;
import org.springframework.hateoas.server.core.Relation;

/**
 * Implementation from Abstract Class WorkModel for RepresentationModel of Entity Book.
 *
 * @see WorkModel
 * @see Book
 * @see org.springframework.hateoas.RepresentationModel
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "book")
@Relation(itemRelation = "work")
public class BookModelImpl extends WorkModel<BookModelImpl> {

  private String isbn;

  private String issn;

  private String language;

  private String cdd;

  public BookModelImpl() {
    super();
  }

  /**
   * All args constructor used by Builder Pattern.
   *
   * @param idWork Unique identifier from Book.
   * @param name Name from Book.
   * @param copies All the copies related to the Book.
   * @see Book
   * @since 1.0
   */
  protected BookModelImpl(
      Long idWork,
      String name,
      Set<CopyModel> copies,
      Set<? extends AuthorModel> authors,
      String isbn,
      String issn,
      String language,
      String cdd) {
    super(idWork, name, copies, authors);
    this.isbn = isbn;
    this.issn = issn;
    this.language = language;
    this.cdd = cdd;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getIssn() {
    return issn;
  }

  public String getLanguage() {
    return language;
  }

  public String getCdd() {
    return cdd;
  }

  public static BookModelBuilder builder() {
    return new BookModelBuilder();
  }

  /**
   * Class representing Builder Pattern of BookModelImpl.
   *
   * @see BookModelImpl
   * @since 1.0
   */
  public static class BookModelBuilder {

    private Long idWork;
    private String name;
    private Set<CopyModel> copies;

    private Set<? extends AuthorModel> authors;

    private String isbn;

    private String issn;

    private String language;

    private String cdd;

    BookModelBuilder() {}

    public BookModelBuilder idWork(Long idWork) {
      this.idWork = idWork;
      return this;
    }

    public BookModelBuilder name(String name) {
      this.name = name;
      return this;
    }

    public BookModelBuilder copies(Set<CopyModel> copies) {
      this.copies = copies;
      return this;
    }

    public BookModelBuilder authors(Set<? extends AuthorModel> authors) {
      this.authors = authors;
      return this;
    }

    public BookModelBuilder isbn(String isbn) {
      this.isbn = isbn;
      return this;
    }

    public BookModelBuilder issn(String issn) {
      this.issn = issn;
      return this;
    }

    public BookModelBuilder language(String language) {
      this.language = language;
      return this;
    }

    public BookModelBuilder cdd(String cdd) {
      this.cdd = cdd;
      return this;
    }

    public BookModelImpl build() {
      return new BookModelImpl(
          this.idWork,
          this.name,
          this.copies,
          this.authors,
          this.isbn,
          this.issn,
          this.language,
          this.cdd);
    }
  }
}
