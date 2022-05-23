package br.com.dgc.simplecatalogue.model.dto.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.dgc.simplecatalogue.controller.impl.BookAuthorControllerImpl;
import br.com.dgc.simplecatalogue.controller.impl.BookControllerImpl;
import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import br.com.dgc.simplecatalogue.model.dto.assembler.AuthorModelAssembler;
import br.com.dgc.simplecatalogue.model.dto.impl.BookAuthorModelImpl;
import br.com.dgc.simplecatalogue.model.dto.impl.BookModelImpl;
import br.com.dgc.simplecatalogue.model.entity.BookAuthor;
import br.com.dgc.simplecatalogue.model.entity.Work;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class BookAuthorModelAssemblerImpl extends AuthorModelAssembler<BookAuthor, BookAuthorModelImpl> {

  public BookAuthorModelAssemblerImpl() {
    super(BookAuthorControllerImpl.class, BookAuthorModelImpl.class);
  }

  @Override
  public BookAuthorModelImpl toModel(BookAuthor entity) {
    BookAuthorModelImpl bookAuthorModel = instantiateModel(entity);

    bookAuthorModel.add(
        linkTo(methodOn(BookAuthorControllerImpl.class).readById(entity.getIdAuthor())).withSelfRel()
    );

    bookAuthorModel.setIdAuthor(entity.getIdAuthor());
    bookAuthorModel.setName(entity.getName());
    bookAuthorModel.setWorks(toWorkModel(entity.getWorks()));

    return bookAuthorModel;
  }

  private Set<? extends WorkModel> toWorkModel(Set<Work> works){
    if(works.isEmpty()){
      return Collections.emptySet();
    }

    return works.stream()
        .map(
            work ->
                BookModelImpl.builder()
                    .idWork(work.getIdWork())
                    .build()
                    .add(
                        linkTo(methodOn(BookControllerImpl.class).readById(work.getIdWork()))
                            .withSelfRel()))
        .collect(Collectors.toSet());
  }

}