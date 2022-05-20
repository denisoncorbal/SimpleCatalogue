package br.com.dgc.simplecatalogue.model.dto.assembler;

import br.com.dgc.simplecatalogue.model.dto.AuthorModel;
import br.com.dgc.simplecatalogue.model.entity.Author;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public abstract class AuthorModelAssembler<T1 extends Author, T2 extends AuthorModel<? extends T2>> extends
    RepresentationModelAssemblerSupport<T1, T2> {

  public AuthorModelAssembler(Class<?> controllerClass, Class<T2> resourceType) {
    super(controllerClass, resourceType);
  }

}
