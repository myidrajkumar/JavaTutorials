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
    
    for (int i = 0; i < 3; i++) { // NOSONAR
      hmm: // NOSONAR
      try {
        logger.info("Try :  " + i);
        if (i % 2 == 0) {
          break hmm;
        } else {
          throw new RuntimeException("Hmmm"); // NOSONAR
        }
      } finally {
        logger.info("Finally :  " + i);
        if (i < 2) {
          continue; // NOSONAR
          /*
           * This is serious threat. Jump should not be present in finally Due
           * to that, program is still continuing.
           */
        }
      }
      
      logger.info("Loop end :  " + i);
    }
    
  }
  
}
