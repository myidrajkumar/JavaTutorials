package com.rajkumar.java.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Synchronized Collections example.
 * 
 * <p>
 * Rather than this version, always use latest concurrent version.
 * ConcurrentHashMap, CopyOnWriteArrayList...
 * </p>
 * 
 * @author Rajkumar
 *
 */
public class SynchronizedCollectionExample {
  
  private SynchronizedCollectionExample() { }

  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
    syncMap.put("String1", "String1");
    syncMap.put("String2", "String2");
    syncMap.put("String3", "String3");
    
    syncMap.forEach((key, value) -> logger.info("Key = " + key + ", Value = " + value));
    
  }
  
}
