package com.rajkumar.java.utils.threads;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * To generate random number always use this.
 * 
 * @author Rajkumar
 *
 */
public class ThreadLocalRandomExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ThreadLocalRandomExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    logger.info(ThreadLocalRandom.current().nextInt(5, 10));
    
  }
  
}
