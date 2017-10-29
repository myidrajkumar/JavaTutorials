package com.rajkumar.java.utils.junit.tutorials;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

/**
 * Testing exceptions.
 * 
 * @author Rajkumar
 *
 */
public class ExceptionTest {
  
  /**
   * Just catching exception.
   */
  @Test
  public void testMethod1() {
    
    assertThrows(ArithmeticException.class, () -> {
      int result = 1 / 0;
      LogManager.getLogger().info("Result => " + result);
    });

  }
}
