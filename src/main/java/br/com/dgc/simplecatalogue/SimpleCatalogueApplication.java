package br.com.dgc.simplecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    SpringApplication.run(SimpleCatalogueApplication.class, args);
  }
}
