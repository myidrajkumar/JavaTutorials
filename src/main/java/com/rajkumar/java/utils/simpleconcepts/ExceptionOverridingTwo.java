package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Overriding exceptions.
 * 
 * @author Rajkumar
 *
 */
public class ExceptionOverridingTwo {
  
  private static Logger logger = LogManager.getLogger();
  
  private ExceptionOverridingTwo() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    try {
      foo();
    } catch (IOException | NullPointerException exception) {
      logger.error(Utils.getException(exception));
    }
    
    try {
      foo();
    } catch (Exception exception) { // Parent Exception can be specified
      logger.error(Utils.getException(exception));
    }
    
    // try { //NOSONAR
    // foo();
    // } catch (FileNotFoundException e) { Subclass cannot be specified
    // }
  }
  
  /**
   * Just Dummy Method.
   * 
   * @throws IOException eception
   */
  public static void foo() throws IOException {
    // Just example
  }
  
}
