package br.com.dgc.simplecatalogue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.service.impl.CopyServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CopyControllerTests {

  @Mock
  private CopyServiceImpl copyService;

  @InjectMocks
  private CopyController copyController;

  final private Copy voidCopy = new Copy();

  final private Copy validCopy = new Copy(0L, null);

  @Test
  public void createTest(){
    ResponseEntity<Copy> response = copyController.create(validCopy);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void readTest(){
    ResponseEntity<List<Copy>> response = copyController.read();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void updateTest(){
    when(copyService.readById(any())).thenReturn(validCopy);
    ResponseEntity<Copy> response = copyController.update(validCopy.getIdCopy(), validCopy);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void deleteTest(){
    ResponseEntity<Copy> response = copyController.delete();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
