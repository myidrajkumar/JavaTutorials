package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Looping with MAX value.
 * 
 * @author Rajkumar
 *
 */
public class DataMaxValueProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private DataMaxValueProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int start = Integer.MAX_VALUE - 10;
    int end = Integer.MAX_VALUE;
    
    int count = 0;
    
    for (int i = start; i <= end; i++) {
      count++;
      logger.info("Keep on looping,  count is " + count);
    }
    
    logger.info(count);
  }
  
}
