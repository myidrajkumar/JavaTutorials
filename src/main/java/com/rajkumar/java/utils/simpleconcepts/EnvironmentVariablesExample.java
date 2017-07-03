package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

/**
 * Just listing out environment variables.
 * 
 * @author Rajkumar
 *
 */
public class EnvironmentVariablesExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private EnvironmentVariablesExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    System.getenv().entrySet().forEach(logger::info);
    
    // update map is not possible since they are in unmodifiable map
    
    logger.info(Constants.LINE_SEPERATOR);
    
    System.getProperties().entrySet().forEach(logger::info);
    
    logger.info(Constants.LINE_SEPERATOR);
    
  }
  
}
