package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Default Interface Issues Example.
 * 
 * @author Rajkumar
 *
 */
public class DefaultMethodsInInterfacesProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    final String message = "Hello";
    
    FirstInterface firstInterface = new MultiInterfaceImplementer();
    firstInterface.log(message);
    
    SecondInterface secondInterface = new MultiInterfaceImplementer();
    secondInterface.log(message);
    
    ExtenedInterface extInterface = new MultipleInterfaceExtender();
    extInterface.log(message);
  }
  
  private interface FirstInterface {
    
    default void log(String message) {
      
      logger.info("First Interface = " + message);
    }
  }
  
  private interface SecondInterface {
    
    default void log(String message) {
      
      logger.info("Second Interface = " + message);
    }
  }
  
  @FunctionalInterface
  private interface ExtenedInterface extends FirstInterface, SecondInterface {
    
    @Override
    public void log(String message); // If extended interfaces contains same
                                     // methods, then those have to be declared
  }
  
  private static class MultiInterfaceImplementer
      implements
        FirstInterface,
        SecondInterface {
    
    /* This method is mandatory to avoid diamond problem
     * else compilation error will occur
     */
    @Override
    public void log(String message) {
      
      logger.info("Extended Interface1 = " + message);
    }
    
  }
  
  private static class MultipleInterfaceExtender implements ExtenedInterface {
    
    /* This method is mandatory to avoid diamond problem
     * else compilation error will occur
     */
    @Override
    public void log(String message) {
      
      logger.info("Extended Interface2 = " + message);
    }
    
  }
  
}
