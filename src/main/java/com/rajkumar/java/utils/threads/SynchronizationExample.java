package com.rajkumar.java.utils.threads;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Various Synchronization Example.
 * 
 * @author Rajkumar
 *
 */
public class SynchronizationExample {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Instance lock.
   */
  public synchronized void lockedByThis() {
    
    logger.info("This sync method is locked by current instance i.e. this");
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    logger.info(Thread.currentThread().getName());
  }
  
  /**
   * Class level lock.
   */
  public static synchronized void lockedByClassLock() {
    
    logger.info(
        "This static synchronized method is locked by class level lock of this class"
            + " i.e. SychronizationExample.class");
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    logger.info(Thread.currentThread().getName());
  }
  
  /**
   * Synchronized Block.
   */
  public void lockedBySynchronizedBlock() {
    
    logger.info("This line is executed without locking");
    
    Object obj = SynchronizationExample.class;
    
    synchronized (obj) {
      logger.info("synchronized block, locked by obj variable");
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
      logger.info(Thread.currentThread().getName());
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    SynchronizationExample firstExample = new SynchronizationExample();
    SynchronizationExample secondExample = new SynchronizationExample();
    
    Runnable firstRunnable = firstExample::lockedByThis;
    Runnable secondRunnable = secondExample::lockedByThis;
    
    Thread firstInstanceLock = new Thread(firstRunnable);
    firstInstanceLock.start();
    
    // This thread execution will start only after previous thread completion
    Thread secondInstanceLock = new Thread(firstRunnable);
    secondInstanceLock.start();
    
    // This thread execution will start parallel since this is of new object
    Thread thirdInstanceLock = new Thread(secondRunnable);
    thirdInstanceLock.start();
    
    try {
      firstInstanceLock.join();
      secondInstanceLock.join();
      thirdInstanceLock.join();
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    Runnable thirdRunnable = SynchronizationExample::lockedByClassLock;
    Runnable fourthRunnable = SynchronizationExample::lockedByClassLock;
    
    Thread fourthInstanceLock = new Thread(thirdRunnable);
    fourthInstanceLock.start();
    
    // This thread execution will start only after previous thread completion
    Thread fifthInstanceLock = new Thread(fourthRunnable);
    fifthInstanceLock.start();
    
    try {
      fourthInstanceLock.join();
      fifthInstanceLock.join();
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    Runnable fifthRunnable = firstExample::lockedBySynchronizedBlock;
    Runnable sixthRunnable = firstExample::lockedBySynchronizedBlock;
    Runnable seventhRunnable = secondExample::lockedBySynchronizedBlock;
    Runnable eighthRunnable = secondExample::lockedBySynchronizedBlock;
    
    Thread sixthInstanceLock = new Thread(fifthRunnable);
    Thread seventhInstanceLock = new Thread(sixthRunnable);
    Thread eighthInstanceLock = new Thread(seventhRunnable);
    //Why only this has been made final??  to avoid checkstyle issues
    //However it is best practice to make it final always.
    final Thread ninthInstanceLock = new Thread(eighthRunnable); 
    
    sixthInstanceLock.start();
    seventhInstanceLock.start();
    eighthInstanceLock.start();
    ninthInstanceLock.start();
    
    try {
      sixthInstanceLock.join();
      seventhInstanceLock.join();
      eighthInstanceLock.join();
      ninthInstanceLock.join();
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
  }
  
}
