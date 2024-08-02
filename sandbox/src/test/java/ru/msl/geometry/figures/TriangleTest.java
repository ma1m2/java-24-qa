package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
  @Test
  public void canCalculatePerimeter() {
    var triangle = new Triangle(3, 4, 5);
    var perimeter = triangle.perimeter();
    System.out.println(perimeter);
    Assertions.assertEquals(12, perimeter);
  }

  @Test
  public void canCalculateArea() {
    var triangle = new Triangle(3, 4, 5);
    var area = triangle.area();
    System.out.println(area);
    Assertions.assertEquals(6, area);
  }

  @Test
  public void cannotCreateTriangleWithOneNegativeSide() {
    try {
      new Triangle(-3, 4, 5);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for one negative side");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void cannotCreateTriangleWithSecondNegativeSide() {
    try {
      new Triangle(3, -4, 5);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for second negative side");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void cannotCreateTriangleWithThirdNegativeSide() {
    try {
      new Triangle(3, 4, -5);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for third negative side");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void violatedTrianglInequalityFirstSide() {
    try {
      new Triangle(8, 4, 3);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for first side");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void violatedTrianglInequalitySecondSide() {
    try {
      new Triangle(3, 11, 7);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for second sides");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void violatedTrianglInequalityThirdSide() {
    try {
      new Triangle(3, 6, 10);
      Assertions.fail();
    } catch (IllegalArgumentException e) {
      System.out.println("I'm in catch block for third sidet");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testEquality() {
    var t1 = new Triangle(3, 4, 5);
    var t2 = new Triangle(4, 5, 3);
    System.out.println(t1 + " == " + t2);
    Assertions.assertEquals(t1, t2);
  }

}
