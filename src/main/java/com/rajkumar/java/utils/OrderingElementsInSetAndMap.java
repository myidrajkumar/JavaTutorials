package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Linked Hash Set and Map Example.
 * 
 * @author Rajkumar
 *
 */
public class OrderingElementsInSetAndMap {

  private OrderingElementsInSetAndMap() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    Set<Integer> dataSet = new HashSet<>();
    initializeData(dataSet);

    logger.info("Hash Set Iteration");
    for (Integer intValue : dataSet) {
      logger.info(intValue + " ");
    }

    logger.info(Constants.LINE_SEPERATOR);

    dataSet = new LinkedHashSet<>();
    initializeData(dataSet);

    logger.info("Linked Hash Set Iteration");
    dataSet.forEach(logger::info);

    logger.info(Constants.LINE_SEPERATOR);
    
    //Same applies to map also
    Map<Integer, Integer> dataMap = new LinkedHashMap<>();
    dataMap.put(5,5);
    dataMap.put(3,3);
    dataMap.put(1,1);
    dataMap.put(4,4);
    dataMap.put(2,2);
    dataMap.put(8,8);
    
    logger.info("Linked Hash Map Iteration");
    dataMap.forEach(
        (key, value) -> logger.info("Key = " + key + ", Value = " + value));

  }

  /**
   * Initialize Data.
   * 
   * @param dataSet data to be initialized
   */
  private static void initializeData(Set<Integer> dataSet) {

    dataSet.clear();
    dataSet.add(5);
    dataSet.add(3);
    dataSet.add(1);
    dataSet.add(4);
    dataSet.add(2);
    dataSet.add(8);

  }

}
