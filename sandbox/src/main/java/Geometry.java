public class Geometry {
  public static void main(String[] args) {
    printSquareArea(6.0);
    printSquareArea(8.0);
    printSquareArea(10.0);
    printSquareArea(12.0);
    printRectangleArea(6.0, 8.0);
    printRectangleArea(8.0, 10.0);
    printRectangleArea(10.0, 12.0);
  }

  private static void printRectangleArea(double a, double b) {
    System.out.println("Area of rectangle with a side " + a + " and a side " + b + " = " + rectangleArea(a, b));
  }

  private static double rectangleArea(double a, double b) {
    return a * b;
  }

  private static void printSquareArea(double a) {
    System.out.println("Area of square with a side " + a + " = " + area(a));
  }

  private static double area(double side) {
    return side * side;
  }
}
