package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Example about LinkedHashSet and LinkedHashMap.
 * 
 * @author Rajkumar
 *
 */
public class OrderingElementsInSetAndMap {
  
  private static Logger logger = LogManager.getLogger();
  
  private OrderingElementsInSetAndMap() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    doSetOperation();
    
    doMapOperation();
    
  }

  private static void doSetOperation() {
    
    Set<Integer> dataSet = new HashSet<>();
    initializeSet(dataSet);
    
    logger.info("Hash Set Iteration");
    dataSet.forEach(logger::info);
    
    logger.info(Constants.LINE_SEPERATOR);
    
    dataSet = new LinkedHashSet<>();
    initializeSet(dataSet);
    
    logger.info("Linked Hash Set Iteration");
    dataSet.forEach(logger::info);
    
    logger.info(Constants.LINE_SEPERATOR);
  }
  
  private static void doMapOperation() {
    
    Map<Integer, Integer> dataMap = new HashMap<>();
    initializeMap(dataMap);
    
    logger.info("Hash Map Iteration");
    dataMap.forEach((key, value) -> logger.info(key + " = " + value));
    
    logger.info(Constants.LINE_SEPERATOR);
    
    dataMap = new LinkedHashMap<>();
    initializeMap(dataMap);
    
    logger.info("Linked Hash Map Iteration");
    dataMap.forEach((key, value) -> logger.info(key + " = " + value));
    
    logger.info(Constants.LINE_SEPERATOR);
  }
  
  /**
   * Initialize the data.
   * 
   * @param dataSet set to be filled
   */
  private static void initializeSet(Set<Integer> dataSet) {
    
    dataSet.clear();
    dataSet.add(5);
    dataSet.add(3);
    dataSet.add(1);
    dataSet.add(4);
    dataSet.add(2);
    dataSet.add(8);
  }
  
  /**
   * Initialize the data.
   * 
   * @param dataMap map to be filled
   */
  private static void initializeMap(Map<Integer, Integer> dataMap) {
    
    dataMap.clear();
    dataMap.put(5,5);
    dataMap.put(3,3);
    dataMap.put(1,1);
    dataMap.put(4,4);
    dataMap.put(2,2);
    dataMap.put(8,8);
  }
  
}
