package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Shutting down threads example.
 * 
 * @author Rajkumar
 *
 */
public class ThreadsShutdownExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ThreadsShutdownExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Runnable runnable = ThreadsShutdownExample::runnableTask;
    
    ExecutorService executor = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 10; i++) {
      Runnable task = new Thread(runnable);
      executor.execute(task);
    }
    
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.HOURS);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    logger.info("Hello, Am I be printed after completion of threads??");
    
  }

  private static void runnableTask() {
    
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    logger.info("Thread --> " + Thread.currentThread().getName());
  }
  
}