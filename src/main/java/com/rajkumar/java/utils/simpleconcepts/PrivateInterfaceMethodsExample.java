package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Interfaces can have private methods.
 * 
 * @author Rajkumar
 *
 */
public class PrivateInterfaceMethodsExample {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    PrivateInterface pi = () -> logger.info("Hello Private");
    pi.defaultPublicMethod();
    
  }
  
  
  @FunctionalInterface
  private interface PrivateInterface {
    
    public void simpleApi();
    
    public default void defaultPublicMethod() {
      defaultPrivateMethod();
      logger.info("public method is called");
    }

    private void defaultPrivateMethod() {
      logger.info("Private method is called");
    }
  }
  
}
