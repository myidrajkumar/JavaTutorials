package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This problem is present for all wrappers.
 * 
 * @author Rajkumar
 *
 */
public class IntegerCacheProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private IntegerCacheProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String... args) {
    
    Integer integer1 = 3;
    Integer integer2 = 3;
    
    if (integer1 == integer2) {
      logger.info("3 == 3"); // Because less than 256 values will be cached
    } else {
      logger.info("3 != 3");
    }
    
    Integer integer3 = 300;
    Integer integer4 = 300;
    
    if (integer3 == integer4) {
      logger.info("300 == 300");
    } else {
      logger.info("300 != 300");
    }
    
  }
}
