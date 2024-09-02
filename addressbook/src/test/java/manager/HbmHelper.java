package manager;

import hbm.GroupRecord;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HbmHelper extends HelperBase{
  private SessionFactory sessionFactory;

  public HbmHelper(AppManager app) {
    super(app);
    sessionFactory = new Configuration()
                    .addAnnotatedClass(GroupRecord.class)
                    //.addAnnotatedClass(Author.class)
                    .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
                    .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                    .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                    .buildSessionFactory();
  }

  public static List<GroupData> convertListFromRecords(List<GroupRecord> records) {
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
    if ("".equals(id)){
      id = "0";
    }
    return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
  }

  public List<GroupData> getGroupList() {
    return convertListFromRecords(sessionFactory.fromSession(session -> {
      return session.createQuery("from GroupRecord", GroupRecord.class).list();
    }));
  }

  public void verifyOrCreateAvailableGroupHbm() {
    if(getGroupCount() == 0) {
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

}
