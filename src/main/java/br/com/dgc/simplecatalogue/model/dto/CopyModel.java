package br.com.dgc.simplecatalogue.model.dto;

import br.com.dgc.simplecatalogue.model.entity.Work;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("copy")
@Relation(collectionRelation = "copies")
public class CopyModel extends RepresentationModel<CopyModel> {

    private Long idCopy;

    private Work work;

    public CopyModel(Long idCopy, Work work){
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

    public static class CopyModelBuilder {

        private Long idCopy;
        private Work work;

        CopyModelBuilder(){}

        public CopyModelBuilder idCopy(Long idCopy){
            this.idCopy = idCopy;
            return this;
        }

        public CopyModelBuilder work(Work work){
            this.work = work;
            return this;
        }

        public CopyModel build(){
            return new CopyModel(this.idCopy, this.work);
        }

    }

}
