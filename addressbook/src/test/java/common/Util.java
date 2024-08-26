package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Util {
  public static String randomFile(String dir) {
    var fileNames = new File(dir).list();
    var rnd = new Random();
    var index = rnd.nextInt(fileNames.length);
    return Paths.get(dir, fileNames[index]).toString();
  }

  public static String randomString(int length) {
    var rnd = new Random();
    var result = "";
    for (int i = 0; i < length; i++) {
      result = result + (char)('a' + rnd.nextInt(26));
    }
    return result;
  }

  public static String randomNumber(int length) {
    var rnd = new Random();
    var result = "";
    for (int i = 0; i < length; i++) {
      result = result + (char)('0' + rnd.nextInt(10));
    }
    return result;
  }
}
