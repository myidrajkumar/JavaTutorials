package com.rajkumar.java.utils.junit.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Test;

import java.time.ZoneId;

public class AssumptionTest {

  private static Logger logger = LogManager.getLogger();

  @Test
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
  public void testMethod3() {
    logger.info("test starts");
    try {
      int result = 1 / 0;
      logger.info(result);
    } catch (ArithmeticException e) {
      Assume.assumeNoException("No ArithmeticException assumption", e);
    }

    logger.info("test continues");
  }

  @Test
  public void testMethod4() {
    logger.info("test starts");
    try {
      int result = 1 / 2;
      logger.info("result: " + result);
    } catch (ArithmeticException e) {
      Assume.assumeNoException("No ArithmeticException assumption", e);
    }

    logger.info("test continues");
  }

  @Test
  public void testMethod5() {
    logger.info("test starts");
    int result = 1;
    Assume.assumeThat("x being 10 assumption", result, CoreMatchers.is(10));
    logger.info("test continues");
  }

  @Test
  public void testMethod6() {
    logger.info("test starts");
    int number = 10;
    Assume.assumeThat("x being 10 assumption", number, CoreMatchers.is(10));
    logger.info("test continues");
  }

  @Test
  public void testMethod7() {
    logger.info("test starts");
    String strValue = "s value";
    String anotherValue = null;
    Assume.assumeNotNull(strValue, anotherValue);
    logger.info("test continues");
  }

  @Test
  public void testMethod8() {
    logger.info("test starts");
    String strValue = "s value";
    String anotherValue = "t value";
    Assume.assumeNotNull(strValue, anotherValue);
    logger.info("test continues");
  }
}