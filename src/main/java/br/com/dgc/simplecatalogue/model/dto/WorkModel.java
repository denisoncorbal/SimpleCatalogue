package br.com.dgc.simplecatalogue.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;
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
