package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

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
  public void canRemoveContact() {
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
