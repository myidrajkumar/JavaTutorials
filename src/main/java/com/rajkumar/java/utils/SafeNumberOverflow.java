package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

/**
 * Instead of getting the negative value exception is thrown.
 * 
 * @author Rajkumar
 *
 */
public class SafeNumberOverflow {
  
  private static Logger logger = LogManager.getLogger();
  
  private SafeNumberOverflow() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    int firstNum = 2000000000;
    int secondNum = 1000000000;

    int out = firstNum + secondNum;
    logger.info(out);
    logger.info(Constants.LINE_SEPERATOR);
    
    try {
      out = Math.addExact(firstNum, secondNum);
      logger.info(out);
    } catch (ArithmeticException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info(Constants.LINE_SEPERATOR);
    
  }

}
