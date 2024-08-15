package manager;

import org.openqa.selenium.By;

public class LoginHelper  extends HelperBase{

  public LoginHelper(AppManager app) {
    super(app);
  }

  void login(String user, String password) {
    type(By.name("user"), user);
    type(By.name("pass"), password);
    click(By.xpath("//input[@value=\'Login\']"));
  }
}
