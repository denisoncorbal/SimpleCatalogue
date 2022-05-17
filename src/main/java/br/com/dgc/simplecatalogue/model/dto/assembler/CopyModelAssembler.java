package br.com.dgc.simplecatalogue.model.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.dgc.simplecatalogue.controller.CopyController;
import br.com.dgc.simplecatalogue.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.model.entity.Copy;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Converts Entity Copy into CopyModel. Used for HATEOAS purposes.
 *
 * @see Copy
 * @see CopyModel
 * @since 1.0
 */
@Component
public class CopyModelAssembler extends RepresentationModelAssemblerSupport<Copy, CopyModel> {

  public CopyModelAssembler() {
    super(CopyController.class, CopyModel.class);
  }

  @Override
  public CopyModel toModel(Copy entity) {
    CopyModel copyModel = instantiateModel(entity);

    copyModel.add(
        linkTo(methodOn(CopyController.class).readById(entity.getIdCopy())).withSelfRel());

    copyModel.setIdCopy(entity.getIdCopy());
    copyModel.setWork(entity.getWork());

    return copyModel;
  }
}
