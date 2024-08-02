package ru.msl.geometry.figures;

import java.util.Objects;

public record Reqtangle (double a, double b) {

  public Reqtangle(double a, double b) {
    if(a < 0 || b < 0) {
      throw new IllegalArgumentException("Rectangle side should be non-negative");
    }
    this.a = a;
    this.b = b;
  }

  public static void printRectangleArea(double a, double b) {
    String text = String.format("Area of rectangle with a side %f and a side %f = %f", a, b, rectangleArea(a, b));
    System.out.println(text);
  }

  private static double rectangleArea(double a, double b) {
    return a * b;
  }

  public double area() {
    return a * b;
  }
  public double perimeter() {
    return 2 * (a + b);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Reqtangle reqtangle = (Reqtangle) o;
    return (Double.compare(this.a, reqtangle.a) == 0 && Double.compare(this.b, reqtangle.b) == 0
            || Double.compare(this.a, reqtangle.b) == 0 && Double.compare(this.b, reqtangle.a) == 0);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }
}
