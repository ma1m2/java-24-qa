package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactModificationTests extends TestBase {
  //HW-14
  @Test
  public void canModifyContactHbm() {
    app.hbm().verifyOrCreateAvailableContact();
    var oldContacts = app.hbm().getContactList();
    var rmd = new Random();
    int index = rmd.nextInt(oldContacts.size());
    var testData = new ContactData().withFirstName("modified contact").withLastName("modified contact");
    app.contact().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    newContacts.sort(app.contact().compareById());
    expectedList.sort(app.contact().compareById());
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  public void canModifyContact() {
    app.contact().verifyOrCreateAvailableContact();
    var oldContacts = app.contact().getLictNames();
    var rmd = new Random();
    int index = rmd.nextInt(oldContacts.size());
    var testData = new ContactData().withFirstName("modified contact").withLastName("modified contact");
    app.contact().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.contact().getLictNames();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    newContacts.sort(app.contact().compareById());
    expectedList.sort(app.contact().compareById());
    Assertions.assertEquals(newContacts, expectedList);
  }
}
