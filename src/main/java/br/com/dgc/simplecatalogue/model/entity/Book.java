package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_book")
public class Book extends Work{
}
