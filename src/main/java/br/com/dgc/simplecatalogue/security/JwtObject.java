package br.com.dgc.simplecatalogue.security;

import java.util.Date;
import java.util.List;

/**
 * Class representing the payload of Jwt token.
 *
 * @since 1.0
 */
public class JwtObject {
  private String subject;
  private Date issuedAt;
  private Date expiration;
  private List<String> roles;

  public JwtObject() {}

  protected JwtObject(String subject, Date issuedAt, Date expiration, List<String> roles) {
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

  public static JwtObjectBuilder builder() {
    return new JwtObjectBuilder();
  }

  /**
   * Class representing Builder pattern for a JwtObject.
   *
   * @see JwtObject
   * @since 1.0
   */
  public static class JwtObjectBuilder {
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;

    public JwtObjectBuilder subject(String subject) {
      this.subject = subject;
      return this;
    }

    public JwtObjectBuilder issuedAt(Date issuedAt) {
      this.issuedAt = issuedAt;
      return this;
    }

    public JwtObjectBuilder expiration(Date expiration) {
      this.expiration = expiration;
      return this;
    }

    public JwtObjectBuilder roles(List<String> roles) {
      this.roles = roles;
      return this;
    }

    public JwtObject build() {
      return new JwtObject(this.subject, this.issuedAt, this.expiration, this.roles);
    }
  }
}
