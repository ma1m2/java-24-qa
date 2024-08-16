package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

  public ContactHelper(AppManager app) {
    super(app);
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData);
    submitContactCreation();
    returnToHomePage();
  }

  public void removeContact() {
    openHomePage();
    selectContact();
    removeSelectedContact();
  }

  private void removeSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  private void selectContact() {
    click(By.name("selected[]"));
  }

  public boolean isGroupPresent() {
    openHomePage();
    return app.isElementPresent(By.name("selected[]"));
  }
  private void openHomePage() {
    if (!app.isElementPresent(By.id("search_count"))) {
      click(By.linkText("home"));
    }
  }

  private void returnToHomePage() {
    click(By.linkText("home page"));
  }

  private void submitContactCreation() {
    click(By.name("submit"));
  }

  private void fillContactForm(ContactData contact) {
    type(By.name("firstname"), contact.firstname());
    type(By.name("lastname"), contact.lastname());
    type(By.name("address"), contact.address());
    type(By.name("home"), contact.home());
    type(By.name("mobile"), contact.mobile());
    type(By.name("work"), contact.work());
    type(By.name("email"), contact.email());
    type(By.name("email2"), contact.email2());
    type(By.name("email3"), contact.email3());
    if(!contact.photo().equals("")) {
      attachFile(By.name("photo"), contact.photo());
    }
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }
}
