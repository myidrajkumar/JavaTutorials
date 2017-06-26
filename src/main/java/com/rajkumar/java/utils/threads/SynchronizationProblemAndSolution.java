package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Example of non-synchronization problem.
 * 
 * @author Rajkumar
 *
 */
public class SynchronizationProblemAndSolution {

  private static final int INCREMENT_COUNT = 10000;
  private static int count = 0;
  
  private static Logger logger = LogManager.getLogger();
  
  private SynchronizationProblemAndSolution() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    logger.info("Not Synchronization Example");
    
    ExecutorService executor = Executors.newFixedThreadPool(2);
    IntStream.range(0, INCREMENT_COUNT)
        .forEach(i -> executor.submit(SynchronizationProblemAndSolution::increment));
    try {
      executor.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException exception) {
      Utils.getException(exception);
      Thread.currentThread().interrupt();
    }
    
    logger.info("Not Synchronized Count = " + count);
    logger.info(Constants.LINE_SEPERATOR);
    
    logger.info("Synchronization Example");
    
    count = 0;
    IntStream.range(0, INCREMENT_COUNT)
        .forEach(i -> executor.submit(SynchronizationProblemAndSolution::syncIncrement));
    executor.shutdown();
    try {
      executor.awaitTermination(3, TimeUnit.SECONDS);
    } catch (InterruptedException exception) {
      Utils.getException(exception);
      Thread.currentThread().interrupt();
    }
    logger.info("Synchronized Count = " + count);

  }
  
  private static void increment() {
    count = count + 1;
  }
  
  private static synchronized void syncIncrement() {
    count = count + 1;
  }

}
