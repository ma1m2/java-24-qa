package ru.msl.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.msl.mantis.common.Util;

public class JamesTests extends TestBase {

  @Test
  public void canCreateUser() {
    app.james().addUser(String.format("%s@localhost", Util.randomString(5)), "password");
  }

  @Test
  public void canRemoveUser() {
    app.james().removeUser("user1@localhost");
  }

}
