package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    Comparator<GroupData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newGroups.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  public void canModifyGroupOld() {
    app.group().verifyOrCreateAvailableGroup();
    app.group().modifyGroupOld(new GroupData().withName("modified group"));
  }

}
