package ru.msl.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

  //video 8.6
  @Test
  public void canDrainInbox() {
    app.mail().drain("user1@localhost", "password");
  }

  //video 8.6
  @Test
  public void waitForEmail() {
    var messages = app.mail().receive("user1@localhost",
            "password", Duration.ofSeconds(10));
    Assertions.assertEquals(1, messages.size());
    System.out.println(messages);
  }

  //video 8.6
  @Test
  public void canExtractUrl() {
    var messages = app.mail().receive("user1@localhost",
            "password", Duration.ofSeconds(10));
    var test = messages.get(0).content();
    var pattern = Pattern.compile("http://\\S+");
    var matcher = pattern.matcher(test);
    if(matcher.find()) {
      var url = test.substring(matcher.start(), matcher.end());
      System.out.println(url);
    }
  }

  //video 8.5
  @Test
  public void canReceiveEmail() {
    var messages = app.mail().receive("user1@localhost", "password");
    Assertions.assertEquals(1, messages.size());
    System.out.println(messages);
  }
}
