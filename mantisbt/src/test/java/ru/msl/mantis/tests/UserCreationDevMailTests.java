package ru.msl.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.msl.mantis.common.Util;
import ru.msl.mantis.model.DevMailUser;

import java.time.Duration;

public class UserCreationDevMailTests extends TestBase {
  DevMailUser user;

  //video 9.3; video 9.4 don't work. Mantis doesn't want to send email to DeveloperMail.com
  @Test
  public void canRegisterUserDevMail() throws InterruptedException {
    var password = "password";
    user = app.devMail().addUser();
    var email = String.format("%s@developermail.com", user.name());
    System.out.println(user);
    System.out.println(email);
    //open browser and fill sing up form and submit (browser)
    //app.user().signUp(user.name(), email);
    app.user().startCreation(user.name(), email);
    //wait for email (DevMailHelper)
    var message = app.devMail().receive(user, Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.extractUrl(message);
    //open browser and login using confirmation link and edit account (browser)
    app.user().editAccount(url, user.name(), password);
    //check that user is logged in (HttpSessionHelper)
    app.http().login(user.name(), password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  @Test
  public void canReceiveDevMail(){
    user = new DevMailUser("v-ehjdp6", "80FCED54015726551BAE71C79106A7FB048C9227");
    var message = app.devMail().receive(user, Duration.ofSeconds(10));
    var url = Util.extractUrl(message);//not work
    //System.out.println(message);
    System.out.println(url);
  }

  @AfterEach
  public void deleteMailUser(){
    app.devMail().deleteUser(user);
  }

}
