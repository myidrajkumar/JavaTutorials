package com.rajkumar.java.utils.junit.tutorials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.ZoneId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class AssumptionTest {
  
  private static Logger logger = LogManager.getLogger();
  
  @Test
  public void testMethod1() {
    
    assumeTrue(ZoneId.systemDefault().getId().equals("Asia/Calcutta"),
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
  public void testMethod5() {
    
    int num = 1;
    assumingThat(num == 1, () -> assertEquals(2, 2));
  }
  
  @Test
  public void testMethod6() {
    
    int num = 10;
    assumingThat(num == 1, () -> assertEquals(num + 1, 2));
  }
}
