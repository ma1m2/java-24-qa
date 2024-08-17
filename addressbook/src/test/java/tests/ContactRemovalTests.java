package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
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
