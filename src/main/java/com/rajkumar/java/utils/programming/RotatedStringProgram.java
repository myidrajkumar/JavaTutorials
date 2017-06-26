package com.rajkumar.java.utils.programming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * To check the string is rotated or not.
 * 
 * @author Rajkumar
 *
 */
public class RotatedStringProgram {
  
  private RotatedStringProgram() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    Logger logger = LogManager.getLogger();
    
    String str1 = "hello";
    String str2 = "elloh";
    
    if (isRotation(str1, str2)) {
      logger.info("'" + str2 + "' is a rotated version of '" + str1 + "'");
    } else {
      logger.info("'" + str2 + "' is not a rotated version of '" + str1 + "'");
    }
  }

  public static boolean isRotation(String s1, String s2) {
    return (s1.length() == s2.length()) && ((s1 + s1).indexOf(s2) != -1);
  }

}
