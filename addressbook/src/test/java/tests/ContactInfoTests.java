package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

  @Test
  public void testPhones() {
    app.hbm().verifyOrCreateAvailableContact();
    var contacts = app.hbm().getContactList();
    var contact = contacts.get(0);
    var phones = app.contact().getPhones(contact);
    var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
            .filter(s -> s != null && ! "".equals(s))
            .collect(Collectors.joining("\n"));
    Assertions.assertEquals(expected, phones);

  }
}
