package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void canCreateContactWithFirstName() {
    app.contact().createContact(new ContactData().withFirstName("Newfirstname"));
  }

}
