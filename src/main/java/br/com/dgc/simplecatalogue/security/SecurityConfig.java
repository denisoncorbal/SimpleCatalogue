package br.com.dgc.simplecatalogue.security;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

/** Default configurations for security purpose. */
@Configuration
@ConfigurationProperties(prefix = "security.config")
@ConstructorBinding
public class SecurityConfig {
  public final String prefix;

  public final String key;

  private SecretKey secretKey;
  public final Long expiration;

  /**
   * Constructor for inject parameters from application.properties.
   *
   * @param prefix Prefix used for Authorization header. Default "Bearer" for Bearer schema.
   * @param key Value used for generate the secretKey.
   * @param expiration Valor used as default for make the token expirated.
   */
  public SecurityConfig(String prefix, String key, Long expiration) {
    this.prefix = prefix;
    this.key = key;
    this.expiration = expiration;

    try {
      this.secretKey = Keys.hmacShaKeyFor(this.key.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public SecretKey getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(SecretKey secretKey) {
    this.secretKey = secretKey;
  }
}
