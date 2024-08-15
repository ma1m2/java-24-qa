package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
  private final AppManager app;

  public GroupHelper(AppManager app) {
    this.app = app;
  }

  public void removeGroup() {
    openGroupPage();
    app.driver.findElement(By.name("selected[]")).click();
    app.driver.findElement(By.name("delete")).click();
    app.driver.findElement(By.linkText("groups")).click();
  }

  public void openGroupPage() {
    if (!app.isElementPresent(By.name("new"))) {
      app.driver.findElement(By.linkText("groups")).click();
    }
  }

  public void createGroup(GroupData group) {
    openGroupPage();
    app.driver.findElement(By.name("new")).click();
    app.driver.findElement(By.name("group_name")).click();
    app.driver.findElement(By.name("group_name")).sendKeys(group.name());
    app.driver.findElement(By.name("group_header")).click();
    app.driver.findElement(By.name("group_header")).sendKeys(group.header());
    app.driver.findElement(By.name("group_footer")).click();
    app.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
    app.driver.findElement(By.name("submit")).click();
    app.driver.findElement(By.linkText("group page")).click();
  }

  public boolean isGroupPresent() {
    openGroupPage();
    return app.isElementPresent(By.name("selected[]"));
  }
}
