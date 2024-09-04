package manager;

import model.GroupData;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{

  public JdbcHelper(AppManager app) {
    super(app);
  }

  public List<GroupData> getGroupList() {
    var groups = new ArrayList<GroupData>();
    try(var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
        var st = conn.createStatement();
        var rs = st.executeQuery("SELECT group_id, group_name, group_header, group_footer from group_list"))
    {
      while(rs.next()) {
        groups.add(new GroupData()
                .withId(rs.getString("group_id"))
                .withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header"))
                .withFooter(rs.getString("group_footer")));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return groups;
  }

  public List<GroupData> getListWithIdAndName() {
    var groups = new ArrayList<GroupData>();
    try(var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
        var st = conn.createStatement();
        var rs = st.executeQuery("SELECT group_id, group_name from group_list"))
    {
      while(rs.next()) {
        groups.add(new GroupData()
                .withId(rs.getString("group_id"))
                .withName(rs.getString("group_name")));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return groups;
  }


  public void checkConsistancyDB() {
    try(var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
        var st = conn.createStatement();
        var rs = st.executeQuery("SELECT * FROM `address_in_groups` ag LEFT JOIN `addressbook` ab ON ag.id = ab.id WHERE ab.id IS NULL"))
    {
      if(rs.next()) {
        System.out.println(rs.getString("ag.id") + " "
                + rs.getString("ag.group_id") + " " + rs.getString("ab.id"));
        throw new IllegalStateException("Inconsistency in DB, it's corrupted");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
