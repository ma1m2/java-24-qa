package ru.msl.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase{

  public UserHelper(AppManager app) {
    super(app);
  }

  public void signUp(String username, String email) throws InterruptedException {
    openLoginPage();
    openSignUpPage();
    fillRegistrationForm(username, email);
    submitRegistration();
    proceedRegistration();
  }

  private void proceedRegistration() throws InterruptedException {
    Thread.sleep(1000);
    click(By.linkText("Proceed"));
  }

  private void submitRegistration(){
    click(By.xpath("//input[@value='Signup']"));//input[@type='submit']
    //click(By.cssSelector("input.width-40.btn"));
    //app.driver().findElement(By.cssSelector("input.width-40.btn")).click();
  }

  private void fillRegistrationForm(String username, String email) {
    type(By.id("username"), username);
    type(By.name("email"), email);
  }

  private void openSignUpPage() {
    click(By.linkText("Signup for a new account"));
  }

  private void openLoginPage() {
    app.driver().get(app.prop("web.baseUrl") + "login_page.php");
  }

}
