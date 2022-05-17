package br.com.dgc.simplecatalogue.unitarytests;

import static org.junit.jupiter.api.Assertions.fail;

import br.com.dgc.simplecatalogue.SimpleCatalogueApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleCatalogueApplicationTests {

	@Test
	public void runApplication(){
		try{
			SimpleCatalogueApplication.main(new String[]{""});
		}
		catch (Exception e){
			fail();
		}
	}
}
