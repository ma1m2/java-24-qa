import java.io.File;

public class Hello {
  public static void main(String[] args) {
    try {
      var z = calculate();
      System.out.println(z);
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    System.out.println("Hello, World!");

/*    var configFile = new File("sandbox/build.gradle");
    System.out.println(configFile.getAbsolutePath());
    System.out.println(configFile.exists());*/
  }

  private static int calculate() {
    var x = 1;
    var y = 0;
    var z = divide(x, y);
    return z;
  }

  private static int divide(int x, int y) {
    var z = x / y;
    return z;
  }
}
