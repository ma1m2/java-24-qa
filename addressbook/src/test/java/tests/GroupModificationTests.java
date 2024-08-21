package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {

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
