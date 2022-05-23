package br.com.dgc.simplecatalogue.unitarytests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import br.com.dgc.simplecatalogue.controller.impl.BookControllerImpl;
import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.service.impl.BookServiceImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith({MockitoExtension.class})
public class BookControllerTests {

  @Mock
  private BookServiceImpl bookService;

  @InjectMocks
  private BookControllerImpl bookController;

  final private Book voidBook = new Book();

  final private Book validBook = new Book(0L,
      "book",
      Collections.emptySet(),
      Collections.emptySet(),
      "isbn",
      "issn",
      "language",
      "cdd");

  @Test
  public void givenValidBook_whenPostToBook_thenRespondCreated(){
    given(bookService.create(validBook)).willReturn(validBook);

    ResponseEntity<Book> response = bookController.create(validBook);

    then(bookService).should().create(validBook);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void givenValidBook_whenPostToBook_thenRespondNotVoidBook(){
    given(bookService.create(validBook)).willReturn(validBook);

    ResponseEntity<Book> response = bookController.create(validBook);

    then(bookService).should().create(validBook);
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getIdWork());
  }

  @Test
  public void givenGetToBook_whenExistBookInDatabase_thenRespondOk(){
    List<Book> books = List.of(validBook);
    given(bookService.read()).willReturn(books);

    ResponseEntity<List<Book>> response = bookController.read();

    then(bookService).should().read();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenGetToBook_whenExistBookInDatabase_thenRespondNotEmptyBookList(){
    List<Book> books = List.of(validBook);
    given(bookService.read()).willReturn(books);

    ResponseEntity<List<Book>> response = bookController.read();

    then(bookService).should().read();
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
  }

  @Test
  public void givenGetToBook_whenInexistsBookInDatabase_thenRespondNoContent(){
    given(bookService.read()).willReturn(Collections.emptyList());

    ResponseEntity<List<Book>> response = bookController.read();

    then(bookService).should().read();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void givenGetToBook_whenInexistsBookInDatabase_thenRespondEmptyBookList(){
    given(bookService.read()).willReturn(Collections.emptyList());

    ResponseEntity<List<Book>> response = bookController.read();

    then(bookService).should().read();
    assertNotNull(response.getBody());
    assertTrue(response.getBody().isEmpty());
  }

  @Test
  public void givenUpdateValidId_whenExistsInDatabase_thenRespondOK(){
    given(bookService.readById(any())).willReturn(Optional.of(validBook));

    ResponseEntity<Book> response = bookController.update(validBook.getIdWork(), validBook);

    then(bookService).should().readById(any());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenUpdateValidId_whenExistsInDatabase_thenRespondValidBook(){
    given(bookService.readById(any())).willReturn(Optional.of(validBook));
    given(bookService.create(validBook)).willReturn(validBook);

    ResponseEntity<Book> response = bookController.update(validBook.getIdWork(), validBook);

    then(bookService).should().readById(any());
    then(bookService).should().create(validBook);
    assertNotNull(response.getBody());
    assertEquals(validBook.getIdWork(), response.getBody().getIdWork());
  }

  @Test
  public void givenUpdateValidId_whenInexistsInDatabase_thenRespondNotFound(){
    given(bookService.readById(any())).willReturn(Optional.empty());

    ResponseEntity<Book> response = bookController.update(validBook.getIdWork(), validBook);

    then(bookService).should().readById(any());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void givenUpdateValidId_whenInexistsInDatabase_thenRespondEmptyBook(){
    given(bookService.readById(any())).willReturn(Optional.empty());

    ResponseEntity<Book> response = bookController.update(validBook.getIdWork(), validBook);

    then(bookService).should().readById(any());
    assertNotNull(response.getBody());
    assertEquals(0L, response.getBody().getIdWork());
  }

  @Test
  public void givenRequisition_whenDeleteCopies_thenRespondOK(){
    willDoNothing().given(bookService).delete();

    ResponseEntity<Book> response = bookController.delete();

    then(bookService).should().delete();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenRequisition_whenDeleteCopies_thenRespondEmptyCopy(){
    willDoNothing().given(bookService).delete();

    ResponseEntity<Book> response = bookController.delete();

    then(bookService).should().delete();
    assertNotNull(response.getBody());
    assertEquals(0L, response.getBody().getIdWork());
  }

}