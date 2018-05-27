package com.rajkumar.java.utils.threads;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Scheduled Executor Service Example.
 * 
 * @author Rajkumar
 *
 */
public class ScheduledExecutorServiceExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private static final long INITIAL_DELAY = 1L;
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    Runnable firstRunnable = () -> logger.info("After 1 second, Runnable task will be executed");
    executor.schedule(firstRunnable, INITIAL_DELAY, TimeUnit.SECONDS);
    
    Callable<String> callable = () -> "After one second, this Callable task will be executed";
    ScheduledFuture<String> schdFuture = executor.schedule(callable, 1, TimeUnit.SECONDS);
    try {
      logger.info("Scheduled Future => " + schdFuture.get());
    } catch (InterruptedException | ExecutionException exception) {
      logger.error(Utils.getException(exception));
    }
    
    Future<String> future = executor.schedule(callable, 1, TimeUnit.SECONDS);
    try {
      logger.info("Future => " + future.get());
    } catch (InterruptedException | ExecutionException exception) {
      logger.error(Utils.getException(exception));
    }
    
    Runnable secondRunnable = () -> logger.info("After every second, this task will be executed");
    executor.scheduleAtFixedRate(secondRunnable, INITIAL_DELAY, 1, TimeUnit.SECONDS);

    Runnable thirdRunnable = () -> logger.info("After every second, "
        + "this task will be executed with fixed delay");
    executor.scheduleWithFixedDelay(thirdRunnable, INITIAL_DELAY, 1, TimeUnit.SECONDS);

    try {
      TimeUnit.SECONDS.sleep(INITIAL_DELAY * 10);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
   
    executor.shutdown();
  }
}

