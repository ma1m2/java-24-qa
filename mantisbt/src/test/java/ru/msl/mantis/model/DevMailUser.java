package ru.msl.mantis.model;

public record DevMailUser(String name, String token) {

  public DevMailUser() {
    this("", "");
  }

  public DevMailUser withName(String name) {
    return new DevMailUser(name, this.token);
  }

  public DevMailUser withToken(String token) {
    return new DevMailUser(this.name, token);
  }
}
