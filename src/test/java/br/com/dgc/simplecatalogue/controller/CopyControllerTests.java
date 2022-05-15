package br.com.dgc.simplecatalogue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import br.com.dgc.simplecatalogue.model.entity.Copy;
import br.com.dgc.simplecatalogue.service.impl.CopyServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class CopyControllerTests {

  @Mock
  private CopyServiceImpl copyService;

  @InjectMocks
  private CopyController copyController;

  final private Copy voidCopy = new Copy();

  final private Copy validCopy = new Copy(0L, null);

  @Test
  public void givenValidCopy_whenPostToCopy_thenRespondCreated(){
    given(copyService.create(validCopy)).willReturn(validCopy);

    ResponseEntity<Copy> response = copyController.create(validCopy);

    then(copyService).should().create(validCopy);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void givenValidCopy_whenPostToCopy_thenRespondNotVoidCopy(){
    given(copyService.create(validCopy)).willReturn(validCopy);

    ResponseEntity<Copy> response = copyController.create(validCopy);

    then(copyService).should().create(validCopy);
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getIdCopy());
  }

  @Test
  public void givenGetToCopy_whenExistCopyInDatabase_thenRespondOk(){
    List<Copy> copies = List.of(validCopy);
    given(copyService.read()).willReturn(copies);

    ResponseEntity<List<Copy>> response = copyController.read();

    then(copyService).should().read();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenGetToCopy_whenExistCopyInDatabase_thenRespondNotEmptyCopyList(){
    List<Copy> copies = List.of(validCopy);
    given(copyService.read()).willReturn(copies);

    ResponseEntity<List<Copy>> response = copyController.read();

    then(copyService).should().read();
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
  }

  @Test
  public void givenGetToCopy_whenInexistsCopyInDatabase_thenRespondNoContent(){
    given(copyService.read()).willReturn(Collections.emptyList());

    ResponseEntity<List<Copy>> response = copyController.read();

    then(copyService).should().read();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void givenGetToCopy_whenInexistsCopyInDatabase_thenRespondEmptyCopyList(){
    given(copyService.read()).willReturn(Collections.emptyList());

    ResponseEntity<List<Copy>> response = copyController.read();

    then(copyService).should().read();
    assertNotNull(response.getBody());
    assertTrue(response.getBody().isEmpty());
  }

  @Test
  public void givenUpdateValidId_whenExistsInDatabase_thenRespondOK(){
    given(copyService.readById(any())).willReturn(Optional.of(validCopy));

    ResponseEntity<Copy> response = copyController.update(validCopy.getIdCopy(), validCopy);

    then(copyService).should().readById(any());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenUpdateValidId_whenExistsInDatabase_thenRespondValidCopy(){
    given(copyService.readById(any())).willReturn(Optional.of(validCopy));
    given(copyService.create(validCopy)).willReturn(validCopy);

    ResponseEntity<Copy> response = copyController.update(validCopy.getIdCopy(), validCopy);

    then(copyService).should().readById(any());
    then(copyService).should().create(validCopy);
    assertNotNull(response.getBody());
    assertEquals(validCopy.getIdCopy(), response.getBody().getIdCopy());
  }

  @Test
  public void givenUpdateValidId_whenInexistsInDatabase_thenRespondNotFound(){
    given(copyService.readById(any())).willReturn(Optional.empty());

    ResponseEntity<Copy> response = copyController.update(validCopy.getIdCopy(), validCopy);

    then(copyService).should().readById(any());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void givenUpdateValidId_whenInexistsInDatabase_thenRespondEmptyCopy(){
    given(copyService.readById(any())).willReturn(Optional.empty());

    ResponseEntity<Copy> response = copyController.update(validCopy.getIdCopy(), validCopy);

    then(copyService).should().readById(any());
    assertNotNull(response.getBody());
    assertEquals(0L, response.getBody().getIdCopy());
  }

  @Test
  public void givenRequisition_whenDeleteCopies_thenRespondOK(){
    willDoNothing().given(copyService).delete();

    ResponseEntity<Copy> response = copyController.delete();

    then(copyService).should().delete();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void givenRequisition_whenDeleteCopies_thenRespondEmptyCopy(){
    willDoNothing().given(copyService).delete();

    ResponseEntity<Copy> response = copyController.delete();

    then(copyService).should().delete();
    assertNotNull(response.getBody());
    assertEquals(0L, response.getBody().getIdCopy());
  }

}
