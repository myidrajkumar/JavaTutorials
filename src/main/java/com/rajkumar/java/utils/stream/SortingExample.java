package com.rajkumar.java.utils.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sorting Techniques.
 * 
 * @author Rajkumar
 *
 */
public class SortingExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<Integer> numList = Arrays.asList(4, 8, 2, 5, 3, 1, 2);
    logger.info("Original Numbers ==> " + numList);
    
    List<Integer> sortedNumList = numList.stream().sorted()
        .collect(Collectors.toList());
    logger.info("Sorted Numbers ==> " + sortedNumList);
    
    List<Integer> reverselySortedNumList = numList.stream()
        .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    logger.info("Reversely Sorted Numbers ==> " + reverselySortedNumList);
    
    List<Integer> lambdaSortedNumList = numList.stream()
        .sorted((num1, num2) -> num1.compareTo(num2))
        .collect(Collectors.toList());
    logger.info("Lambda Sorted Numbers ==> " + lambdaSortedNumList);
    
    Collections.sort(numList, (num1, num2) -> num1.compareTo(num2));
    logger.info("Collection API Sorted Numbers ==> " + numList);
    
  }
  
}
