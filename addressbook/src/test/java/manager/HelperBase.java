package manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {

  protected final AppManager app; //manager

  public HelperBase(AppManager app) {
    this.app = app;
  }

  protected void click(By locator) {
    app.driver.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    app.driver.findElement(locator).clear();
    app.driver.findElement(locator).sendKeys(text);
  }

  protected void attachFile(By locator, String photo) {
    app.driver.findElement(locator).sendKeys(Paths.get(photo).toAbsolutePath().toString());
  }
}
