package ru.msl.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.msl.mantis.common.Util;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {
  //Hw-18
  @Test
  public void canRegisterUser() throws InterruptedException {
    //var username = Util.randomString(6);
    var username = "user1";
    var email = String.format("%s@localhost", username);
    //creat user(email) on mail server (JamesCliHelper)
    app.james().addUser(email, "password");
    //------Assertions.assertTrue(app.james().listUsers().contains(email));
    //open browser and fill sing up form and submit (browser)
    app.user().signUp(username, email);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.canExtractUrl(messages);
    //open browser and login using confirmation link (browser)
    app.driver().navigate().to(url);
    Thread.sleep(2000);
    //check that user is logged in (HttpSessionHelper)

  }

  @Test
  public void canCheckListUsers() {
    var email = app.james().listUsers();
  }
}
