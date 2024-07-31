package ru.msl.geometry.figures;

public class Reqtangle {
  public static void printRectangleArea(double a, double b) {
    String text = String.format("Area of rectangle with a side %f and a side %f = %f", a, b, rectangleArea(a, b));
    System.out.println(text);
  }

  private static double rectangleArea(double a, double b) {
    return a * b;
  }
}
