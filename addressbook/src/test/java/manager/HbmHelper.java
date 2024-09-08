package manager;

import hbm.ContactRecord;
import hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HbmHelper extends HelperBase {
  private SessionFactory sessionFactory;

  public HbmHelper(AppManager app) {
    super(app);
    sessionFactory = new Configuration()
            .addAnnotatedClass(GroupRecord.class)
            .addAnnotatedClass(ContactRecord.class)
/*            .setProperty(AvailableSettings.URL,
                    "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
            .setProperty(AvailableSettings.USER, "root")
            .setProperty(AvailableSettings.PASS, "")*/
            .setProperty(AvailableSettings.JAKARTA_JDBC_URL,
                    "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
            .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
            .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
            .buildSessionFactory();
  }

  //video 7.3
  public static List<GroupData> convertGroupList(List<GroupRecord> records) {
    return records.stream().map(HbmHelper::convert).collect(Collectors.toList());
  }

  public static List<GroupData> convertGroupListOld(List<GroupRecord> records) {
    List<GroupData> groups = new ArrayList<>();
    for (GroupRecord record : records) {
      groups.add(convert(record));
    }
    return groups;
  }

  private static GroupData convert(GroupRecord record) {
    return new GroupData("" + record.id, record.name, record.header, record.footer);
  }

  private static GroupRecord convert(GroupData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
  }

  //video 7.3
  public static List<ContactData> convertContactList(List<ContactRecord> records) {
    return records.stream().map(HbmHelper::convert).collect(Collectors.toList());
  }

  public static List<ContactData> convertContactListOld(List<ContactRecord> records) {
    List<ContactData> contacts = new ArrayList<>();
    for (ContactRecord record : records) {
      contacts.add(convert(record));
    }
    return contacts;
  }

  private static ContactData convert(ContactRecord record) {
    return new ContactData().withId("" + record.id)
            .withFirstName(record.firstname)
            .withLastName(record.lastname)
            .withAddress(record.address)
            .withAddress2(record.address2)
            .withHome(record.home)
            .withMobile(record.mobile)
            .withWork(record.work)
            .withPhone2(record.phone2)
            .withEmail(record.email)
            .withEmail2(record.email2)
            .withEmail3(record.email3);
  }

  private static ContactRecord convert(ContactData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(),
            data.address(), data.address2(),
            data.home(), data.mobile(), data.work(), data.phone2(),
            data.email(), data.email2(), data.email3());
  }

  public List<GroupData> getGroupList() {
    return convertGroupList(sessionFactory.fromSession(session -> {
      return session.createQuery("from GroupRecord", GroupRecord.class).list();
    }));
  }

  public void verifyOrCreateAvailableGroupHbm() {
    if (getGroupCount() == 0) {
      createGroup(new GroupData("", "group name", "group header", "group footer"));
    }
  }

  public void createGroup(GroupData groupData) {
    sessionFactory.inSession(session -> {
      session.getTransaction().begin();
      session.persist(convert(groupData));
      session.getTransaction().commit();
    });

  }

  public long getGroupCount() {
    return sessionFactory.fromSession(session -> {
      return session.createQuery("select count(*) from GroupRecord", Long.class).getSingleResult();
    });
  }

  public List<ContactData> getContactsInGroup(GroupData group) {
    return sessionFactory.fromSession(session -> {
      return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
    });
  }

  public List<ContactData> getContactList() {
    return convertContactList(sessionFactory.fromSession(session -> {
      return session.createQuery("from ContactRecord", ContactRecord.class).list();
    }));
  }

  public void verifyOrCreateAvailableContact() {
    if (getContactCount() == 0) {
      createContact(new ContactData().withFirstName("first name").withLastName("last name"));
    }
  }

  private void createContact(ContactData contactData) {
    sessionFactory.inSession(session -> {
      session.getTransaction().begin();
      session.persist(convert(contactData));
      session.getTransaction().commit();
    });
  }

  private long getContactCount() {
    return sessionFactory.fromSession(session -> {
      return session.createQuery("select count(*) from ContactRecord", Long.class).getSingleResult();
    });
  }
}
