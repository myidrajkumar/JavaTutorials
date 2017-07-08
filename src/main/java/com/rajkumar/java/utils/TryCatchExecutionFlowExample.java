package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

/**
 * Try Catch Execution Flow Example.
 * 
 * @author Rajkumar
 *
 */
public class TryCatchExecutionFlowExample {
  
  private TryCatchExecutionFlowExample() { }
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
   
    try {
      logger.info("Try Block");
      
      logger.info(5 / 0); //NOSONAR
    } catch (ArithmeticException exception) {
      logger.info("Catch Block");
      logger.error(Utils.getException(exception));
    } finally {
      logger.info("Finally Block");
    }
    
  }
  
}
