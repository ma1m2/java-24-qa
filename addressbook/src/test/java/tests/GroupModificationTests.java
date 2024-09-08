package tests;

import common.Util;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  //video 7.4
  @Test
  public void canModifyGroupSet() {
    app.hbm().verifyOrCreateAvailableGroupHbm();
    var oldGroups = app.hbm().getGroupList();
    var rmd = new Random();
    int index = rmd.nextInt(oldGroups.size());
    GroupData testData = new GroupData().withName(Util.randomString(9))
            .withHeader(Util.randomString(9)).withFooter(Util.randomString(9));
    app.group().modifyGroup(oldGroups.get(index), testData);
    var newGroups = app.hbm().getGroupList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index, testData.withId(oldGroups.get(index).id()));
    Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
  }

  //video 6.3
  @Test
  public void canModifyGroupHbm() {
    app.hbm().verifyOrCreateAvailableGroupHbm();
    var oldGroups = app.hbm().getGroupList();
    var rmd = new Random();
    int index = rmd.nextInt(oldGroups.size());
    GroupData testData = new GroupData().withName("modified group")
            .withHeader("modified header").withFooter("modified footer");
    app.group().modifyGroup(oldGroups.get(index), testData);
    var newGroups = app.hbm().getGroupList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index, testData.withId(oldGroups.get(index).id()));
    newGroups.sort(app.group().compareById());
    expectedList.sort(app.group().compareById());
    Assertions.assertEquals(newGroups, expectedList);
  }
  //=======================================================
  @Test
  public void canModifyGroup() {
    app.group().verifyOrCreateAvailableGroup();
    var oldGroups = app.group().getList();
    var rmd = new Random();
    int index = rmd.nextInt(oldGroups.size());
    GroupData testData = new GroupData().withName("modified group");
    app.group().modifyGroup(oldGroups.get(index), testData);
    var newGroups = app.group().getList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index, testData.withId(oldGroups.get(index).id()));
    newGroups.sort(app.group().compareById());
    expectedList.sort(app.group().compareById());
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  public void canModifyGroupOld() {
    app.group().verifyOrCreateAvailableGroup();
    app.group().modifyGroupOld(new GroupData().withName("modified group"));
  }

}
