package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Timer Example.
 * 
 * @author Rajkumar
 *
 */
public class MySchedluedTimerExample {

  private static Logger logger = LogManager.getLogger();
  
  private MySchedluedTimerExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Timer timer = new Timer(true);

    TimerTask myTimer = new TimerTask() {

      @Override
      public void run() {

        logger.info("Timer task started at: " + new Date());
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException exception) {
          Utils.getException(exception);
          Thread.currentThread().interrupt();
        }
        logger.info("Timer task finished at: " + new Date());

      }
    };

    timer.scheduleAtFixedRate(myTimer, 0, TimeUnit.SECONDS.toSeconds(5));
    logger.info("Timer is started");

    // cancel after sometime
    try {
      TimeUnit.MINUTES.sleep(1);
    } catch (InterruptedException exception) {
      Utils.getException(exception);
      Thread.currentThread().interrupt();
    }

    timer.cancel();
    logger.info("TimerTask cancelled");
  }

}
