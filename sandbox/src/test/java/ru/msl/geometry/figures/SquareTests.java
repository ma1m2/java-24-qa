package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
  @Test
  public void canCalculateArea() {
    var s = new Square(4.0);
    Assertions.assertEquals(16.0, s.area());
  }

  @Test
  public void canCalculatePerimeter() {
    Assertions.assertEquals(16.0, new Square(4.0).perimeter());
  }
}
