package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * For Each Consumer example.
 * 
 * @author Rajkumar
 *
 */
public class ForEachExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ForEachExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Map<Integer, Integer> map = new HashMap<>();
    // this loop is to add key and value to Map
    for (int i = 0; i < 15; i++) {
      map.put(i, i + i);
    }
    
    map.forEach((key, value) -> logger.info("Key: " + key + ", Value: " + value));
    
    logger.info(Constants.LINE_SEPERATOR);
    
    List<Integer> list = new ArrayList<>();
    
    for (int i = 0; i < 15; i++) {
      list.add(i);
    }
    
    list.forEach(logger::info);
    
    logger.info(Constants.LINE_SEPERATOR);
    
    // Filtering value
    List<Integer> newList = list.stream()
        .filter(u -> u > 10)
        .collect(Collectors.toList());
    
    newList.forEach(logger::info);
  }
  
}
