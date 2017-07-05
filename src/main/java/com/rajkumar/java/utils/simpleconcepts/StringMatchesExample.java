package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * String match example. Here we can even give regular expression.
 * 
 * @author Rajkumar
 *
 */
public class StringMatchesExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private StringMatchesExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String... args) {
    
    String[] alphabets = {"", "12345", "A12345", "12345B", "12345a", "abcd",
        "aa343"};
    
    for (String alphabet : alphabets) {
      logger.info(" does " + alphabet + " contains alphabetic words : "
          + alphabet.matches(".*[A-Za-z].*"));
      
    }
    
    // checking if String contains digits or not
    String[] numbers = {"1234", "+1234", "234a"};
    for (String number : numbers) {
      logger.info(" number " + number + " contains only 1-9 digits : "
          + number.matches(".*[1-9].*"));
    }
    
  }
}
