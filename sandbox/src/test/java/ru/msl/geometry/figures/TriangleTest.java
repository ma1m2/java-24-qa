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
}
