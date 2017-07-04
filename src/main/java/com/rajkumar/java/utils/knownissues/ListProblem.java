package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * List cannot be extended / reduced.
 * 
 * @author Rajkumar
 *
 */
public class ListProblem {
  
  private static Logger logger = LogManager.getLogger();
  
  private ListProblem() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String[] arr = {"Val1", "Val2", "Val3"};
    
    List<String> list = Arrays.asList(arr);
    list.add("Val4");
    
    logger.info(list);
  }
  
}
