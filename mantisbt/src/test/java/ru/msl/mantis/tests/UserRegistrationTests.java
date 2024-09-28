package ru.msl.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msl.mantis.common.Util;
import ru.msl.mantis.model.DevMailUser;
import ru.msl.mantis.model.UserData;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {


  public static Stream<UserData> randomApiUser() {
    var password = "password";
    var username = Util.randomString(8);
    var email = String.format("%s@localhost", username);
    return Stream.of(new UserData().withUsername(username)
            .withRealName(username)
            .withPassword(password)
            .withEmail(email));
  }

  //Hw-19
  @ParameterizedTest
  @MethodSource("randomApiUser")
  public void canRegisterUserByRestMantis(UserData userData) throws InterruptedException {
    var password = userData.password();
    var username = userData.username();
    var email = userData.email();
    //creat user(email) on mail server (JamesApiHelper)
    app.jamesApi().addUser(email, password);
    //open browser and fill sing up form and submit (browser)
    app.rest().addUser(userData);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.canExtractUrl(messages);
    //open browser and login using confirmation link and edit account (browser)
    app.user().editAccount(url, username, password);
    //check that user is logged in (HttpSessionHelper)
    app.http().login(username, password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  //video 9.2
  public static Stream<String> randomUser() {
    return Stream.of(Util.randomString(8));
  }

  @ParameterizedTest
  @MethodSource("randomUser")
  public void canRegisterUserByRestJames(String username) throws InterruptedException {
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
