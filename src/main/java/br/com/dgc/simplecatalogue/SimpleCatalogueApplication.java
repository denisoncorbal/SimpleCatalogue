package br.com.dgc.simplecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot Application starter.
 *
 * @see SpringBootApplication
 * @since 1.0
 */
@SpringBootApplication
public class SimpleCatalogueApplication {

  /**
   * Run the application providing context needed for execution.
   *
   * @param args Comand line parameters. Not used on this application.
   */
  public static void main(String[] args) {
    try (ConfigurableApplicationContext context =
        SpringApplication.run(SimpleCatalogueApplication.class, args)) {
      System.out.println("Inicialização com êxito");
    } catch (Exception e) {
      System.out.println("Erro de inicialização");
    }
  }
}
