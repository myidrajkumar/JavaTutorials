package com.rajkumar.java.utils.threads;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Thread Pool Example.
 * 
 * @author Rajkumar
 *
 */
public class ThreadPoolExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ThreadPoolExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // creating a pool of 5 threads
    ExecutorService executor = Executors.newFixedThreadPool(5);
    
    for (int i = 0; i < 10; i++) {
      Runnable runnable = new WorkerThread("Message " + i);
      executor.execute(runnable);
    }
    
    executor.shutdown();
    // while (!executor.isTerminated()) {} //NOSONAR - What is the other approach
    
    // Do not use the above approach since it is continuously processing
    try {
      executor.awaitTermination(1, TimeUnit.HOURS);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    logger.info("Finished all threads");
  }
  
  private static class WorkerThread implements Runnable {
    
    private String message;
    
    public WorkerThread(String message) {
      this.message = message;
    }
    
    @Override
    public void run() {
      
      logger.info(
          Thread.currentThread().getName() + " (Start) message = " + message);
      processmessage();
      logger.info(Thread.currentThread().getName() + " (End)");
    }
    
    private void processmessage() {
      
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
    }
  }
  
}
