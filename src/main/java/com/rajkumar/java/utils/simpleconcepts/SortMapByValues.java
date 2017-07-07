package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Sort HashMap by values.
 * 
 * @author Rajkumar
 *
 */
public class SortMapByValues {
  
  private static Logger logger = LogManager.getLogger();
  
  private SortMapByValues() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Map<Integer, String> intMap = new HashMap<>();
    intMap.put(5, "Five");
    intMap.put(1, "One");
    intMap.put(2, "Two");
    intMap.put(8, "Eight");
    intMap.put(6, "Six");
    
    intMap.forEach(
        (key, value) -> logger.info("Key = " + key + ", Value = " + value));
   
    logger.info(Constants.LINE_SEPERATOR);
    
    intMap.entrySet().stream()
    .sorted(( entry1, entry2) ->  entry1.getValue().compareTo(entry2.getValue()))
    .forEach((entry) -> logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
    
  }
  
}
