package ru.msl.geometry;

import ru.msl.geometry.figures.Reqtangle;
import ru.msl.geometry.figures.Square;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
  public static void main(String[] args) {

    //video 7.1
    var squares = List.of(new Square(4.0), new Square(6.0), new Square(8.0));
    squares.forEach(Square::printSquareArea);
    //Consumer<Square> print = (s) -> {Square.printSquareArea(s);};
    Consumer<Square> print = s -> {
      Square.printSquareArea(s);
      Square.perimeterSquare(s);
    };
    squares.forEach(print);

    //video 7.2
    Supplier<Square> rndSquare = () -> new Square(new Random().nextDouble(100.0));
    var sqs = Stream.generate(rndSquare).limit(3);
    sqs.peek(Square::printSquareArea).forEach(Square::perimeterSquare);

    //Square.printSquareArea(new Square(5.0));
/*    Reqtangle.printRectangleArea(6.0, 8.0);
    Reqtangle.printRectangleArea(8.0, 10.0);
    Reqtangle.printRectangleArea(10.0, 12.0);*/
  }

}
