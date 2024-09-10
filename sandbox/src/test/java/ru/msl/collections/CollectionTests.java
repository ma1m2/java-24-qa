package ru.msl.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTests {
  //video 7.6
  @Test
  void mapTest() {
    var digits = new HashMap<Character, String>();
    digits.put('0', "zero");
    digits.put('1', "one");
    digits.put('2', "two");
    digits.put('3', "three");
    digits.put('4', "four");
    digits.put('5', "five");
    digits.put('6', "six");
    digits.put('7', "seven");
    digits.put('8', "eight");
    digits.put('9', "nine");
    Assertions.assertEquals("nine", digits.get('9'));
    digits.put('9', "nove");
    System.out.println(digits);
    Assertions.assertEquals(10, digits.size());
    Assertions.assertEquals("nove", digits.get('9'));
  }

  //video 7.4
  @Test
  void setTest() {
    //var set = Set.copyOf(List.of("a", "b", "c", "a"));
    var set = new HashSet<>(List.of("a", "b", "c", "a"));
    System.out.println(set.iterator().next());
    System.out.println(set.stream().findAny().get());
    set.add("b");
    Assertions.assertEquals(3, set.size());
  }

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
