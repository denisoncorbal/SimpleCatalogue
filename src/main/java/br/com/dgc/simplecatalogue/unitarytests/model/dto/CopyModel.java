package br.com.dgc.simplecatalogue.unitarytests.model.dto;

import br.com.dgc.simplecatalogue.unitarytests.model.entity.Work;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * RepresentationModel of Entity Copy. Used for HATEOAS purposes.
 *
 * @see RepresentationModel
 * @see br.com.dgc.simplecatalogue.unitarytests.model.entity.Copy
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("copy")
@Relation(collectionRelation = "copies")
public class CopyModel extends RepresentationModel<CopyModel> {

  private Long idCopy;

  private Work work;

  public CopyModel(){
    this.idCopy = 0L;
    this.work = null;
  }

  protected CopyModel(Long idCopy, Work work) {
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

  public static CopyModelBuilder builder() {
    return new CopyModelBuilder();
  }

  /**
   * Class representing Builder Pattern for CopyModel.
   *
   * @since 1.0
   */
  public static class CopyModelBuilder {

    private Long idCopy;
    private Work work;

    CopyModelBuilder() {}

    public CopyModelBuilder idCopy(Long idCopy) {
      this.idCopy = idCopy;
      return this;
    }

    public CopyModelBuilder work(Work work) {
      this.work = work;
      return this;
    }

    public CopyModel build() {
      return new CopyModel(this.idCopy, this.work);
    }
  }
}
