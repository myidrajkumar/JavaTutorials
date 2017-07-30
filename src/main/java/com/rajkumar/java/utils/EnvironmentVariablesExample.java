package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Example of environment variables.
 * 
 * @author Rajkumar
 *
 */
public class EnvironmentVariablesExample {

  private EnvironmentVariablesExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    System.getenv().forEach((key, value) -> logger.info(key + " = " + value));
    // The update map is not possible since environment are in unmodifiable map
    logger.info(Constants.LINE_SEPERATOR);

    System.getProperties().forEach((key, value) -> logger.info(key + " = " + value));
    logger.info(Constants.LINE_SEPERATOR);

  }

}
