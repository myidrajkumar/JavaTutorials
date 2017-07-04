package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unicode character problem.
 * 
 * @author Rajkumar
 *
 */
public class UnicodeNewLineProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private UnicodeNewLineProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    logger.info("Hi Guys!");
    
    /****
     * <p>
     * // Character myChar = new Character('\u000d'); 
     * 
     * //Even though this looks valid, this is compilation error - uncomment and see it
     * </p>
     * logger.info("Hi Guys!"); // Character myChar = new Character('\u000d');
     * //Even if it is commented, program will not run
     * 
     * 
     */
  }
  
}
