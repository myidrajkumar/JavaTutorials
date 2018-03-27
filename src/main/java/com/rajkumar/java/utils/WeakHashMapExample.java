package com.rajkumar.java.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * WeakHashMap example to understand.
 * 
 * @author Rajkumar
 *
 */
public class WeakHashMapExample {

  private WeakHashMapExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    Map<String, String> hashmapObject = new HashMap<>();
    Map<String, String> weakhashmapObject = new WeakHashMap<>();
    
    // Created HashMap and WeakHashMap keys
    String hashmapKey = new String("hashmapkey");  //NOSONAR
    String weakhashmapKey = new String("weakhashmapkey"); //NOSONAR

    // Created HashMap and WeakHashMap values
    String hashmapValue = "hashmapvalue";
    String weakhashmapValue = "weakhashmapvalue";  

    // Putting key and value in HashMap and WeakHashMap Object
    hashmapObject.put(hashmapKey, hashmapValue);
    weakhashmapObject.put(weakhashmapKey, weakhashmapValue);

    // Print HashMap and WeakHashMap Object : Before Garbage Collection
    logger.info("HashMap before Garbage Collected :" + hashmapObject);
    logger.info("WeakHashMap before Garbage Collected :" + weakhashmapObject);

    // Set HashMap and WeakHashMap Object keys to null
    hashmapKey = null;  //NOSONAR
    weakhashmapKey = null; //NOSONAR

    // Calling Garbage Collection
    System.gc();  //NOSONAR

    // Print HashMap and WeakHashMap Object : After Garbage Collection
    logger.info("HashMap after Garbage Collected :" + hashmapObject);
    logger.info("WeakHashMap after Garbage Collected :" + weakhashmapObject); 

  }

}
