package com.rajkumar.java.utils.junit.tutorials;

import java.time.ZoneId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

public class AssumptionTest {

  private static Logger logger = LogManager.getLogger();
  
  @Test
  @Ignore
  public void testMethod1() {
    Assume.assumeTrue("applicable time zone assumption",
        ZoneId.systemDefault().getId().equals("America/New_York"));
    logger.info("assumption passed");
  }

  @Test
  public void testMethod2() {
    Assume.assumeTrue("not applicable time zone assumption",
        !ZoneId.systemDefault().getId().equals("America/New_York"));
    logger.info("assumption passed");
  }

  @Test
  @Ignore
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
  @Ignore
  public void testMethod5() {
    int num = 1;
    Assume.assumeThat("x being 10 assumption", num, CoreMatchers.is(10));
  }

  @Test
  public void testMethod6() {
    int num = 10;
    Assume.assumeThat("x being 10 assumption", num, CoreMatchers.is(10));
  }

  @Test
  @Ignore
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