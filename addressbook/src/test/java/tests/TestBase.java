package tests;

import manager.AppManager;
import model.GroupData;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Comparator;
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

  public static String randomFile(String dir) {
    var fileNames = new File(dir).list();
    var rnd = new Random();
    var index = rnd.nextInt(fileNames.length);
    return Paths.get(dir, fileNames[index]).toString();
  }

  public static String randomString(int length) {
    var rnd = new Random();
    var result = "";
    for (int i = 0; i < length; i++) {
      result = result + (char)('a' + rnd.nextInt(26));
    }
    return result;
  }

  public static String randomNumber(int length) {
    var rnd = new Random();
    var result = "";
    for (int i = 0; i < length; i++) {
      result = result + (char)('0' + rnd.nextInt(10));
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
