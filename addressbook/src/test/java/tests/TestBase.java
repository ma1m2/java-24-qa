package tests;

import manager.AppManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
  protected static AppManager app;
  //protected AppManager app;

  @BeforeEach
  public void setUp() {
    if(app== null) {
      app = new AppManager();
    }
    app.init(System.getProperty("browser", "firefox"));
  }

  public String randomString(int length) {
    var rnd = new Random();
    var result = "";
    for (int i = 0; i < length; i++) {
      result = result + (char)('a' + rnd.nextInt(26));
    }
    return result;
  }

  public String randomStringGPT(int length) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append((char) ('a' + (int) (Math.random() * 26)));
    }
    return sb.toString();
  }

  /*  @AfterEach
  public void tearDown() {
    //driver.findElement(By.linkText("Logout")).click();
    //driver.quit();
  }*/
}
