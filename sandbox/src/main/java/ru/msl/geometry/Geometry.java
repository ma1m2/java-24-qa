package ru.msl.geometry;

import ru.msl.geometry.figures.Reqtangle;
import ru.msl.geometry.figures.Square;

public class Geometry {
  public static void main(String[] args) {
    Square.printSquareArea(new Square(4.0));
    Reqtangle.printRectangleArea(6.0, 8.0);
    Reqtangle.printRectangleArea(8.0, 10.0);
    Reqtangle.printRectangleArea(10.0, 12.0);
  }

}
