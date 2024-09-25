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

  public void editAccount(String url, String username, String password) throws InterruptedException {
    clickConfirmLink(url);
    fillEditAccountForm(username, password);
    submitUpdate();
  }

  private void submitUpdate() {
    click(By.cssSelector("span.bigger-110"));
  }

  private void fillEditAccountForm(String username, String password) {
    type(By.name("realname"), username);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
  }

  private void clickConfirmLink(String url) {
    app.driver().get(url);
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
