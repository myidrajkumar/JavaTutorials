package com.rajkumar.java.utils.threads;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CountDown Latch Example.
 * 
 * @author Rajkumar
 *
 */
public class CountDownLatchDemo {
  
  private static Logger logger = LogManager.getLogger();
  private static final CountDownLatch loadingLatch = new CountDownLatch(3);
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Runnable pythonRunnable = () -> getRunnableContent("python");
    Thread pythonThread = new Thread(pythonRunnable);
    
    Runnable javaRunnable = () -> getRunnableContent("java");
    Thread javaThread = new Thread(javaRunnable);
    
    Runnable devRunnable = () -> getRunnableContent("dev");
    Thread devThread = new Thread(devRunnable);
    
    pythonThread.start();
    javaThread.start();
    devThread.start();
        
    try {
      loadingLatch.await();
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    logger.info("Finished all execution");
  }

  private static void getRunnableContent(String courseName) {
    
    logger.info("Loading " + courseName + " courses");
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    logger.info("Loaded " + courseName + " courses");
    loadingLatch.countDown();
  }
  
}
