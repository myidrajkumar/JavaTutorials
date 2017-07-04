package com.rajkumar.java.utils.bettersolution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Arrays;

/**
 * Finding maximum and minimum.
 * 
 * @author Rajkumar
 *
 */
public class FindingMaxAndMin {
  
  private static Logger logger = LogManager.getLogger();
  
  private FindingMaxAndMin() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    long[] numbersArray = {4, 3, 12, 3, -2, 6, 9, 2, 10, 11};
    
    // Compare Each Number Against Min and Max. Time Comparison = 2 * n
    bruteForce(numbersArray);
    
    // Compare Numbers In Pairs. Time Comparison = 1.5 * n
    pairCompare(numbersArray);
  }
  
  /**
   * Getting minimum number by comparing minimum against passed number.
   * 
   * @param minNumber already computed minimum
   * @param nextNumber number to be checked against minimum
   * 
   * @return minimum number
   */
  private static <T extends Comparable<T>> T getMinNumber(T minNumber, T nextNumber) {
    
    T result = minNumber;
    if (nextNumber.compareTo(minNumber) < 0) {
      result = nextNumber;
    }
    return result;
  }
  
  /**
   * Getting maximum number by comparing maximum against passed number.
   * 
   * @param maxNumber already computed maximum
   * @param currentNumber number to be checked against maximum
   * 
   * @return maximum number
   */
  private static <T extends Comparable<T>> T getMaxNumber(T maxNumber, T currentNumber) {
    
    T result = maxNumber;
    if (currentNumber.compareTo(maxNumber) > 0) {
      result = currentNumber;
    }
    return result;
  }
  
  private static <T extends Comparable<T>> void pairCompare(T[] numbersArray) {
    
    logger.info("***************** Pair Comparison ****************");
    if (isEmpty(numbersArray)) {
      return;
    }
    
    T minNumber = numbersArray[0];
    T maxNumber = numbersArray[0];
    
    int halfArrayLength = numbersArray.length / 2;
    int index = 0;
    for (; index < halfArrayLength; index = index + 2) {
      T currentNumber = numbersArray[index];
      T nextNumber = numbersArray[index + 1];
      
      if (currentNumber.compareTo(nextNumber) >= 0) {
        
        maxNumber = getMaxNumber(maxNumber, currentNumber);
        minNumber = getMinNumber(minNumber, nextNumber);
      } else {
        minNumber = getMinNumber(minNumber, currentNumber);
        maxNumber = getMaxNumber(maxNumber, nextNumber);
      }
    }
    
    if (index * 2 < numbersArray.length) {
      T currentNumber = numbersArray[index * 2];
      minNumber = getMinNumber(minNumber, currentNumber);
      
      maxNumber = getMaxNumber(maxNumber, currentNumber);
    }
    
    logger.info("Max Number = " + maxNumber + ", Min Number = " + minNumber);
    logger.info(Constants.LINE_SEPERATOR);
  }
  
  /**
   * Pair comparison for integer primitives.
   * 
   * @param numbersArray numbers
   */
  public static void pairCompare(int[] numbersArray) {
    
    Integer[] num = Arrays.stream(numbersArray).boxed().toArray(Integer[]::new);
    pairCompare(num);
  }
  
  /**
   * Pair comparison for long primitives.
   * 
   * @param numbersArray numbers
   */
  public static void pairCompare(long[] numbersArray) {
    
    Long[] num = Arrays.stream(numbersArray).boxed().toArray(Long[]::new);
    pairCompare(num);
  }
  
  /**
   * Pair comparison for double primitives.
   * 
   * @param numbersArray numbers
   */
  public static void pairCompare(double[] numbersArray) {
    
    Double[] num = Arrays.stream(numbersArray).boxed().toArray(Double[]::new);
    pairCompare(num);
  }
  
  private static <T extends Comparable<T>> void bruteForce(T[] numbersArray) {
    
    logger.info("***************** Brute Force Method ****************");
    if (isEmpty(numbersArray)) {
      return;
    }
    
    T minNumber = numbersArray[0];
    T maxNumber = numbersArray[0];
    
    int arrayLength = numbersArray.length;
    for (int i = 1; i < arrayLength; i++) {
      T currentNumber = numbersArray[i];
      if (minNumber.compareTo(currentNumber) > 0) {
        minNumber = currentNumber;
      }
      
      if (maxNumber.compareTo(currentNumber) < 0) {
        maxNumber = currentNumber;
      }
    }
    
    logger.info("Max Number = " + maxNumber + ", Min Number = " + minNumber);
    logger.info(Constants.LINE_SEPERATOR);
  }
  
  /**
   * Brute force comparison for integer primitives.
   * 
   * @param numbersArray numbers
   */
  public static void bruteForce(int[] numbersArray) {
    
    Integer[] num = Arrays.stream(numbersArray).boxed().toArray(Integer[]::new);
    bruteForce(num);
  }
  
  /**
   * Brute force comparison for long primitives.
   * 
   * @param numbersArray numbers
   */
  public static void bruteForce(long[] numbersArray) {
    
    Long[] num = Arrays.stream(numbersArray).boxed().toArray(Long[]::new);
    bruteForce(num);
  }
  
  /**
   * Brute force comparison for double primitives.
   * 
   * @param numbersArray numbers
   */
  public static void bruteForce(double[] numbersArray) {
    
    Double[] num = Arrays.stream(numbersArray).boxed().toArray(Double[]::new);
    bruteForce(num);
  }
  
  /**
   * Checking whether empty or not.
   * 
   * @param numbersArray numbers
   */
  private static <T extends Comparable<T>> boolean isEmpty(T[] numbersArray) {
    
    if (numbersArray == null) {
      logger.info("No numbers present in the array..");
      logger.info(Constants.LINE_SEPERATOR);
      return true;
    }
    
    return false;
  }
}
