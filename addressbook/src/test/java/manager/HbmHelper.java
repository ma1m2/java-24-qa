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

  public List<GroupData> getGroupList() {
    return convertListFromRecords(sessionFactory.fromSession(session -> {
      return session.createQuery("from GroupRecord", GroupRecord.class).list();
    }));
  }
}
