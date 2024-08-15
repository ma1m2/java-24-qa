package manager;

import org.openqa.selenium.By;

public class LoginHelper {

  private final AppManager app;

  public LoginHelper(AppManager appManager) {
    this.app = appManager;
  }

  void login(String user, String password) {
    app.driver.findElement(By.name("user")).sendKeys(user);
    app.driver.findElement(By.name("pass")).sendKeys(password);
    app.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
  }
}
