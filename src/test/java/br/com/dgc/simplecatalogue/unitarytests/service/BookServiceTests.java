package br.com.dgc.simplecatalogue.unitarytests.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.repository.impl.BookRepositoryImpl;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
  @Mock
  private BookRepositoryImpl repository;

  @InjectMocks
  private BookServiceImpl service;

  final private Book validBook = new Book(0L,
      "book",
      Collections.emptySet(),
      Collections.emptySet(),
      "isbn",
      "issn",
      "language",
      "cdd");

  @Test
  public void create(){
    given(repository.save(any())).willReturn(validBook);

    Book book = service.create(validBook);

    then(repository).should().save(validBook);
    assertNotNull(book);
  }

  @Test
  public void read(){
    List<Book> books = List.of(validBook);
    given(repository.findAll()).willReturn(books);

    books = service.read();

    then(repository).should().findAll();
    assertNotNull(books);
  }

  @Test
  public void readById(){
    given(repository.findById(any())).willReturn(Optional.of(validBook));

    Book book = service.readById(validBook.getIdWork()).orElse(new Book());

    then(repository).should().findById(book.getIdWork());
    assertNotNull(book);
  }

  @Test
  public void update(){
    given(repository.save(validBook)).willReturn(validBook);

    Book book = service.update(validBook);

    then(repository).should().save(validBook);
    assertNotNull(book);
  }

  @Test
  public void delete(){
    willDoNothing().given(repository).deleteAll();

    service.delete();

    then(repository).should().deleteAll();
  }
}
