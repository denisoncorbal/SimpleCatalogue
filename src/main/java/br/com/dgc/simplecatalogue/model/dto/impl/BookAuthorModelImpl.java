package br.com.dgc.simplecatalogue.model.dto.impl;

import br.com.dgc.simplecatalogue.model.dto.AuthorModel;
import br.com.dgc.simplecatalogue.model.dto.WorkModel;
import java.util.Set;

public class BookAuthorModelImpl extends AuthorModel<BookAuthorModelImpl> {

  public BookAuthorModelImpl(){
    super();
  }

  public BookAuthorModelImpl(Long id, String name, Set<? extends WorkModel> works){
    super(id, name, works);
  }

  public static BookAuthorModelImplBuilder builder(){return new BookAuthorModelImplBuilder();}

  public static class BookAuthorModelImplBuilder{

    private Long idAuthor;

    private String name;

    private Set<? extends WorkModel> works;

    public BookAuthorModelImplBuilder(){}

    public BookAuthorModelImplBuilder idAuthor(Long idAuthor){
      this.idAuthor = idAuthor;
      return this;
    }

    public BookAuthorModelImplBuilder name(String name){
      this.name = name;
      return this;
    }

    public BookAuthorModelImplBuilder works(Set<? extends WorkModel> works){
      this.works = works;
      return this;
    }

    public BookAuthorModelImpl build(){
      return new BookAuthorModelImpl(this.idAuthor, this.name, this.works);
    }
  }

}