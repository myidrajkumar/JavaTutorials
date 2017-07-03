package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Converting arrays to array list.
 * 
 * @author Rajkumar
 *
 */
public class ConvertArraysToArrayList {
  
  private static final String LIST_STRING = "List => ";
  private static final String ARRAY_STRING = "Array => ";
  private static Logger logger = LogManager.getLogger();
  
  private ConvertArraysToArrayList() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Method-1
    Integer[] intArray = {1, 66, 88, 100, 201};
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    
    List<Integer> convertedIntList = Arrays.asList(intArray);
    logger.info(LIST_STRING + convertedIntList);
    
    try {
      // This is not allowed
      convertedIntList.add(250);
    } catch (UnsupportedOperationException exception) {
      logger.error(Utils.getException(exception));
    }
    
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    intArray[0] = 32;
    logger.info("0 index is changed in the array from 1 to 32");
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    logger.info(LIST_STRING + convertedIntList);
    
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    convertedIntList.set(0, 1);
    logger.info("0 index is changed in the list from 32 to 1");
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    logger.info(LIST_STRING + convertedIntList);
    
    logger.info(Constants.LINE_SEPERATOR);
    
    // Method-2
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    
    List<Integer> anotherIntList = new ArrayList<>(Arrays.asList(intArray));
    logger.info(LIST_STRING + anotherIntList);
    
    anotherIntList.add(250);
    
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    intArray[0] = 32;
    logger.info("0 index is changed in the array from 1 to 32");
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    logger.info(LIST_STRING + anotherIntList);
    
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException exception) {
      logger.error(Utils.getException(exception));
      Thread.currentThread().interrupt();
    }
    
    anotherIntList.set(0, 1);
    logger.info("0 index is changed in the list from 32 to 1");
    logger.info(ARRAY_STRING + Arrays.toString(intArray));
    logger.info(LIST_STRING + anotherIntList);
    
    logger.info(Constants.LINE_SEPERATOR);
    
    // Method-3
    // Converting a primitive 'int' array to List
    int[] primitiveIntArray = {1, 11, 111, 1111, 10000};
    List<Integer> integerList = Arrays.stream(primitiveIntArray).boxed()
        .collect(Collectors.toList());
    logger.info(integerList);
    
    // Converting an 'Integer'array to List
    convertedIntList = Arrays.stream(intArray)
        .collect(Collectors.toList());
    logger.info(convertedIntList);
  }
  
}
