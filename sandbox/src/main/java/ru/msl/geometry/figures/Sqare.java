package ru.msl.geometry.figures;

public class Sqare {
  public static void printSquareArea(double a) {
    System.out.println("Area of square with a side " + a + " = " + area(a));
  }

  private static double area(double side) {
    return side * side;
  }
}
