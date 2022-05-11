package br.com.dgc.simplecatalogue.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Define a specific type of Work referenced as book.
 *
 * @see Work
 * @since 1.0
 */
@Entity
@Table(name = "tb_book")
public class Book extends Work{
}
