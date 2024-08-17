package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

  @Test
  public void canRemoveGroup() {
    app.group().verifyOrCreateAvailableGroup();
    int groupCount = app.group().getCount();
    app.group().removeGroup();
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount - 1, newGroupCount);
  }


  @Test
  public void canRemoveAllGroupsAtOnce() {
    app.group().verifyOrCreateAvailableGroup();
    app.group().removeAllGroup();
    Assertions.assertEquals(0, app.group().getCount());
  }

}
