package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

  @Test
  public void canRemoveContact() {
    app.contact().verifyOrCreateAvailableContact();
    var oldContacts = app.contact().getLictNames();
    var rnd = new Random();
    int index = rnd.nextInt(oldContacts.size());
    app.contact().removeContact(oldContacts.get(index));
    var newContacts = app.contact().getLictNames();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  public void canRemoveAllCsAtOnce() {
    System.out.println("Before " + app.contact().getCount());
    app.contact().verifyOrCreateAvailableContact();
    System.out.println("After creation " + app.contact().getCount());
    app.contact().removeAllContacts();
    System.out.println("After " + app.contact().getCount());
    Assertions.assertEquals(0, app.contact().getCount());
  }

  @Test
  public void canRemoveContactOld() {
    app.contact().verifyOrCreateAvailableContact();
    app.contact().removeContact();
  }

  @Test
  public void chechNumberOfContactsAfterRemoval(){
    System.out.println("Before " + app.contact().takeNumberOfContact());
    app.contact().verifyOrCreateAvailableContact();
    app.contact().removeContact();
    System.out.println("After " + app.contact().takeNumberOfContact());
  }
}
