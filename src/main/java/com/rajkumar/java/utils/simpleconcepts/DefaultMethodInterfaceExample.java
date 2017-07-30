package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Default Interface Example.
 * 
 * @author Rajkumar
 *
 */
public class DefaultMethodInterfaceExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private interface DefaultMethodInterface {
    
    default void defaultMethod() {
      
      logger.info("Default Interface Method");
    }
  }
  
  private static class Implementor1 implements DefaultMethodInterface {
    
  }
  
  private static class Implementor2 implements DefaultMethodInterface {
    
    @Override
    public void defaultMethod() {
      
      logger.info("Implementor Method");
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Implementor1 def1 = new Implementor1();
    def1.defaultMethod();
    
    logger.info(Constants.LINE_SEPERATOR);
    
    Implementor2 def2 = new Implementor2();
    def2.defaultMethod();
    
  }
  
}
