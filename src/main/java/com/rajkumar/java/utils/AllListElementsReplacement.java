package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This to check java8 replaceAll API.
 * 
 * @author Rajkumar
 *
 */
public class AllListElementsReplacement {
  
  private static Logger logger = LogManager.getLogger();
  
  private AllListElementsReplacement() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    List<String> inputData = getInputData();
    
    replaceByIterator(inputData);
    logger.info(Constants.LINE_SEPERATOR);
    
    listReplaceAll(inputData);
    
  }

  /**
   * By using unary operator as argument, replaces elements.
   * 
   * @param inputData the input data as list
   */
  private static void listReplaceAll(List<String> inputData) {
    logger.info("Approach - By Replace All :");
    
    List<String> clonedData = new ArrayList<>(inputData);
    logger.info("Before conversion: " + clonedData);
    
    clonedData.replaceAll(String::toUpperCase);
    logger.info("After conversion: " + clonedData);
  }
  
  /**
   * Iterate the list elements and capitalize them.
   * 
   * @param inputData the input data as list
   */
  private static void replaceByIterator(List<String> inputData) {
    logger.info("Approach - By using iterator :");
    
    List<String> clonedData = new ArrayList<>(inputData);
    logger.info("Before conversion: " + clonedData);
    
    ListIterator<String> iterator = clonedData.listIterator();
    while (iterator.hasNext()) {
      String value = iterator.next();
      iterator.set(value.toUpperCase());
    }
    
    logger.info("After conversion: " + clonedData);
  }
  
  /**
   * Returns the input data.
   * 
   * @return the input data as List
   */
  private static List<String> getInputData() {
    
    return Arrays.asList("one", "two", "three");
  }
  
}
