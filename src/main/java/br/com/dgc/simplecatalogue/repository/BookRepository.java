package br.com.dgc.simplecatalogue.repository;

import br.com.dgc.simplecatalogue.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
