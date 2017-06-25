package com.rajkumar.java.utils.junit.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimeOutTest {
  
  private static Logger logger = LogManager.getLogger();

  @Test(timeout = 200)
  public void testMethod() {
    boolean condition = true;
    if (condition) {
      try {
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
        logger.info(e.getMessage());
      }
    }
  }
}