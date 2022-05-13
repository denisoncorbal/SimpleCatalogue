package br.com.dgc.simplecatalogue.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.dgc.simplecatalogue.model.dto.assembler.impl.BookModelAssemblerImpl;
import br.com.dgc.simplecatalogue.model.dto.impl.BookModelImpl;
import br.com.dgc.simplecatalogue.model.entity.Book;
import io.jsonwebtoken.lang.Assert;
import java.util.Collections;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class BookTests {

  final private BookModelAssemblerImpl assembler = new BookModelAssemblerImpl();

  @Test
  public void givenBook_whenConvertToDTO_thenReturnBookModelImpl() {
    Book book = new Book();
    Assert.isInstanceOf(BookModelImpl.class, assembler.toModel(book));
  }

  @Test
  public void givenBook_whenConvertToDTO_thenReturnCorrespondingBookModelImpl() {
    Book book =
        new Book(
            new Random().nextLong(),
            Character.getName(new Random().nextInt(126)),
            Collections.emptySet());
    BookModelImpl model = assembler.toModel(book);

    assertEquals(model.getIdWork(), book.getIdWork());
    assertEquals(model.getName(), book.getName());
    assertEquals(model.getCopies().isEmpty(), book.getCopies().isEmpty());
  }

  @Test
  public void givenBookModelBuilder_whenBuildBookModel_thenReturnCorrespondingBookModelImpl(){
    BookModelImpl bookModel =
        new BookModelImpl(
            new Random().nextLong(),
            Character.getName(new Random().nextInt(126)),
            Collections.emptySet());

    BookModelImpl bookModelBuilded =
        BookModelImpl.builder().idWork(bookModel.getIdWork()).name(bookModel.getName()).copies(bookModel.getCopies()).build();

    assertEquals(bookModelBuilded.getIdWork(), bookModel.getIdWork());
    assertEquals(bookModelBuilded.getName(), bookModel.getName());
    assertEquals(bookModelBuilded.getCopies(), bookModel.getCopies());
  }
}
