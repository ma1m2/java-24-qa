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

  public static List<GroupData> convertGroupListFromRecords(List<GroupRecord> records) {
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

  public static List<ContactData> convertContactListFromRecords(List<ContactRecord> records) {
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
            .withAddress(record.address);
  }

  private static ContactRecord convert(ContactData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address());
  }

  public List<GroupData> getGroupList() {
    return convertGroupListFromRecords(sessionFactory.fromSession(session -> {
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
      return convertContactListFromRecords(session.get(GroupRecord.class, group.id()).contacts);
    });
  }

}
