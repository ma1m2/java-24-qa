package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroup(){
    int groupCount = app.group().getCount();
    app.group().createGroup(new GroupData("group name", "group header", "group footer"));
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  @Test
  public void canCreateGroupWithEmptyFields() {
    app.group().createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnly() {
    app.group().createGroup(new GroupData().withName("some group"));
  }

}
