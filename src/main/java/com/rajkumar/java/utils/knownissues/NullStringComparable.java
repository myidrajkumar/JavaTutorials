package com.rajkumar.java.utils.knownissues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Null should not be present when comparable sorting.
 * 
 * @author Rajkumar
 *
 */
public class NullStringComparable {
  
  private static Logger logger = LogManager.getLogger();
  
  private NullStringComparable() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    List<String> strList = new ArrayList<>();
    
    strList.add("str1");
    strList.add(null);
    
    Collections.sort(strList);
    logger.info(strList);
  }
  
}
