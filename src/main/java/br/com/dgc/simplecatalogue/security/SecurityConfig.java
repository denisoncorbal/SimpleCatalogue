package br.com.dgc.simplecatalogue.security;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Default configurations for security purpose. */

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {

  private String prefix;
  private String key;
  private long expiration;

  public SecurityConfig(){

  }

  public SecurityConfig(String prefix, String key, long expiration){
    this.prefix = prefix;
    this.key = key;
    this.expiration = expiration;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public long getExpiration() {
    return expiration;
  }

  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }

  @Bean
  public SecretKey getSecretKey(){
    try{
      return Keys.hmacShaKeyFor(this.key.getBytes(StandardCharsets.UTF_8));
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
