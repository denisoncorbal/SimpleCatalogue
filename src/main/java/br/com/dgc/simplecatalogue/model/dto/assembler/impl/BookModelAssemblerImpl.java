package br.com.dgc.simplecatalogue.model.dto.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.dgc.simplecatalogue.controller.impl.BookControllerImpl;
import br.com.dgc.simplecatalogue.controller.CopyController;
import br.com.dgc.simplecatalogue.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.model.dto.assembler.WorkModelAssembler;
import br.com.dgc.simplecatalogue.model.dto.impl.BookModelImpl;
import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.model.entity.Copy;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Implementation of Abstract Class WorkModelAssembler for convert Entity Book into BookModelImpl.
 * Used for HATEOAS purposes.
 *
 * @see WorkModelAssembler
 * @see Book
 * @see BookModelImpl
 * @since 1.0
 */
@Component
public class BookModelAssemblerImpl extends WorkModelAssembler<Book, BookModelImpl> {

  public BookModelAssemblerImpl() {
    super(BookControllerImpl.class, BookModelImpl.class);
  }

  @Override
  public BookModelImpl toModel(Book entity) {
    BookModelImpl bookModel = instantiateModel(entity);

    bookModel.add(
        linkTo(methodOn(BookControllerImpl.class).readById(entity.getIdWork())).withSelfRel());

    bookModel.setIdWork(entity.getIdWork());
    bookModel.setName(entity.getName());
    bookModel.setCopies(toCopyModel(entity.getCopies()));

    return bookModel;
  }

  private Set<CopyModel> toCopyModel(Set<Copy> copies) {
    if (copies.isEmpty()) {
      return Collections.emptySet();
    }

    return copies.stream()
        .map(
            copy ->
                CopyModel.builder()
                    .idCopy(copy.getIdCopy())
                    .build()
                    .add(
                        linkTo(methodOn(CopyController.class).readById(copy.getIdCopy()))
                            .withSelfRel()))
        .collect(Collectors.toSet());
  }
}
