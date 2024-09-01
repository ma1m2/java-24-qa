package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.Util;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static common.Util.randomString;

public class GroupCreationTests extends TestBase {
  //video 6.2
  @ParameterizedTest
  @MethodSource("singleDataGroup")
  public void canCreateGroupHbm(GroupData group) {
    var oldGroups = app.hbm().getGroupList();
    app.group().createGroup(group);
    var newGroups = app.hbm().getGroupList();
    newGroups.sort(app.group().compareById());
    var expectedList = new ArrayList<>(oldGroups);
    var maxId = newGroups.get(newGroups.size() - 1).id();
    expectedList.add(group.withId(maxId));
    expectedList.sort(app.group().compareById());
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedList);
  }

  //video 6.1
  public static List<GroupData> singleDataGroup() {
    return List.of(new GroupData()
            .withName(randomString(10))
            .withHeader(randomString(20))
            .withFooter(randomString(30)));
  }

  @ParameterizedTest
  @MethodSource("singleDataGroup")
  public void canCreateGroupJdbc(GroupData group){
    var oldGroups = app.jdbc().getGroupList();
    app.group().createGroup(group);
    var newGroups = app.jdbc().getGroupList();
    newGroups.sort(app.group().compareById());
    var expectedList = new ArrayList<>(oldGroups);
    var maxId = newGroups.get(newGroups.size()-1).id();
    expectedList.add(group.withId(maxId));
    expectedList.sort(app.group().compareById());
    System.out.println(oldGroups.size() + " " + newGroups.size());
    Assertions.assertEquals(newGroups, expectedList);
  //===compare UI and DB list of group===
    var newUiGroups = app.group().getList();
    newUiGroups.sort(app.group().compareById());
    var newDbGoups = app.jdbc().getListWithIdAndName();
    newDbGoups.sort(app.group().compareById());
    Assertions.assertEquals(newUiGroups, newDbGoups);

  }

  //video 5.7
  public static List<GroupData> groupProviderXml() throws IOException {
    var result = new ArrayList<GroupData>();
    var mapper = new XmlMapper();
    var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>(){});
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupProviderXml")
  public void canCreateGroupsFromXmlFile(GroupData group){
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

  public static List<GroupData> groupProviderJson3() throws IOException {
    var result = new ArrayList<GroupData>();
    var json = "";
    try(var reader = new FileReader(  "groups.json"); var br = new BufferedReader(reader)) {
      var line = br.readLine();
      while (line != null) {
        json = json + line;
        line = br.readLine();
      }
    }
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(json, new TypeReference<List<GroupData>>(){});
    result.addAll(value);
    return result;
  }

  public static List<GroupData> groupProviderJson2() throws IOException {
    var result = new ArrayList<GroupData>();
    var json = Files.readString(Paths.get("groups.json"));
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(json, new TypeReference<List<GroupData>>(){});
    result.addAll(value);
    return result;
  }

  public static List<GroupData> groupProviderJson() throws IOException {
    var result = new ArrayList<GroupData>();
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("groupProviderJson3")
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
              .withName(randomString(i+5))
              .withHeader(randomString(i+5))
              .withFooter(randomString(i+5)));
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
      result.add(randomString(i*10));
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
