package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Postfix problem.
 * 
 * @author Rajkumar
 *
 */
public class PostPrefixProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private PostPrefixProblem() {}
  
  /**
   * Some value returns.
   * 
   * @return some value
   */
  public static int getValue() {
    
    int value = 0;
    value++; // c = 1 now
    value--; // c = 0 now
    return (value++); //NOSONAR
    // returns 0; So go with Pre-Increment Only
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    logger.info(PostPrefixProblem.getValue());
  }
  
}
