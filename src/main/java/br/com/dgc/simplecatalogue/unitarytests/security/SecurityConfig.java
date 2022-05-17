package br.com.dgc.simplecatalogue.unitarytests.security;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig{
  private String PREFIX;

  private String KEY;

  private SecretKey SECRET_KEY;
  private Long EXPIRATION;

  final private String ALGORITHM = "DSA";

  public SecurityConfig(){

  }

  public SecurityConfig(String prefix, String key, Long expiration){
    this.PREFIX = prefix;
    this.KEY = key;
    this.EXPIRATION = expiration;

    try{
      this.SECRET_KEY = Keys.hmacShaKeyFor(this.KEY.getBytes(StandardCharsets.UTF_8));
    }
    catch (Exception e){
      e.printStackTrace();
    }

  }

  public String getPREFIX() {
    return PREFIX;
  }

  public void setPREFIX(String PREFIX) {
    this.PREFIX = PREFIX;
  }

  public SecretKey getSECRET_KEY() {
    return SECRET_KEY;
  }

  public void setSECRET_KEY(SecretKey SECRET_KEY) {
    this.SECRET_KEY = SECRET_KEY;
  }

  public String getKEY() {
    return KEY;
  }

  public void setKEY(String KEY) {
    this.KEY = KEY;
  }

  public Long getEXPIRATION() {
    return EXPIRATION;
  }

  public void setEXPIRATION(Long EXPIRATION) {
    this.EXPIRATION = EXPIRATION;
  }
}
