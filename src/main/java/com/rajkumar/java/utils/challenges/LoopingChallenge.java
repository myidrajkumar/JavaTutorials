package com.rajkumar.java.utils.challenges;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenge2.
 * 
 * @author Rajkumar
 *
 */
public class LoopingChallenge {
  
  private static Logger logger = LogManager.getLogger();
  
  private LoopingChallenge() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    for (int i = 0; i < 3; i++) {  //NOSONAR
      hmm:
      try {
        logger.info("Try :  " + i);
        if (i % 2 == 0) {
          break hmm;
        } else {
          throw new RuntimeException("Hmmm");
        }
      } finally {
        logger.info("Finally :  " + i);
        if (i < 2) {
          continue;  //NOSONAR
          //This is serious threat. Jump should not be present in finally
        }
      }
      
      logger.info("Loop end :  " + i);
    }
    
  }
  
}
