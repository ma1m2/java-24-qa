package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class AppManager {
  protected WebDriver driver;
  private LoginHelper session;
  private GroupHelper group;
  private ContactHelper contact;
  private Properties prop;

  public void init(String browser, Properties prop) {
    this.prop = prop;
    if (driver == null) {
      if("chrome".equals(browser)) {
        driver = new ChromeDriver();
      } else if ("firefox".equals(browser)) {
        driver = new FirefoxDriver();
      }else {
        throw new IllegalArgumentException(String.format("Unrecognized browser: %s", browser));
      }
      Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
      driver.get(prop.getProperty("web.baseUrl"));
      driver.manage().window().setSize(new Dimension(1264, 964));
      session().login(prop.getProperty("web.username"), prop.getProperty("web.password"));
    }
  }

  public LoginHelper session() {
    if (session == null) {
      session = new LoginHelper(this);
    }
    return session;
  }

  public GroupHelper group() {
    if (group == null) {
      group = new GroupHelper(this);
    }
    return group;
  }

  public ContactHelper contact() {
    if (contact == null) {
      contact = new ContactHelper(this);
    }
    return contact;
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
