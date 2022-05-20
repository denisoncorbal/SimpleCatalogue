package br.com.dgc.simplecatalogue.model.dto;

import br.com.dgc.simplecatalogue.model.entity.Work;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;

/**
 * RepresentationModel abstraction for Entities who inherit from Work. Used for HATEOAS purposes.
 *
 * @param <T> Entity who implementation will transform into RepresentationModel.
 * @see RepresentationModel
 * @see Work
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class WorkModel<T extends WorkModel<? extends T>> extends RepresentationModel<T> {

  /** No args constructor for WorkModel. */
  public WorkModel() {
    this.idWork = 0L;
    this.name = "";
    this.copies = Collections.emptySet();
    this.authors = Collections.emptySet();
  }

  /**
   * All args constructor for WorkModel.
   *
   * @param idWork Unique identifier for WorkModel.
   * @param name Name of WorkModel.
   * @param copies Set of copies created from this WorkModel.
   * @param authors Set of authors of the WorkModel.
   */
  public WorkModel(Long idWork, String name, Set<CopyModel> copies, Set<? extends AuthorModel> authors) {
    this.idWork = idWork;
    this.name = name;
    this.copies = copies;
    this.authors = authors;
  }

  private Long idWork;
  private String name;
  private Set<CopyModel> copies;

  private Set<? extends AuthorModel> authors;

  public Long getIdWork() {
    return idWork;
  }

  public void setIdWork(Long idWork) {
    this.idWork = idWork;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<CopyModel> getCopies() {
    return copies;
  }

  public void setCopies(Set<CopyModel> copies) {
    this.copies = copies;
  }

  public Set<? extends AuthorModel> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<? extends AuthorModel> authors) {
    this.authors = authors;
  }
}
