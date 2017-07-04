package com.rajkumar.java.utils.regex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Regular expression example.
 * 
 * @author Rajkumar
 *
 */
public class ReplaceUsingRegExExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ReplaceUsingRegExExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String inputString = "    This is Taj     Mahal";
    inputString = inputString.replaceAll("\\s{2,}", " "); 
    // Replace if the sentence contains more than one space with one space
    logger.info(inputString);
    
  }
  
}
