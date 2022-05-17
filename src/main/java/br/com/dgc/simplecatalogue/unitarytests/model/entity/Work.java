package br.com.dgc.simplecatalogue.unitarytests.model.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * Generalization of any kind of work to Database Entity representation.
 *
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Work implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idWork;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "work")
  private Set<Copy> copies;

  public Work() {
    this.idWork = 0L;
    this.name = "";
    this.copies = Collections.emptySet();
  }

  public Work(Long idWork, String name, Set<Copy> copies){
    this.idWork = idWork;
    this.name = name;
    this.copies = copies;
  }

  public Long getIdWork() {
    return idWork;
  }

  public String getName() {
    return name;
  }

  public void setIdWork(Long idWork) {
    this.idWork = idWork;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Copy> getCopies() {
    return copies;
  }

  public void setCopies(Set<Copy> copies) {
    this.copies = copies;
  }
}
