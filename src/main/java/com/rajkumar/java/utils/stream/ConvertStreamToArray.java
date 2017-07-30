package com.rajkumar.java.utils.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Converting stream to array.
 * 
 * @author Rajkumar
 *
 */
public class ConvertStreamToArray {
  
  private ConvertStreamToArray() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<String> stringList = Arrays.asList("Red", "Blue", "Green", "Orange",
        "Yellow");
    Stream<String> stringStream = stringList.stream();
    String[] stringArray = stringStream.toArray(size -> new String[size]);
    logger.info(Arrays.toString(stringArray));
    
  }
  
}
