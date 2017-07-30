package com.rajkumar.java.utils.challenges;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenge3.
 * 
 * @author Rajkumar
 *
 */
public class StringChallenge {
  
  private static Logger logger = LogManager.getLogger();
  
  private StringChallenge() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String s1 = "hello";
    String s2 = "hel" + "lo";  //This addition refers from string pool
    String s3 = " mum";
    
    if (s1 == s2) {  //NOSONAR
      logger.info("1 and 2 are same");
    } else {
      logger.info("1 and 2 are different");
    }
    
    if (s1 + s3 == "hello mum") {  //NOSONAR
      logger.info("both are same");
    } else {
      logger.info("both are different");
    }
    
  }
  
}
