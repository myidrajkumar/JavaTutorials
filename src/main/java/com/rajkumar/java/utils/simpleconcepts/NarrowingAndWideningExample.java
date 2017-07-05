package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Up/Down casting example.
 * 
 * @author Rajkumar
 *
 */
public class NarrowingAndWideningExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private NarrowingAndWideningExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int intNumber = 991777777;
    logger.info("Integer => " + intNumber);
    
    short shortNumber = (short) intNumber; 
    // Narrowing explicitly we need to do
    logger.info("Short => " + shortNumber);
    
    long longNumber = intNumber; 
    // Widening implicitly automatically will be done
    logger.info("Long => " + longNumber);
  }
  
}
