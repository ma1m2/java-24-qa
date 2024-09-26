package ru.msl.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msl.mantis.common.Util;
import ru.msl.mantis.model.DevMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

  //video 9.2
  public static Stream<String> randomUser() {
    return Stream.of(Util.randomString(8));
  }

  @ParameterizedTest
  @MethodSource("randomUser")
  public void canRegisterUserApi(String username) throws InterruptedException {
    var password = "password";
    var email = String.format("%s@localhost", username);
    //creat user(email) on mail server (JamesApiHelper)
    app.jamesApi().addUser(email, password);
    System.out.println(app.jamesApi().listUsers());
    //open browser and fill sing up form and submit (browser)
    app.user().signUp(username, email);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.canExtractUrl(messages);
    //open browser and login using confirmation link and edit account (browser)
    app.user().editAccount(url, username, "password");
    //check that user is logged in (HttpSessionHelper)
    app.http().login(username, "password");
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  //Hw-18
  @Test
  public void canRegisterUser() throws InterruptedException {
    var username = Util.randomString(7);
    //var username = "user1";
    var email = String.format("%s@localhost", username);
    //creat user(email) on mail server (JamesCliHelper)
    app.jamesCli().addUser(email, "password");
    //open browser and fill sing up form and submit (browser)
    app.user().signUp(username, email);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.canExtractUrl(messages);
    //open browser and login using confirmation link and edit account (browser)
    app.user().editAccount(url, username, "password");
    //check that user is logged in (HttpSessionHelper)
    app.http().login(username, "password");
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  //when I remove user from James server, INBOX is saved in DB
  @Test
  public void haveMantisBugWithConfirmationLink() throws InterruptedException {
    var username = "user1";
    var email = String.format("%s@localhost", username);
    //creat user(email) on mail server (JamesCliHelper)
    app.jamesCli().addUser(email, "password");
    //open browser and fill sing up form and submit (browser)
    app.user().signUp(username, email);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
    System.out.println("How many messages in INBOX: " + messages.size());
    System.out.println(messages);

  }

  @Test
  public void canCheckListUsers() {
    var email = app.jamesCli().listUsers();
  }
}
