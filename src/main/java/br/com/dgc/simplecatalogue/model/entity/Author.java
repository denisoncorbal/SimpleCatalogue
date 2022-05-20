package br.com.dgc.simplecatalogue.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Author implements Serializable {

  @Id
  private Long idAuthor;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "tb_works_authors",
      joinColumns = @JoinColumn(name = "idAuthor"),
      inverseJoinColumns = @JoinColumn(name = "idWork")
  )
  private Set<Work> works;

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

  public Set<Work> getWorks() {
    return works;
  }

  public void setWorks(Set<Work> works) {
    this.works = works;
  }
}
