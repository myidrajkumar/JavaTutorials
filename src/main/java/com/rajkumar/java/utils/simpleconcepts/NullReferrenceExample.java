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
  
  private NullReferrenceExample() { }
  
  public String getMe(String strValue) {
    
    return strValue + " String";
  }
  
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
