package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppManager {
  protected WebDriver driver;
  private LoginHelper session;
  private GroupHelper group;

  public void init() {
    if (driver == null) {
      driver = new ChromeDriver();
      Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
      driver.get("http://localhost/addressbook/");
      driver.manage().window().setSize(new Dimension(1264, 964));
      session().login("admin", "secret");
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

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
