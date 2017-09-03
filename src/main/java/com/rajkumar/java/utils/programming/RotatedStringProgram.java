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
  
  private RotatedStringProgram() {}
  
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
  
  /**
   * Input arguments to check whether they have been rotated.
   * 
   * @param argument1 Argument No1
   * @param argument2 Argument No2
   * 
   * @return true if just rotated else false
   */
  public static boolean isRotation(String argument1, String argument2) {
    
    return (argument1.length() == argument2.length())
        && ((argument1 + argument1).indexOf(argument2) != -1);
  }
  
}
