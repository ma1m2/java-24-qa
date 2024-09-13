package ru.msl.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.msl.mantis.manager.AppManager;

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
}
