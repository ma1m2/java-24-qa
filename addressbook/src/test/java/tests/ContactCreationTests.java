package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void canCreateContactWithFirstName() {
    app.contact().createContact(new ContactData().withFirstName("Newfirstname"));
  }

  @Test
  public void canCreateContactWithFieldsFromHomePage() {
    app.contact().createContact(new ContactData().withFirstName("Newfirstname")
            .withLastName("newLastName").withAddress("newAddress")
            .withEmail("newEmail").withMobile("123456789"));
  }

}
