package ru.msl.geometry.figures;

public class Sqare {
  public static void printSquareArea(double a) {
    String text = String.format("Area of square with a side %f = %f", a, area(a));
    System.out.println(text);
  }

  public static double area(double side) {
    return side * side;
  }

  public static double perimeter(double side) {
    return 4 * side;
  }
}
