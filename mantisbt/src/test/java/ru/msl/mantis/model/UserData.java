package ru.msl.mantis.model;

public record UserData(Long id, String username, String realName, String password, String email) {

  public UserData() {
    this(0L, "", "", "", "");
  }

  public UserData withUsername(String username) {
    return new UserData(this.id,username, this.realName, this.password, this.email);
  }

  public UserData withRealName(String realName) {
    return new UserData(this.id, this.username, realName, this.password, this.email);
  }

  public UserData withPassword(String password) {
    return new UserData(this.id, this.username, this.realName, password, this.email);
  }

  public UserData withEmail(String email) {
    return new UserData(this.id, this.username, this.realName, this.password, email);
  }
}
