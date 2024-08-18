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

  public void removeAllContacts() {
    openHomePage();
    selectAllContacts();
    removeSelectedContacts();
  }

  private void removeSelectedContacts() {
    removeSelectedContact();
  }

  private void selectAllContacts() {
    var checkBoxes = app.driver.findElements(By.name("selected[]"));
    for (var checkbox : checkBoxes) {
      checkbox.click();
    }
  }

  private void removeSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  private void selectContact() {
    click(By.name("selected[]"));
  }

  public boolean isContactPresent() {
    openHomePage();
    return app.isElementPresent(By.name("selected[]"));
  }
  private void openHomePage() {
    if (!app.isElementPresent(By.id("search_count"))) {
      click(By.linkText("home"));
    }
  }

  public String takeNumberOfContact(){
    openHomePage();
    return app.driver.findElement(By.id("search_count")).getText();
  }

  public void modifyContact(ContactData contact) {
    openHomePage();
    selectContact();
    //initContactModification();
    fillContactForm(contact);
    //submitContactModification();
    returnToHomePage();
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

  public void verifyOrCreateAvailableContact() {
    if(!app.contact().isContactPresent()) {
      app.contact().createContact(new ContactData().withFirstName("firstname")
              .withLastName("LastName").withAddress("Address")
              .withEmail("Email").withMobile("12345"));
    }
  }

  public int getCount() {
    return app.driver.findElements(By.name("selected[]")).size();
  }
}
