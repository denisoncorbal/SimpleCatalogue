package br.com.dgc.simplecatalogue.model.dto.impl;

import br.com.dgc.simplecatalogue.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import br.com.dgc.simplecatalogue.model.entity.Copy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "book")
@Relation(itemRelation = "work")
public class BookModelImpl extends WorkModel<BookModelImpl>{


    public BookModelImpl(Long idWork, String name, Set<CopyModel> copies){
        super.setIdWork(idWork);
        super.setName(name);
        super.setCopies(copies);
    }

    public static BookModelBuilder builder() {
        return new BookModelBuilder();
    }

    public static class BookModelBuilder {

        private Long idWork;
        private String name;
        private Set<CopyModel> copies;

        BookModelBuilder(){}

        public BookModelBuilder idWork(Long idWork){
            this.idWork = idWork;
            return this;
        }

        public BookModelBuilder name(String name){
            this.name = name;
            return this;
        }

        public BookModelBuilder copies(Set<CopyModel> copies){
            this.copies = copies;
            return this;
        }

        public BookModelImpl build(){
            return new BookModelImpl(this.idWork, this.name, this.copies);
        }

    }

}
