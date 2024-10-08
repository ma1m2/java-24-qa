package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
  @Test
  public void canCalculateArea() {
    var s = new Square(4.0);
    var result = s.area();
    //Assertions.assertEquals(16.0, result);
    if(result != 16.0) {
      throw new AssertionError(String.format("Expected %f, but actual %f", 16.0, result));
    }
  }

  @Test
  public void canCalculatePerimeter() {
    Assertions.assertEquals(16.0, new Square(4.0).perimeter());
  }

  @Test
  public void cannotCreateSquareWithNegativeSide() {
    try {
      new Square(-4.0);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testEquality() {
    var s1 = new Square(4.0);
    var s2 = new Square(4.0);
    System.out.println(s1 + " = " + s2);
    Assertions.assertEquals(s1, s2);
  }
}
