package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Will the variable's value change affect it's alias??.
 * 
 * @author Rajkumar
 *
 */
public class AliasingExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private AliasingExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String s1 = "abc";
    String s2 = s1;
    
    s1 += "d";
    
    logger.info("S1 = " + s1 + ",  S2 = " + s2);
    
    StringBuilder sb1 = new StringBuilder("abc");
    StringBuilder sb2 = sb1;
    
    sb1.append("d");
    
    logger.info("SB1 = " + sb1 + ",  SB2 = " + sb2);
  }
  
}
