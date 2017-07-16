package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore - Restrict the number of threads that can access a resource.
 * When max count is reached, others are blocked to release the lock.
 * 
 * @author Rajkumar
 *
 */
public class SemaphoreExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private SemaphoreExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Semaphore semaphore = new Semaphore(2);
    
    Runnable task = () -> {
      logger.info(Thread.currentThread().getName() 
          + " : Before Acquiring ==> Available Permits Now: " + semaphore.availablePermits());
      
      logger.info(Thread.currentThread().getName() 
          + " is going to acquire the lock");
      
      try {
        semaphore.acquire();
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
      
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
      
      logger.info(Thread.currentThread().getName() 
          + " : After Acquiring ==> Available Permits Now: " + semaphore.availablePermits());
      
      logger.info(Thread.currentThread().getName() 
          + " is going to release the lock");
      semaphore.release();
      
      logger.info(Thread.currentThread().getName() 
          + " : After Releasing ==> Available Permits Now: " + semaphore.availablePermits());

    };
    
    Thread thread1 = new Thread(task, "T1");
    thread1.start();
    
    Thread thread2 = new Thread(task, "T2");
    thread2.start();
    
    Thread thread3 = new Thread(task, "T3");
    thread3.start();
    
    Thread thread4 = new Thread(task, "T4");
    thread4.start();
    
  }
  
}
