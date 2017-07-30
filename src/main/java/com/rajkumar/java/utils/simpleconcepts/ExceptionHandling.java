package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception handling.
 * 
 * @author Rajkumar
 *
 */
public class ExceptionHandling {
  
  private static Logger logger = LogManager.getLogger();
  
  private ExceptionHandling() {}
  
  static class ParentException extends Exception {
    
    private static final long serialVersionUID = -1304555747528167406L;
  }
  
  static class ChildException extends ParentException {

    private static final long serialVersionUID = 4818971207352723549L;
  }
  
  static class ExceptionThrower {
    
    void method1() throws ParentException {
      
      throw new ParentException();
      // throw new ChildException(); - Allowed
    }
    
    void method2() throws ChildException {
      
      // throw new ParentException(); - Not Allowed
      throw new ChildException();
    }
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    ExceptionThrower exceptionThrower = new ExceptionThrower();
    
    try {
      exceptionThrower.method1();
    } catch (ParentException exception) {
      logger.error(Utils.getException(exception));
    } 
    
  }
}
