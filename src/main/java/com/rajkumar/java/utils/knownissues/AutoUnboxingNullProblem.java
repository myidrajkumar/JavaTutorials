package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

/**
 * Auto unboxing is dangerous; There will be chances of NullPointerException.
 * 
 * @author Rajkumar
 *
 */
public class AutoUnboxingNullProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private AutoUnboxingNullProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    problem1();
    
    logger.info(Constants.LINE_SEPERATOR);
    
    problem2();
  }
  
  @SuppressWarnings("null")
  private static void problem1() {
    
    Integer intNumber = null;
    if (intNumber == null) {  //NOSONAR
      logger.info("Why this Integer flag code is executed?");
    }
    
    Boolean booleanFlag = null;
    
    try {
      if (booleanFlag) {
        logger.info("Why this boolean flag code is executed?");
      }
    } catch (Exception exception) {
      logger.error(Utils.getException(exception));
    }
  }
  
  @SuppressWarnings("null")
  private static void problem2() {
    
    Integer intNumber = null;
    
    try {
      if (intNumber == 7) {  //NOSONAR
        logger.info("Why this Integer flag code is executed?");
      }
    } catch (Exception e) {
      logger.info("Problem2 ==> \n" + e);
    }
  }
  
}
