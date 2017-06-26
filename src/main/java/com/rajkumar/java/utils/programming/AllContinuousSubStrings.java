package com.rajkumar.java.utils.programming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Program to print all continuous sub strings.
 * 
 * @author Rajkumar
 *
 */
public class AllContinuousSubStrings {

  private static Logger logger = LogManager.getLogger();
  
  private AllContinuousSubStrings() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    String input = "abcd";
    printSubStrings(input);

  }

  /**
   * 
   * @param input to which substrings have to be printed.
   */
  private static void printSubStrings(String input) {

    int length = input.length();
    
    for (int i = 1; i < length; i++) {
      for (int j = 0; j <= length - i; j++) {
        logger.info(input.substring(j, j + i));
      }
    }
    
  }

}
