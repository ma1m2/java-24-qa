package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();
    for (int i = 0; i < 5; i++) {
      result.add(new ContactData().withFirstName(randomString(i + 5))
              .withLastName(randomString(i + 5))
              .withAddress(randomString(i + 5) + ", " + randomNumber(2) +
                      ", " + randomString(i + 5) + ", " + randomNumber(3))
              .withEmail(randomString(i + 5) + "@mail.ru")
              .withMobile(randomNumber(10)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContact(ContactData contact) {
    int contactCount = app.contact().getCount();
    app.contact().createContact(contact);
    int newContactCount = app.contact().getCount();
    System.out.println(contactCount + " " + newContactCount);
    Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void canCreateContactWithFirstName () {
      app.contact().createContact(new ContactData().withFirstName("Newfirstname"));
    }

/*    @Test
    public void canCreateContactWithFieldsFromHomePage () {
      app.contact().createContact(new ContactData().withFirstName("Newfirstname")
              .withLastName("newLastName").withAddress("newAddress")
              .withEmail("newEmail").withMobile("123456789"));
    }*/

  }
