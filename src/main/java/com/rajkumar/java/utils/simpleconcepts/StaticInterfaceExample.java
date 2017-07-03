package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Static Interface example.
 * 
 * @author Rajkumar
 *
 */
public interface StaticInterfaceExample {  //NOSONAR
  
  public static Logger logger = LogManager.getLogger();
  
  public static void testStaticMethod() {
    
    logger.info("Static Test");
  }
  
  public default void testDefaultMethod() {
    
    logger.info("Default Test");
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Note the interface can be executed
    logger.info("Main Interface Test ");
    
    testStaticMethod();
    
  }
}
