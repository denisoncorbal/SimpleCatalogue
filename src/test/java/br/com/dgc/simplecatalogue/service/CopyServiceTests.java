package br.com.dgc.simplecatalogue.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import br.com.dgc.simplecatalogue.model.entity.Book;
import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.repository.CopyRepository;
import br.com.dgc.simplecatalogue.service.impl.CopyServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CopyServiceTests {

  @Mock
  private CopyRepository repository;

  @InjectMocks
  private CopyServiceImpl service;

  final private Copy validCopy = new Copy(0L, null);

  @Test
  public void create(){
    given(repository.save(any())).willReturn(validCopy);

    Copy copy = service.create(validCopy);

    then(repository).should().save(validCopy);
    assertNotNull(copy);
  }

  @Test
  public void read(){
    List<Copy> copies = List.of(validCopy);
    given(repository.findAll()).willReturn(copies);

    copies = service.read();

    then(repository).should().findAll();
    assertNotNull(copies);
  }

  @Test
  public void readById(){
    given(repository.findById(any())).willReturn(Optional.of(validCopy));

    Copy copy = service.readById(validCopy.getIdCopy()).orElse(new Copy());

    then(repository).should().findById(copy.getIdCopy());
    assertNotNull(copy);
  }

  @Test
  public void update(){
    given(repository.save(validCopy)).willReturn(validCopy);

    Copy copy = service.update(validCopy);

    then(repository).should().save(validCopy);
    assertNotNull(copy);
  }

  @Test
  public void delete(){
    willDoNothing().given(repository).deleteAll();

    service.delete();

    then(repository).should().deleteAll();
  }
}
