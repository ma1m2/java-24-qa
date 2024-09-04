package tests;

import manager.AppManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
  protected static AppManager app;
  //protected AppManager app;

  @BeforeEach
  public void setUp() throws IOException {
    if(app== null) {
      var prop = new Properties();
      prop.load(new FileReader(System.getProperty("target", "local.properties")));
      app = new AppManager();
      app.init(System.getProperty("browser", "firefox"), prop);
    }
  }

  @AfterEach
  public void checkConsistancyDB() {
    app.jdbc().checkConsistancyDB();
  }

  /*  @AfterEach
  public void tearDown() {
    //driver.findElement(By.linkText("Logout")).click();
    //driver.quit();
  }*/
}
