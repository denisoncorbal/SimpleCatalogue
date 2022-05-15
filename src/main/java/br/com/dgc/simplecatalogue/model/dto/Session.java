package br.com.dgc.simplecatalogue.model.dto;

import javax.validation.constraints.NotNull;

public class Session {
  @NotNull
  private String login;
  @NotNull
  private String token;

  public Session(){
    this.login = "";
    this.token = "";
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
