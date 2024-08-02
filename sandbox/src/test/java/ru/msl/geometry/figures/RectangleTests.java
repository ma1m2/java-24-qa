package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {
  @Test
  public void cannotCreateRectangleWithOneNegativeSide() {
    try {
      new Reqtangle(-4.0, 5.0);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for one negative side");
    }
  }

  @Test
  public void cannotCreateRectangleWithSecondNegativeSide() {
    try {
      new Reqtangle(4.0, -5.0);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for second negative side");
    }
  }

  @Test
  public void testEquality() {
    var r1 = new Reqtangle(4.0, 5.0);
    var r2 = new Reqtangle(4.0, 5.0);
    System.out.println(r1 + " = " + r2);
    Assertions.assertEquals(r1, r2);
  }

  @Test
  public void testEquality2() {
    var r1 = new Reqtangle(4.0, 5.0);
    var r2 = new Reqtangle(5.0, 4.0);
    System.out.println(r1 + " = " + r2);
    Assertions.assertEquals(r1, r2);
  }
}
