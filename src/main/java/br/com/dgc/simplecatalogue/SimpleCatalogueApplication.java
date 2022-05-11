package br.com.dgc.simplecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimpleCatalogueApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(SimpleCatalogueApplication.class, args)) {
			System.out.println("Inicialização com êxito");
		} catch (Exception e) {
			System.out.println("Erro de inicialização");
		}
	}
}
