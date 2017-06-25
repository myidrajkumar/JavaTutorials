package com.rajkumar.java.utils.junit.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class ExceptionTest {
  
  private static Logger logger = LogManager.getLogger();
  
  @Test
  public void testMethod1() {
    int i = 1 / 0;
    logger.info(i);
  }

  @Test(expected = ArithmeticException.class)
  public void testMethod2() {
    int i = 1 / 0;
    logger.info(i);
  }

  @Test(expected = ArithmeticException.class)
  public void testMethod3() {
    logger.info("running testMethod3");
  }
}