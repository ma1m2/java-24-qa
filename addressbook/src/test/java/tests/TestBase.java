package tests;

import manager.AppManager;
import org.junit.jupiter.api.BeforeEach;

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

  /*  @AfterEach
  public void tearDown() {
    //driver.findElement(By.linkText("Logout")).click();
    //driver.quit();
  }*/
}
