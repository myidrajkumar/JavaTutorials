package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Math Calculations example but interesting.
 * 
 * @author Rajkumar
 *
 */
public class TrickyMathCalculationExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private TrickyMathCalculationExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int addition1 = 10 + +11;
    logger.info("Add1 = " + addition1);
    
    int addition2 = 10 + +11 - -12;
    logger.info("Add2 = " + addition2);
    
    int addition3 = 10 + +11 - -12 + +13;
    logger.info("Add3 = " + addition3);
    
    int addition4 = 10 + +11 - -12 + +13 - -14;
    logger.info("Add4 = " + addition4);
    
    int addition5 = 10 + +11 - -12 + +13 + -14;
    logger.info("Add5 = " + addition5);
    
    int addition6 = 10 + +11 - -12 + +13 - +14;
    logger.info("Add6 = " + addition6);
  }
  
}
