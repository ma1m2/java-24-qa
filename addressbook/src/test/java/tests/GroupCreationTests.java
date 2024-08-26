package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Util;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

  public static List<GroupData> groupProviderJson() throws IOException {
    var result = new ArrayList<GroupData>();
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupProviderJson")
  public void canCreateGroupsFromFile(GroupData group){
    var oldGroups = app.group().getList();
    app.group().createGroup(group);
    var newGroups = app.group().getList();
    newGroups.sort(app.group().compareById());

    var expectedList = new ArrayList<>(oldGroups);
    expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id())
            .withHeader("")
            .withFooter(""));
    expectedList.sort(app.group().compareById());
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedList);
  }

  public static List<GroupData> groupProvider() {
    var result = new ArrayList<GroupData>();
    for (var name : List.of("", "group name")) {
      for (var header : List.of("", "group header")) {
        for (var footer : List.of("", "group footer")) {
          result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
        }
      }
    }
    for (int i = 0; i < 2; i++) {
      result.add(new GroupData()
              .withName(Util.randomString(i+5))
              .withHeader(Util.randomString(i+5))
              .withFooter(Util.randomString(i+5)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupProvider")
  public void canCreateMultipleGroupCompareList(GroupData group){
    var oldGroups = app.group().getList();
    app.group().createGroup(group);
    var newGroups = app.group().getList();
    newGroups.sort(app.group().compareById());

    var expectedList = new ArrayList<>(oldGroups);
    expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id())
            .withHeader("")
            .withFooter(""));
    expectedList.sort(app.group().compareById());
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedList);
  }

  @ParameterizedTest
  @MethodSource("groupProvider")
  public void canCreateMultipleGroupWithObject(GroupData group){
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
  public void canNotCreateGroupCompareList(GroupData group){
    var oldGroups = app.group().getList();
    app.group().createGroup(group);
    var newGroups = app.group().getList();
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, oldGroups);
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
      result.add(Util.randomString(i*10));
    }
    return result;
  }

  //@ParameterizedTest
  //@MethodSource("groupNameProvider")
  public void canCreateMultipleGroupWithName(String name) {
    int groupCount = app.group().getCount();
    app.group().createGroup(new GroupData("", name, "group header", "group footer"));
    int newGroupCount = app.group().getCount();
    System.out.println(groupCount + " " + newGroupCount);
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  @ParameterizedTest
  @ValueSource(strings = {"group", "name", "group name header footer"})
  public void canCreateGroup(String name) {
    int groupCount = app.group().getCount();
    app.group().createGroup(new GroupData("", name, "group header", "group footer"));
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
  public void canCreateMultipleGroupWithName() {
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
