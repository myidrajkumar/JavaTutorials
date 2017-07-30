package com.rajkumar.java.utils.simpleconcepts;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Currency format based on locale.
 * 
 * @author Rajkumar
 *
 */
class CurrencyLocaleExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private CurrencyLocaleExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String... args) {
    
    logger.info("Local Currency: "
        + NumberFormat.getCurrencyInstance().format(295));
    logger.info("US Currency: "
        + NumberFormat.getCurrencyInstance(Locale.US).format(295));
    logger.info("German Currency: "
        + NumberFormat.getCurrencyInstance(Locale.GERMANY).format(295));
    logger.info("Chinese Currency: "
        + NumberFormat.getCurrencyInstance(Locale.CHINESE).format(295));
  }

}
