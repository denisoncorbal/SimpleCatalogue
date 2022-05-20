package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository <T extends Author> extends JpaRepository<T, Long> {}