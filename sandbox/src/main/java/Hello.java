public class Hello {
  public static void main(String[] args) {
    var x = 1;
    var y = 1;
    if(y == 0) {
      System.out.println("Division by zero is not allowed");
    } else {
      var z = divide(x, y);
      System.out.println(z);
    }

/*    try {
      var x = 1;
      var y = 0;
      var z = divide(x, y);
      System.out.println(z);
    } catch (ArithmeticException exception) {
      System.out.println("Division by zero is not allowed");
    }*/
/*    var configFile = new File("sandbox/build.gradle");
    System.out.println(configFile.getAbsolutePath());
    System.out.println(configFile.exists());*/
  }

  private static int divide(int x, int y) {
    var z = x / y;
    return z;
  }
}
