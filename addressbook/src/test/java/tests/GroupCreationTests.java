package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

  public static List<GroupData> groupProvider() {
    var result = new ArrayList<GroupData>();
    for (var name : List.of("", "group name")) {
      for (var header : List.of("", "group header")) {
        for (var footer : List.of("", "group footer")) {
          result.add(new GroupData(name, header, footer));
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      result.add(new GroupData(randomString(i*10), randomString(i*10), randomString(i*10)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupProvider")
  public void canCreateGroupWithObject(GroupData group){
    int groupCount = app.group().getCount();
    app.group().createGroup(group);
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  public static List<GroupData> nagatitiveGroupProvider() {
    var result = new ArrayList<GroupData>(List.of(
            new GroupData().withName("name'")));
    return result;
  }

  @ParameterizedTest
  @MethodSource("nagatitiveGroupProvider")
  public void canNotCreateGroup(GroupData group){
    int groupCount = app.group().getCount();
    app.group().createGroup(group);
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount, newGroupCount);
  }

  public static List<String> groupNameProvider() {
    var result = new ArrayList<String>(List.of("group", "name", "group name"));
    for(int i = 0; i < 4; i++) {
      result.add(randomString(i*10));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupNameProvider")
  public void canCreateMultipleGroup(String name) {
    int groupCount = app.group().getCount();
    app.group().createGroup(new GroupData(name, "group header", "group footer"));
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  @ParameterizedTest
  @ValueSource(strings = {"group", "name", "group name header footer"})
  public void canCreateGroup(String name) {
    int groupCount = app.group().getCount();
    app.group().createGroup(new GroupData(name, "group header", "group footer"));
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  /*@Test
  public void canCreateGroup() {
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

  @Test
  public void canCreateMultipleGroup() {
    int n = 5;
    int groupCount = app.group().getCount();
    for (int i = 0; i < n; i++) {
      app.group().createGroup(new GroupData(randomString(i * 10), "group header" + i, "group footer" + i));
    }
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + n, newGroupCount);
  }*/
}
