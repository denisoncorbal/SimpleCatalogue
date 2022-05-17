package br.com.dgc.simplecatalogue.unitarytests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.dgc.simplecatalogue.unitarytests.model.dto.assembler.impl.BookModelAssemblerImpl;
import br.com.dgc.simplecatalogue.unitarytests.model.dto.impl.BookModelImpl;
import br.com.dgc.simplecatalogue.unitarytests.model.entity.Book;
import br.com.dgc.simplecatalogue.unitarytests.model.entity.Copy;
import io.jsonwebtoken.lang.Assert;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class BookTests {

  final private BookModelAssemblerImpl assembler = new BookModelAssemblerImpl();

  @Test
  public void givenBook_whenConvertToDTO_thenReturnBookModelImpl() {
    Book book = new Book();
    Book book2 = new Book(0L, "name", Collections.emptySet());
    BookModelImpl bookModel = BookModelImpl.builder()
        .idWork(0L)
        .name("name")
        .copies(Collections.emptySet())
        .build();

    Assert.isInstanceOf(BookModelImpl.class, assembler.toModel(book));
    Assert.isInstanceOf(BookModelImpl.class, assembler.toModel(book2));
    assertNotNull(bookModel);
    assertEquals(0L, bookModel.getIdWork());
    assertEquals("name", bookModel.getName());
    assertTrue(bookModel.getCopies().isEmpty());
  }

  @Test
  public void givenBook_whenConvertToDTO_thenReturnCorrespondingBookModelImpl() {
    Book book =
        new Book(
            new Random().nextLong(),
            Character.getName(new Random().nextInt(126)),
            Set.of(new Copy()));
    BookModelImpl model = assembler.toModel(book);

    assertEquals(model.getIdWork(), book.getIdWork());
    assertEquals(model.getName(), book.getName());
    assertEquals(model.getCopies().isEmpty(), book.getCopies().isEmpty());
  }

}
