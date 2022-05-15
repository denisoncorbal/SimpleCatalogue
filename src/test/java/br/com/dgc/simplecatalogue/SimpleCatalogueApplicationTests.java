package br.com.dgc.simplecatalogue;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
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
