package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

  public void modifyContact(ContactData contact, ContactData modifiedContact) {
    openHomePage();
    selectContactById(contact);
    initContactModification(contact);
    fillContactForm(modifiedContact);
    submitContactUpdate();
    returnToHomePage();
  }

  public void removeContactById(ContactData contact) {
    openHomePage();
    selectContactById(contact);
    removeSelectedContact();
  }

  public void removeAnyContact() {
    openHomePage();
    selectAnyContact();
    removeSelectedContact();
  }

  public void removeAllContacts() {
    openHomePage();
    selectAllContacts();
    removeSelectedContacts();
  }

  private void initContactModification(ContactData contact) {
    var xpath = String.format("//tr[@name=\"entry\"]/td/input[@id='%s']/../..//img[@title='Edit']", contact.id());
    click(By.xpath(xpath));
  }

  private void submitContactUpdate() {
    click(By.cssSelector("input[value=Update]"));
  }

  private void selectAllContacts() {
    var checkBoxes = app.driver.findElements(By.name("selected[]"));
    for (var checkbox : checkBoxes) {
      checkbox.click();
    }
  }

  private void removeSelectedContacts() {
    removeSelectedContact();
  }

  private void removeSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  private void selectContactById(ContactData contact) {
    var css = String.format("tr[name=entry] input[id='%s']", contact.id());
    click(By.cssSelector(css));
  }

  private void selectAnyContact() {
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

  public List<ContactData> getLictNames() {
    openHomePage();
    var contacts = new ArrayList<ContactData>();
    var rows = app.driver.findElements(By.cssSelector("tr[name=entry]"));
    for (var row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("value");
      var lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      var firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
      System.out.println("Id: " + id + " LastName: " + lastName + " FirstName: " + firstName);
    }
    return contacts;
  }

  public Comparator<ContactData> compareById() {
    return (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
  }
}
