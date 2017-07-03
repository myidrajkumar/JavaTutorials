package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Filter Stream Example.
 * 
 * @author Rajkumar
 *
 */
public class FilteringExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private FilteringExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int[] numbers = {1, 2, 3, 4, 5};
    int searchKey = 8;
    
    int index = IntStream.range(0, numbers.length)
        .filter(i -> searchKey == numbers[i])
        .findFirst()
        .orElse(-1);
    
    logger.info("Index of 3 in numbers array = " + index);
    
    // filtering non-null values
    Stream<String> language = Stream.of("java", "python", "node", null, "ruby",
        null, "php");
    language.filter(x -> x != null)  //NOSONAR
        .collect(Collectors.toList())
        .forEach(logger::info);
    
    // Another way is
    Stream<String> language1 = Stream.of("java", "python", "node", null, "ruby",
        null, "php");
    language1.filter(Objects::nonNull)
        .collect(Collectors.toList())
        .forEach(logger::info);
  }
}
