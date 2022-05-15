package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Database representation of a Book.
 *
 * @see Work
 * @since 1.0
 */
@Entity
@Table(name = "tb_copies")
public class Copy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCopy;

  @ManyToOne
  @JoinColumn(name = "idWork", nullable = false)
  private Work work;

  public Copy(){
    this.idCopy = 0L;
    this.work = null;
  }

  public Copy(Long idCopy, Work work){
    this.idCopy = idCopy;
    this.work = work;
  }

  public Long getIdCopy() {
    return idCopy;
  }

  public void setIdCopy(Long idCopy) {
    this.idCopy = idCopy;
  }

  public Work getWork() {
    return work;
  }

  public void setWork(Work work) {
    this.work = work;
  }
}
