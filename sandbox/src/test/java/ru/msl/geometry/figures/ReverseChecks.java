package ru.msl.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {
  //video 7.5
  @Test
  void testSqrt() {
    var input = 5.0;
    var result = Math.sqrt(input);
    var reverse = result * result;
    Assertions.assertEquals(reverse, input, 0.00001);
  }

  @Test
  void testSort() {
    var input = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    input.sort(Integer::compareTo);
    for(int i = 0; i < input.size() - 1; i++) {
      Assertions.assertTrue(input.get(i) <= input.get(i + 1));
    }
  }

}
