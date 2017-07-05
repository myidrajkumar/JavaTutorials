package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Operator precedence example.
 * 
 * @author Rajkumar
 *
 */
public class OperatorPrecedenceProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private OperatorPrecedenceProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    long[] a1 = {3, 4, 5};
    logger.info(a1[0] + a1[1] + a1[2] + " " + a1[0] + a1[1] + a1[2]);
  }
  
}
