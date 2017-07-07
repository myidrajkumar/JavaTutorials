package com.rajkumar.java.utils.junit.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimeOutTest {

  private static Logger logger = LogManager.getLogger();
  
  @Test(timeout = 200)
  public void testMethod() {
    logger.error("If this method did not return within specified time, will fail.");
    boolean condition = true;
    if (condition) {
      try {
        //To pass the test i am mentioning lesser time period
        TimeUnit.MILLISECONDS.sleep(180);
      } catch (InterruptedException e) {
        logger.info(e.getMessage());
      }
    }
  }
}