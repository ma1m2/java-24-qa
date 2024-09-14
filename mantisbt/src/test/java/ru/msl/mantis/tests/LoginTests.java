package ru.msl.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {
  @Test
  public void canLogin() {
    app.session().login("administrator", "root");
    Assertions.assertTrue(app.session().isLoggedIn());
  }

  //video 8.3
  @Test
  public void canLoginHttp() {
    app.http().login("administrator", "root");
    Assertions.assertTrue(app.http().isLoggedIn());
  }
}
