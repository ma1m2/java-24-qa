package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Util;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {
  //===video 6.4===
  @Test
  public void canCreateContactInGroup() {
    var contact = new ContactData()
            .withFirstName(Util.randomString(10))
            .withLastName(Util.randomString(10))
            .withAddress(Util.randomString(10));
    app.hbm().verifyOrCreateAvailableGroupHbm();
    var group = app.hbm().getGroupList().get(0);
    var oldRelated = app.hbm().getContactsInGroup(group);

    app.contact().create(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);
    System.out.println(oldRelated.size() + " " + newRelated.size());
    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    //=====compare lists of contacts in group=============
    newRelated.sort(app.contact().compareById());
    var expectedList = new ArrayList<>(oldRelated);
    expectedList.add(newRelated.get(newRelated.size() - 1));
    expectedList.sort(app.contact().compareById());
    Assertions.assertEquals(expectedList, newRelated);
  }
  //============================================
  public static List<ContactData> contactProviderFromJson() throws IOException {
    var result = new ArrayList<ContactData>();
    var mapper = new ObjectMapper();
    var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProviderFromJson")
  public void canCreateContactFromJson(ContactData contact) {
    var oldContacts = app.contact().getLictNames();
    app.contact().create(contact);
    var newContacts = app.contact().getLictNames();
    newContacts.sort(app.contact().compareById());
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(newContacts.get(newContacts.size() - 1));
    expectedList.sort(app.contact().compareById());
    Assertions.assertEquals(expectedList, newContacts);
  }

  @Test
  public void canCreateContactWithPhoto() {
    app.contact().create(new ContactData()
            .withFirstName(Util.randomString(10))
            .withLastName(Util.randomString(10))
            .withPhoto(Util.randomFile("src/test/resources/images")));
  }

  public static List<ContactData> contactProviderWithNames() {
    var result = new ArrayList<ContactData>();
    for (int i = 0; i < 2; i++) {
      result.add(new ContactData()
              .withFirstName(Util.randomString(i * 5))
              .withLastName(Util.randomString(i * 5)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProviderWithNames")
  public void canCreateContactWithNames(ContactData contact) {
    var oldContacts = app.contact().getLictNames();
    app.contact().create(contact);
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
      result.add(new ContactData().withFirstName(Util.randomString(i + 5))
              .withLastName(Util.randomString(i + 5))
              .withAddress(Util.randomString(i + 5) + ", " + Util.randomNumber(2) +
                      ", " + Util.randomString(i + 5) + ", " + Util.randomNumber(3))
              .withEmail(Util.randomString(i + 5) + "@mail.ru")
              .withMobile(Util.randomNumber(10)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContact(ContactData contact) {
    int contactCount = app.contact().getCount();
    app.contact().create(contact);
    int newContactCount = app.contact().getCount();
    System.out.println(contactCount + " " + newContactCount);
    Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void canCreateContactWithFirstName () {
      app.contact().create(new ContactData().withFirstName("Newfirstname"));
    }

/*    @Test
    public void canCreateContactWithFieldsFromHomePage () {
      app.contact().createContact(new ContactData().withFirstName("Newfirstname")
              .withLastName("newLastName").withAddress("newAddress")
              .withEmail("newEmail").withMobile("123456789"));
    }*/

  }
