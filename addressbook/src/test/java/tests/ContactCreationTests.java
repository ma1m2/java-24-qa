package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void canCreateContactWithPhoto() {
    app.contact().createContact(new ContactData()
            .withFirstName(randomString(10))
            .withLastName(randomString(10))
            .withPhoto(randomFile("src/test/resources/images")));
  }

  public static List<ContactData> contactProviderWithNames() {
    var result = new ArrayList<ContactData>();
    for (int i = 0; i < 2; i++) {
      result.add(new ContactData()
              .withFirstName(randomString(i + 5))
              .withLastName(randomString(i + 5)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProviderWithNames")
  public void canCreateContactWithNames(ContactData contact) {
    var oldContacts = app.contact().getLictNames();
    app.contact().createContact(contact);
    var newContacts = app.contact().getLictNames();
    newContacts.sort(app.contact().compareById());
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(newContacts.get(newContacts.size() - 1));
    expectedList.sort(app.contact().compareById());
    Assertions.assertEquals(expectedList, newContacts);
  }


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
