package com.rajkumar.java.utils.junit.tutorials;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * Timeout example.
 * 
 * @author Rajkumar
 *
 */
public class TimeOutTest {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Testing with timeout operation.
   */
  @Test
  public void testMethod() {
    
    logger.info(
        "If this method did not return within specified time, will fail.");
    assertTimeout(Duration.ofSeconds(110), () -> TimeUnit.MILLISECONDS.sleep(100));
  }
}
