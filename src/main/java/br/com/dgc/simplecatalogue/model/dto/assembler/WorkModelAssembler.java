package br.com.dgc.simplecatalogue.model.dto.assembler;

import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import br.com.dgc.simplecatalogue.model.entity.Work;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Generalization for RepresentationModelAssemlberSupoort who will handle conversion between Entity
 * who inherit from Work and his RepresentationModel. Used for HATEOAS purposes.
 *
 * @param <T1> Entity who inherits from Work and should be converted to his RepresentationModel.
 * @param <T2> RepresentationModel suitable for the Entity.
 * @see RepresentationModelAssemblerSupport
 * @see org.springframework.hateoas.RepresentationModel
 * @see Work
 * @see WorkModel
 */
@Component
public abstract class WorkModelAssembler<T1 extends Work, T2 extends WorkModel<? extends T2>>
    extends RepresentationModelAssemblerSupport<T1, T2> {

  public WorkModelAssembler(Class<?> controllerClass, Class<T2> resourceType) {
    super(controllerClass, resourceType);
  }
}
