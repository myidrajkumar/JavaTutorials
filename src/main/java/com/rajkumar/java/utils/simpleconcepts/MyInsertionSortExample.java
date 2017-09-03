package com.rajkumar.java.utils.simpleconcepts;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Insertion Sort example.
 * 
 * @author Rajkumar
 *
 */
public class MyInsertionSortExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private MyInsertionSortExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    Integer[] values = { 34, 43, null, 12, 21, 18, null, 25, 31, 13};
    doInsertionSort(values);
    
    logger.info(Arrays.toString(values));
    
    Integer[] values1 = null;
    doInsertionSort(values1);
    
    logger.info(Arrays.toString(values1));
     
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> T[] doInsertionSort(T[] values) {
    
    if (values == null) {
      return (T[]) new Comparable[0];
    }
    
    for (int i = 1; i < values.length; i++) {
      int lowestElementIndex = i - 1;
      T currentElement = values[i];
      
      if (currentElement == null) {
        for (int j = i - 1; j >= 0; j--) {
          values[j + 1] = values[j];
        }
        
        values[0] = null;
        continue;
      }
      
      for (int j = i; j < values.length; j++) {
        T checkingElement = values[j];
        
        if (checkingElement != null 
            && checkingElement.compareTo(values[lowestElementIndex]) < 0) {
          lowestElementIndex = j;
        }
      }
      
      T temp = values[lowestElementIndex];
      values[lowestElementIndex] = values[i - 1];
      values[i - 1] = temp;
    }
    return values;
  }
  
}
