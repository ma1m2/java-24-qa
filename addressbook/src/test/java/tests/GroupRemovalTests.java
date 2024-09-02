package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {
  //video 6.3
  @Test
  public void canRemoveGroupHbm() {
    app.hbm().verifyOrCreateAvailableGroupHbm();
    List<GroupData> oldGroups = app.hbm().getGroupList();
    var rmd = new Random();
    int index = rmd.nextInt(oldGroups.size());
    app.group().removeGroup(oldGroups.get(index));
    List<GroupData> newGroups = app.hbm().getGroupList();
    var expectedGroup = new ArrayList<>(oldGroups);
    expectedGroup.remove(index);
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedGroup);
  }

  @Test
  public void canRemoveAllGroupsAtOnceHbm() {
    app.hbm().verifyOrCreateAvailableGroupHbm();
    app.group().removeAllGroup();
    Assertions.assertEquals(0, app.hbm().getGroupCount());
  }
  //=======================================================
  @Test
  public void canRemoveGroup() {
    app.group().verifyOrCreateAvailableGroup();
    List<GroupData> oldGroups = app.group().getList();
    var rmd = new Random();
    int index = rmd.nextInt(oldGroups.size());
    app.group().removeGroup(oldGroups.get(index));
    List<GroupData> newGroups = app.group().getList();
    var expectedGroup = new ArrayList<>(oldGroups);
    expectedGroup.remove(index);
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedGroup);
  }

  @Test
  public void canRemoveGroupOld() {
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
