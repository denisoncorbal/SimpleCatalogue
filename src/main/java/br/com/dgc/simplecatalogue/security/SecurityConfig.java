package br.com.dgc.simplecatalogue.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig{
  private String PREFIX;
  private String KEY;
  private Long EXPIRATION;

  public SecurityConfig(){

  }

  public SecurityConfig(String prefix, String key, Long expiration){
    this.PREFIX = prefix;
    this.KEY = key;
    this.EXPIRATION = expiration;
  }

  public String getPREFIX() {
    return PREFIX;
  }

  public void setPREFIX(String PREFIX) {
    this.PREFIX = PREFIX;
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
