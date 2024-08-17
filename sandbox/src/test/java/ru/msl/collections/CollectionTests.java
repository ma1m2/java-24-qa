package ru.msl.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {
  @Test
  void arrayTest() {
    //var array = new String[] {"a", "b", "c"};
    var array = new String[3];
    Assertions.assertEquals(3, array.length);
    System.out.println("Size of array: " + array.length);
    array[0] = "a";
    array[1] = "b";
    array[2] = "c";
    Assertions.assertEquals("a", array[0]);
  }

  @Test
  void listTest() {
    var list = new ArrayList<String>();
    list.add("a");
    list.add("b");
    list.add("c");
    list.set(0, "d");
    list.add(1, "e");
    System.out.println("Size of list: " + list.size());
    System.out.println(list);
    list.remove(0);
    System.out.println(list);
    Assertions.assertEquals("e", list.get(0));
    Assertions.assertEquals(3, list.size());
  }

  @Test
  void listOfTest() {
    var list = List.of("a", "b", "c");
    System.out.println("Size of list: " + list.size());
    System.out.println(list);
    Assertions.assertEquals("a", list.get(0));
    Assertions.assertEquals(3, list.size());
  }

  @Test
  void preFilledListTest() {
    var list = new ArrayList<String>(List.of("a", "b", "c"));
    System.out.println("Size of list: " + list.size());
    System.out.println(list);
    list.add(0, "g");
    list.remove(3);
    System.out.println("Size of list: " + list.size());
    System.out.println(list);
    Assertions.assertEquals("g", list.get(0));
    Assertions.assertEquals(3, list.size());
  }
}
