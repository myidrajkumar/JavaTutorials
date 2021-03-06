package com.rajkumar.java.utils.threads;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Thread Sleeping Example.
 * 
 * @author Rajkumar
 *
 */
public class ThreadSleepExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ThreadSleepExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Runnable runnable = () -> {
      logger.info("Foo " + Thread.currentThread().getName());
      
      //Rather than using Thread.sleep(), Use the below which is more readable
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
      
      logger.info("Bar " + Thread.currentThread().getName());
    };
    
    Thread thread = new Thread(runnable);
    thread.start();
  }
}
