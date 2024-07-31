package ru.msl.geometry;

import ru.msl.geometry.figures.Reqtangle;
import ru.msl.geometry.figures.Sqare;

public class Geometry {
  public static void main(String[] args) {
    Sqare.printSquareArea(6.0);
    Sqare.printSquareArea(8.0);
    Sqare.printSquareArea(10.0);
    Sqare.printSquareArea(12.0);
    Reqtangle.printRectangleArea(6.0, 8.0);
    Reqtangle.printRectangleArea(8.0, 10.0);
    Reqtangle.printRectangleArea(10.0, 12.0);
  }

}
