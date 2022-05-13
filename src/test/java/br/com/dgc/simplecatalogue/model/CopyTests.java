package br.com.dgc.simplecatalogue.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.dgc.simplecatalogue.model.dto.CopyModel;
import br.com.dgc.simplecatalogue.model.dto.assembler.CopyModelAssembler;
import br.com.dgc.simplecatalogue.model.entity.Copy;
import io.jsonwebtoken.lang.Assert;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class CopyTests {
  final private CopyModelAssembler assembler = new CopyModelAssembler();

  @Test
  public void givenCopy_whenConvertToDTO_thenReturnCopyModel() {
    Copy copy = new Copy();
    Assert.isInstanceOf(CopyModel.class, assembler.toModel(copy));
  }

  @Test
  public void givenCopy_whenConvertToDTO_thenReturnCorrespondingCopyModel() {
    Copy copy = new Copy(new Random().nextLong(), null);
    CopyModel model = assembler.toModel(copy);

    assertEquals(model.getIdCopy(), copy.getIdCopy());
    assertEquals(model.getWork(), copy.getWork());
  }

}
