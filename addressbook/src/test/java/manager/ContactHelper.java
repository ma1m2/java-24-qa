package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {

  public ContactHelper(AppManager app) {
    super(app);
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData);
    submitContactCreation();
    returnToHomePage();
  }

  public void create(ContactData contactData, GroupData group) {
    initContactCreation();
    fillContactForm(contactData);
    selectGroup(group);
    submitContactCreation();
    returnToHomePage();
  }

  private void selectGroup(GroupData group) {
    new Select(app.driver.findElement(By.name("new_group"))).selectByValue(group.id());

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

  public String takeNumberOfContact() {
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
    if (!contact.photo().equals("")) {
      attachFile(By.name("photo"), contact.photo());
    }
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void verifyOrCreateAvailableContact() {
    if (!app.contact().isContactPresent()) {
      app.contact().create(new ContactData().withFirstName("firstname")
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
      //System.out.println("Id: " + id + " LastName: " + lastName + " FirstName: " + firstName);
    }
    return contacts;
  }

  public Comparator<ContactData> compareById() {
    return (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
  }

  public String getPhones(ContactData contact) {
    return app.driver.findElement(By.xpath(
            String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
  }

  public Map<String,String> getPhones() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = app.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var phones = row.findElements(By.tagName("td")).get(5).getText();
      result.put(id, phones);
    }
    return result;
  }

  public Map<String,String> getAddresses() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = app.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var address = row.findElements(By.tagName("td")).get(3).getText();
      result.put(id, address);
    }
    return result;
  }

  public Map<String,String> getEmails() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = app.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var emails = row.findElements(By.tagName("td")).get(4).getText();
      result.put(id, emails);
    }
    return result;
  }

  public Map<String,String> getLastNames() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = app.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var lastNames = row.findElements(By.tagName("td")).get(1).getText();
      result.put(id, lastNames);
    }
    return result;
  }

  public Map<String,String> getFirstNames() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = app.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var lastNames = row.findElements(By.tagName("td")).get(2).getText();
      result.put(id, lastNames);
    }
    return result;
  }

  public StringBuffer getInfoFromHomePage(ContactData contact) {
    openHomePage();
    StringBuffer sb = new StringBuffer();
    List<WebElement> tds = selectRowById(contact).findElements(By.tagName("td"));
    for (WebElement td : tds) {
      if(td.getText() != null && ! td.getText().equals(""))
      sb.append(td.getText()).append("\n");
    }
    return sb;
  }

  public WebElement selectRowById(ContactData contact) {
    var xpath = String.format("//tr[@name='entry']//input[@id='%s']/../..", contact.id());
    WebElement row = app.driver.findElement(By.xpath(xpath));
    return row;
  }

  public StringBuffer getInfoFromEditPage(ContactData contact) {
    openEditContactPage(contact);
    StringBuffer sb = new StringBuffer();
    var lastname = app.driver.findElement(By.name("lastname")).getAttribute("value");
    var firstname = app.driver.findElement(By.name("firstname")).getAttribute("value");
    var address = app.driver.findElement(By.name("address")).getAttribute("value");
    var email = app.driver.findElement(By.name("email")).getAttribute("value");
    var email2 = app.driver.findElement(By.name("email2")).getAttribute("value");
    var email3 = app.driver.findElement(By.name("email3")).getAttribute("value");
    var home = app.driver.findElement(By.name("home")).getAttribute("value");
    var mobile = app.driver.findElement(By.name("mobile")).getAttribute("value");
    var work = app.driver.findElement(By.name("work")).getAttribute("value");
    if(lastname != null && ! lastname.equals("")) sb.append(lastname).append("\n");
    if(firstname != null && ! firstname.equals("")) sb.append(firstname).append("\n");
    if(address != null && ! address.equals("")) sb.append(address).append("\n");
    if(email != null && ! email.equals("")) sb.append(email).append("\n");
    if(email2 != null && ! email2.equals("")) sb.append(email2).append("\n");
    if(email3 != null && ! email3.equals("")) sb.append(email3).append("\n");
    if(home != null && ! home.equals("")) sb.append(home).append("\n");
    if(mobile != null && ! mobile.equals("")) sb.append(mobile).append("\n");
    if(work != null && ! work.equals("")) sb.append(work).append("\n");
    return sb;
  }

  public void openEditContactPage(ContactData contact) {
    selectRowById(contact).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).click();
  }

  public String getLastNameFromEditPage() {
    return app.driver.findElement(By.name("lastname")).getAttribute("value");
  }

  public String getFirstNameFromEditPage() {
    return app.driver.findElement(By.name("firstname")).getAttribute("value");
  }

  public String getAddressFromEditPage() {
    return app.driver.findElement(By.name("address")).getAttribute("value");
  }

  public String getPhonesFromEditPage() {
    StringBuffer sb = new StringBuffer();
    var home = app.driver.findElement(By.name("home")).getAttribute("value");
    var mobile = app.driver.findElement(By.name("mobile")).getAttribute("value");
    var work = app.driver.findElement(By.name("work")).getAttribute("value");
    if(home != null && ! home.equals("")) sb.append(home).append("\n");
    if(mobile != null && ! mobile.equals("")) sb.append(mobile).append("\n");
    if(work != null && ! work.equals("")) sb.append(work);
    return sb.toString();
  }

  public String getEmailsFromEditPage() {
    StringBuffer sb = new StringBuffer();
    var email = app.driver.findElement(By.name("email")).getAttribute("value");
    var email2 = app.driver.findElement(By.name("email2")).getAttribute("value");
    var email3 = app.driver.findElement(By.name("email3")).getAttribute("value");
    if(email != null && ! email.equals("")) sb.append(email).append("\n");
    if(email2 != null && ! email2.equals("")) sb.append(email2).append("\n");
    if(email3 != null && ! email3.equals("")) sb.append(email3);
    return sb.toString();
  }


}
