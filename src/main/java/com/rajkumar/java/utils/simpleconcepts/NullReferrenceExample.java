package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * What will happen when overloaded methods present and argument is null?.
 * 
 * @author Rajkumar
 *
 */
public class NullReferrenceExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private NullReferrenceExample() {}
  
  /**
   * Just returning some string.
   * 
   * @param strValue input argument
   * 
   * @return input argument with appended characters
   */
  public String getMe(String strValue) {
    
    return strValue + " String";
  }
  
  /**
   * Just returning some string.
   * 
   * @param objValue input argument
   * 
   * @return input argument with appended characters
   */
  public String getMe(Object objValue) {
    
    return objValue.toString() + " Object";
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    NullReferrenceExample nullRef = new NullReferrenceExample();
    logger.info(nullRef.getMe(null));
    // The most specific type will be called,
  }
  
}
