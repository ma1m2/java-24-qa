import java.io.File;

public class Hello {
  public static void main(String[] args) {
    try {
      int z = calculate();
      System.out.println(z);
      System.out.println("Hello, World!");
    } catch (ArithmeticException exception) {
      exception.printStackTrace();
    }

/*    var configFile = new File("sandbox/build.gradle");
    System.out.println(configFile.getAbsolutePath());
    System.out.println(configFile.exists());*/
  }

  private static int calculate() {
    var z = divide(1, 1);
    return z;
  }

  private static int divide(int x, int y) {
    var z = x / y;
    return z;
  }
}
