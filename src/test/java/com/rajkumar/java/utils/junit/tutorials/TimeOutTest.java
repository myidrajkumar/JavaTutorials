package com.rajkumar.java.utils.junit.tutorials;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

public class TimeOutTest {

  private static Logger logger = LogManager.getLogger();
  
  @Test(timeout = 200)
  @Disabled
  //TODO
  public void testMethod() {
    logger.info("If this method did not return within specified time, will fail.");
    try {
      //To pass the test i am mentioning lesser time period
      TimeUnit.MILLISECONDS.sleep(100);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
    }
  }
}