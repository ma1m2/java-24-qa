package ru.msl.geometry.figures;

public class Reqtangle {
  public static void printRectangleArea(double a, double b) {
    System.out.println("Area of rectangle with a side " + a + " and a side " + b + " = " + rectangleArea(a, b));
  }

  private static double rectangleArea(double a, double b) {
    return a * b;
  }
}
