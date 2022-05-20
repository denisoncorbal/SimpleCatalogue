package br.com.dgc.simplecatalogue.model.dto;

import java.util.Set;
import org.springframework.hateoas.RepresentationModel;

public abstract class AuthorModel<T extends AuthorModel<? extends T>> extends RepresentationModel<T> {

  private Long idAuthor;

  private String name;

  private Set<? extends WorkModel> works;

  public AuthorModel() {
  }

  public AuthorModel(Long idAuthor, String name, Set<? extends WorkModel> works) {
    this.idAuthor = idAuthor;
    this.name = name;
    this.works = works;
  }

  public Long getIdAuthor() {
    return idAuthor;
  }

  public void setIdAuthor(Long idAuthor) {
    this.idAuthor = idAuthor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<? extends WorkModel> getWorks() {
    return works;
  }

  public void setWorks(Set<? extends WorkModel> works) {
    this.works = works;
  }
}
