package br.com.dgc.simplecatalogue.unitarytests.model.dto.impl;

import br.com.dgc.simplecatalogue.unitarytests.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.unitarytests.model.dto.WorkModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Set;
import org.springframework.hateoas.server.core.Relation;

/**
 * Implementation from Abstract Class WorkModel for RepresentationModel of Entity Book.
 *
 * @see WorkModel
 * @see br.com.dgc.simplecatalogue.unitarytests.model.entity.Book
 * @see org.springframework.hateoas.RepresentationModel
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "book")
@Relation(itemRelation = "work")
public class BookModelImpl extends WorkModel<BookModelImpl> {

  public BookModelImpl(){
    super();
  }

  /**
   * All args constructor used by Builder Pattern.
   *
   * @param idWork Unique identifier from Book.
   * @param name Name from Book.
   * @param copies All the copies related to the Book.
   * @see br.com.dgc.simplecatalogue.unitarytests.model.entity.Book
   * @since 1.0
   */
  protected BookModelImpl(Long idWork, String name, Set<CopyModel> copies) {
    super(idWork, name, copies);
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

    public BookModelImpl build() {
      return new BookModelImpl(this.idWork, this.name, this.copies);
    }
  }
}
