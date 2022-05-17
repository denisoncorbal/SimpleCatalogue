package br.com.dgc.simplecatalogue.unitarytests.security;

import java.util.Date;
import java.util.List;

public class JWTObject {
  private String subject;
  private Date issuedAt;
  private Date expiration;
  private List<String> roles;

  public JWTObject() {
  }

  protected JWTObject(String subject, Date issuedAt, Date expiration, List<String> roles) {
    this.subject = subject;
    this.issuedAt = issuedAt;
    this.expiration = expiration;
    this.roles = roles;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Date getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(Date issuedAt) {
    this.issuedAt = issuedAt;
  }

  public Date getExpiration() {
    return expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public static JWTObjectBuilder builder(){
    return new JWTObjectBuilder();
  }

  public static class JWTObjectBuilder{
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;

    public JWTObjectBuilder subject(String subject){
      this.subject = subject;
      return this;
    }
    public JWTObjectBuilder issuedAt(Date issuedAt){
      this.issuedAt = issuedAt;
      return this;
    }
    public JWTObjectBuilder expiration(Date expiration){
      this.expiration = expiration;
      return this;
    }
    public JWTObjectBuilder roles(List<String> roles){
      this.roles = roles;
      return this;
    }

    public JWTObject build(){
      return new JWTObject(this.subject, this.issuedAt, this.expiration, this.roles);
    }
  }
}
