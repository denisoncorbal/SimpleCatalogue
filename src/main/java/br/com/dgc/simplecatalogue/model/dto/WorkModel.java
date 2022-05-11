package br.com.dgc.simplecatalogue.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;

/**
 * RepresentationModel abstraction for Entities who inherit from Work. Used for HATEOAS purposes.
 *
 * @param <T> Entity who implementation will transform into RepresentationModel.
 * @see RepresentationModel
 * @see br.com.dgc.simplecatalogue.model.entity.Work
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class WorkModel<T extends WorkModel<? extends T>> extends RepresentationModel<T> {

  private Long idWork;
  private String name;
  private Set<CopyModel> copies;

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
}
