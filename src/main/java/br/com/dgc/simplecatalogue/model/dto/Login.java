package br.com.dgc.simplecatalogue.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Represents the attempt of login from user. Must be passed on requisition to get Jwt token.
 *
 * @see br.com.dgc.simplecatalogue.controller.LoginController
 * @see br.com.dgc.simplecatalogue.security.JwtObject
 * @see br.com.dgc.simplecatalogue.security.JwtCreator
 */
public class Login {
  @NotNull private String username;
  @NotNull private String password;

  public Login() {}

  public Login(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
