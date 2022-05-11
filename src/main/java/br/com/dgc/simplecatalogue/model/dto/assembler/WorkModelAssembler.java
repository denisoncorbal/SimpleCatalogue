package br.com.dgc.simplecatalogue.model.dto.assembler;

import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import br.com.dgc.simplecatalogue.model.entity.Work;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public abstract class WorkModelAssembler<T1 extends Work, T2 extends WorkModel<? extends T2>>
        extends RepresentationModelAssemblerSupport<T1, T2> {

    public WorkModelAssembler(Class<?> controllerClass, Class<T2> resourceType) {
        super(controllerClass, resourceType);
    }
}
