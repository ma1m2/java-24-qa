package ru.msl.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserRegistrationTests extends TestBase {
  //video 8.6
  @ParameterizedTest
  @ValueSource(strings = {"user1"})
  public void canRegisterUser(String username) {
    var email = String.format("%s@localhost", username);
    //creat user(email) on mail server (JamesCliHelper)
    //open browser and fill sing up form and submit (browser)
    //wait for email (MailHelper)
    //extract confirmation link
    //open browser and login using confirmation link (browser)
    //check that user is logged in (HttpSessionHelper)

  }
}
