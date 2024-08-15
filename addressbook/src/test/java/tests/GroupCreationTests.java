package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroup(){
    app.openGroupPage();
    app.createGroup(new GroupData("group name", "group header", "group footer"));
  }

  @Test
  public void canCreateGroupWithEmptyFields() {
    app.openGroupPage();
    app.createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnly() {
    app.openGroupPage();
    app.createGroup(new GroupData().withName("some group"));
  }

}
