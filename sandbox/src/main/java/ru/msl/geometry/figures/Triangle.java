package ru.msl.geometry.figures;

public class Triangle {
  private double a;
  private double b;
  private double c;

  public Triangle(double a, double b, double c) {
    if(a < 0 || b < 0 || c < 0) {
      throw new IllegalArgumentException("Triangle side should be non-negative");
    }
    if(a + b < c || a + c < b || b + c < a) {
      throw new IllegalArgumentException("Triangle doesn't exist");
    }
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public double perimeter() {
    return a + b + c;
  }

  public double area() {
    double p = perimeter() / 2;
    return Math.sqrt(p * (p - a) * (p - b) * (p - c));
  }
}
