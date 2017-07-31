package com.rajkumar.java.utils.junit.tutorials;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Rules example.
 * 
 * @author Rajkumar
 *
 */
public class TimeOutRuleTest {
  
  private static Logger logger = LogManager.getLogger();
  
  @Rule
  //This is global rule and applies to all tests in this particular class and referred classes
  public Timeout myGlobalTimeOut = new Timeout(2000, TimeUnit.MILLISECONDS);
  
  @ClassRule
  //This is class rule and applies to all tests in this particular class only
  public static Timeout myClassTimeOut = new Timeout(2000, TimeUnit.MILLISECONDS);
  
  @Test
  @Ignore
  public void testMethod() {
    try {
      TimeUnit.MILLISECONDS.sleep(1800);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info("If this method did not return within specified time, will fail.");
  }
}
