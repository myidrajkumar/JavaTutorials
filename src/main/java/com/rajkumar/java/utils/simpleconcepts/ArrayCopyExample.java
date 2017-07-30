package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Copying Arrays.
 * 
 * @author Rajkumar
 *
 */
public class ArrayCopyExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ArrayCopyExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String[] source = {"test1", "test2", "test3"};
    String[] destination = new String[source.length];
    
    // Copies from one array to another array
    logger.info("Method1: Using System.arrayCopy");
    System.arraycopy(source, 0, destination, 0, source.length);
    logger.info(Arrays.toString(destination));
    
    logger.info(Constants.LINE_SEPERATOR);
    
    
    logger.info("Method2: Using Arrays.copyOf");
    String[] anotherArr = Arrays.copyOf(source, source.length + 1);
    anotherArr[source.length] = "test4";
    
    logger.info(Arrays.toString(anotherArr));
  }
  
}
