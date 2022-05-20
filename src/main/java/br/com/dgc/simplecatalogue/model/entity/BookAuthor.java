package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_book_authors")
public class BookAuthor extends Author{

}
