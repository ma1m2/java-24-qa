package ru.msl.mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
  //video 7.3 5:00
  public static String randomString(int length) {
    var rnd = new Random();
    Supplier<Integer> rndNumbers = () -> rnd.nextInt(26);
    var result = Stream.generate(rndNumbers)
            .limit(length)
            .map(i -> 'a' + i)
            .map(Character::toString)
            .collect(Collectors.joining());
    return result;
  }

  public static String randomFile(String dir) {
    var fileNames = new File(dir).list();
    var rnd = new Random();
    var index = rnd.nextInt(fileNames.length);
    return Paths.get(dir, fileNames[index]).toString();
  }

  public static String randomStringOld(int length) {
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

  public String randomStringGPT(int length) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append((char) ('a' + (int) (Math.random() * 26)));
    }
    return sb.toString();
  }
}
