package ru.msl.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.msl.mantis.model.DevMailUser;

public class UserCreationTests extends TestBase {
  DevMailUser user;

  //video 9.3
  @Test
  public void canRegisterUserDevMail() throws InterruptedException {
    var password = "password";
    user = app.devMail().addUser();
    var email = String.format("%s@developermail.com", user.name());

/*    //creat user(email) on mail server (JamesApiHelper)
    app.jamesApi().addUser(email, password);
    System.out.println(app.jamesApi().listUsers());
    //open browser and fill sing up form and submit (browser)
    app.user().signUp(user.name(), email);
    //wait for email (MailHelper)
    var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
    //extract confirmation link
    var url = Util.canExtractUrl(messages);
    //open browser and login using confirmation link and edit account (browser)
    app.user().editAccount(url, user.name(), "password");
    //check that user is logged in (HttpSessionHelper)
    app.http().login(user.name(), "password");
    Assertions.assertTrue(app.http().isLoggedIn());*/
  }

  @AfterEach
  public void deleteMailUser(){
    app.devMail().deleteUser(user);
  }

}
