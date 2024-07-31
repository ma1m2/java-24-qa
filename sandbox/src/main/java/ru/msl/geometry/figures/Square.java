package ru.msl.geometry.figures;

public class Square {
  private double side;
  public Square(double side) {
    this.side = side;
  }

  public static void printSquareArea(Square s) {
    String text = String.format("Area of square with a side %f = %f", s.side, s.area());
    System.out.println(text);
  }

  public double area() {
    return this.side * this.side;
  }

  public double perimeter() {
    return 4 * this.side;
  }
}
