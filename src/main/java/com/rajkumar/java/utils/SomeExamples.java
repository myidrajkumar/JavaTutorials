package com.rajkumar.java.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * General examples and planning to move these.
 * 
 * @author Rajkumar
 *
 */
public class SomeExamples {
  
  private static Logger logger = LogManager.getLogger();
  private static final String KEY = "Key1";
  
  private SomeExamples() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    complexCollectionExample();
    understandNullReferenceTypeExample();
    
  }
  
  private static void understandNullReferenceTypeExample() {
    
    if (null instanceof Object) {  //NOSONAR
      logger.debug("Object");
    }
    
    if (null instanceof AtomicInteger) {  //NOSONAR
      logger.debug("AtomicInteger");
    }
    
    if (null instanceof StackOverflowError) {  //NOSONAR
      logger.debug("StackOverflowError");
    }
    
    logger.debug(
        "Null does not belong to 'Object' / 'AtomicInteger or other Objects' /"
        + " 'StackOverflowError or other Errors' reference types.");
    
  }
  
  private static void complexCollectionExample() {
    
    Map<String, Map<String, String>> crazyMap = new HashMap<>();
    
    crazyMap.put(KEY, new HashMap<>());
    crazyMap.put(KEY, new HashMap<>());
    crazyMap.put(KEY, new HashMap<>());
    
    List<Map<String, Map<String, String>>> crazyList = new ArrayList<>();
    crazyList.add(crazyMap);
    
    logger.debug(crazyList.get(0).size());
  }
  
}
