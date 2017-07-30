package com.rajkumar.java.utils.simpleconcepts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Overriding exceptions.
 * 
 * @author Rajkumar
 *
 */
public class ExceptionOverridingOne {
  
  private static Logger logger = LogManager.getLogger();
  
  private ExceptionOverridingOne() {}
  
  static class ParentExceptionClass {
    
    protected void test() throws IOException {  //NOSONAR
      
      logger.info("Will be overridden");
    }
  }
  
  static class ChildExceptionClass extends ParentExceptionClass {
    // @Override
    // protected void test() throws Exception { Parent cannot be specified
    //
    // }  //NOSONAR
    
    @Override
    protected void test() throws FileNotFoundException { 
      // Child can be specified
    
    }
    
    // @Override
    // protected void test() { //Exception can be ignored
    //
    // } //NOSONAR
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    new ChildExceptionClass();
  }
  
}
