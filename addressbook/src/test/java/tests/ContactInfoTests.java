package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.ContactData;

public class ContactInfoTests extends TestBase {

  //Hw-16 new
  @Test
  public void compareHomeAndEditPage() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var rmd = new Random();
    int index = rmd.nextInt(contacts.size());
    var rmdContact = contacts.get(index);
    var lastNameHomePage = app.contact().getLastNames().get(rmdContact.id());
    var firstNameHomePage = app.contact().getFirstNames().get(rmdContact.id());
    var emailsHomePage = app.contact().getEmails().get(rmdContact.id());
    var phonesHomePage = app.contact().getPhones().get(rmdContact.id());
    var addressesHomePage = app.contact().getAddresses().get(rmdContact.id());
    app.contact().openEditContactPage(rmdContact);
    var lastNamesEditPage = app.contact().getLastNameFromEditPage();
    var firstNamesEditPage = app.contact().getFirstNameFromEditPage();
    var emailsEditPage = app.contact().getEmailsFromEditPage();
    var phonesEditPage = app.contact().getPhonesFromEditPage();
    var addressesEditPage = app.contact().getAddressFromEditPage();

    System.out.println("Last name: " + lastNameHomePage + "===" + lastNamesEditPage);
    System.out.println("First name: " + firstNameHomePage + "===" + firstNamesEditPage);
    System.out.println("Emails: " + emailsHomePage + "===" + emailsEditPage);
    System.out.println("Phones: " + phonesHomePage + "===" + phonesEditPage);
    System.out.println("Addresses: " + addressesHomePage + "===" + addressesEditPage);
    Assertions.assertEquals(lastNameHomePage, lastNamesEditPage);
    Assertions.assertEquals(firstNameHomePage, firstNamesEditPage);
    Assertions.assertEquals(emailsHomePage, emailsEditPage);
    Assertions.assertEquals(phonesHomePage, phonesEditPage);
    Assertions.assertEquals(addressesHomePage, addressesEditPage);

  }

  //Hw - 16 bad
  @Test
  public void testCompareHomeAndEditPage() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var rmd = new Random();
    int index = rmd.nextInt(contacts.size());
    var contactHome = app.contact().getInfoFromHomePage(contacts.get(index));
    var contactEdit = app.contact().getInfoFromEditPage(contacts.get(index));
    Assertions.assertEquals(contactHome.toString(), contactEdit.toString());
  }

  @Test
  public void testEmailsCompareMaps() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, c -> {
      return Stream.of(c.email(), c.email2(), c.email3())
              .filter(s -> s != null && !"".equals(s))
              .collect(Collectors.joining("\n"));
    }));
    var emails = app.contact().getEmails();
    //System.out.println(emails + " ==================== " + expected);
    Assertions.assertEquals(expected, emails);
  }

  @Test
  public void testAddressesCompareMaps() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, c -> {
      return Stream.of(c.address(), c.address2())
              .filter(s -> s != null && !"".equals(s))
              .collect(Collectors.joining("\n"));
    }));
    var addresses = app.contact().getAddresses();
    Assertions.assertEquals(expected, addresses);
  }

  //video 7.6
  @Test
  public void testPhonesCompareMaps() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, c -> {
      return Stream.of(c.home(), c.mobile(), c.work(), c.phone2())
              .filter(s -> s != null && !"".equals(s))
              .collect(Collectors.joining("\n"));
    }));
    var phones = app.contact().getPhones();
    Assertions.assertEquals(expected, phones);
  }

  //video 7.6
  @Test
  public void testPhonesForAllContacts() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var phones = app.contact().getPhones();
    for (var contact : contacts) {
      var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
              .filter(s -> s != null && !"".equals(s))
              .collect(Collectors.joining("\n"));
      Assertions.assertEquals(expected, phones.get(contact.id()));
    }
  }

  @Test
  public void testPhones() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var contact = contacts.get(0);
    var phones = app.contact().getPhones(contact);
    var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"));
    Assertions.assertEquals(expected, phones);

  }
}
