package com.rajkumar.java.utils.junit.tutorials;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.ZoneId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Test;

public class AssumptionTest {
  
  private static Logger logger = LogManager.getLogger();
  
  @Test
  public void testMethod1() {
    
    assumeTrue(ZoneId.systemDefault().getId().equals("America/New_York"),
        "applicable time zone assumption");
    logger.info("assumption passed");
  }
  
  @Test
  public void testMethod2() {
    
    assumeTrue(!ZoneId.systemDefault().getId().equals("America/New_York"),
        "not applicable time zone assumption");
    logger.info("assumption passed");
  }
  
  @Test
  public void testMethod3() {
    
    try {
      int result = 1 / 0;
      logger.info(result);
    } catch (ArithmeticException e) {
      Assume.assumeNoException("No ArithmeticException assumption", e);
    }
  }
  
  @Test
  public void testMethod4() {
    
    try {
      int result = 1 / 2;
      logger.info("i: " + result);
    } catch (ArithmeticException e) {
      Assume.assumeNoException("No ArithmeticException assumption", e);
    }
  }
  
  @Test
  public void testMethod5() {
    
    int num = 1;
    assumingThat(num == 1, () -> assertEquals(2, 2));
  }
  
  @Test
  public void testMethod6() {
    
    int num = 10;
    assumingThat(num == 1, () -> assertEquals(2, 2));
  }
  
  @Test
  public void testMethod7() {
    
    String value1 = "s value";
    String value2 = null;
    
    Assume.assumeNotNull(value1, value2);
  }
  
  @Test
  public void testMethod8() {
    
    String value1 = "s value";
    String value2 = "t value";
    Assume.assumeNotNull(value1, value2);
  }
}
