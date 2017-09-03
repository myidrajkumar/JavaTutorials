package com.rajkumar.java.utils.junit.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.rajkumar.java.utils.lib.Utils;

/**
 * Testing exceptions.
 * 
 * @author Rajkumar
 *
 */
public class ExceptionTest {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Just catching exception.
   */
  @Test
  public void testMethod1() {
    
    try {
      int result = 1 / 0;
      logger.info(result);
    } catch (ArithmeticException exception) {
      logger.error(Utils.getException(exception));
    }
  }
  
  /**
   * Just catching exception as expected.
   */
  @Test(expected = ArithmeticException.class)
  public void testMethod2() {
    
    int result = 1 / 0;
    logger.info(result);
  }
  
  /**
   * Throwing exception.
   */
  @Test(expected = ArithmeticException.class)
  public void testMethod3() {
    
    logger.info("This test will fail if the method is not throwing exception");
    logger.info("To pass the test, i am manually throwing exception");
    
    throw new ArithmeticException("Manually exception is thrown");
    
  }
}
