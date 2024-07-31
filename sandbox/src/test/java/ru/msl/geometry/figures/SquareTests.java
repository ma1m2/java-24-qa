package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
  @Test
  public void canCalculateArea() {
    Assertions.assertEquals(16.0, Sqare.area(4.0));
  }

  @Test
  public void canCalculatePerimeter() {
    Assertions.assertEquals(16.0, Sqare.perimeter(4.0));
  }
}
