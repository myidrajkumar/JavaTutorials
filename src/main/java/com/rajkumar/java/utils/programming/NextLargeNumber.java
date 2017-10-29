package com.rajkumar.java.utils.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;

/**
 * To Find out next larger number from the input.
 * 
 * @author Rajkumar
 *
 */
public class NextLargeNumber {
  
  private NextLargeNumber() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    LogManager.getLogger().info("5963 => " + getNextLarger(5963));
    LogManager.getLogger().info("5963 => " + getLargest(5963));
    
  }
  
  /**
   * Approach ==>
   * 1. Get all the digits and add in the same order as it appears
   * 2. To get the immediate next large number, start from the end number
   * 3. See if the number is higher than it's immediate previous digit
   * 4. If so, swap the numbers and sort the numbers from that index.
   * 
   * @param number number
   * @return the next larger number
   */
  private static int getNextLarger(int number) {
    
    List<Integer> digits = numberToDigits(number);
    int rightBiggerIndex = -1;
    for (int i = digits.size() - 1; i > 0; i--) {
      if (digits.get(i) > digits.get(i - 1)) {
        rightBiggerIndex = i;
        break;
      }
    }
    if (rightBiggerIndex != -1) {
      swap(digits, rightBiggerIndex, rightBiggerIndex - 1);
      
      Integer[] digitsArray = digits.toArray(new Integer[0]);
      Arrays.sort(digitsArray, rightBiggerIndex, digits.size());
      
      digits.clear();
      digits.addAll(List.of(digitsArray));
    }
    return digitsToNumber(digits);
  }
  
  /**
   * Approach ==>
   * Get all the digits and sort the numbers.
   * 
   * @param number number
   * @return the largest number
   */
  private static int getLargest(int number) {
    
    List<Integer> digits = numberToDigits(number);
    
    Integer[] digitsArray = digits.toArray(new Integer[0]);
    Arrays.sort(digitsArray, 0, digits.size(), Collections.reverseOrder());
    
    digits.clear();
    digits.addAll(List.of(digitsArray));
    
    return digitsToNumber(digits);
  }
  
  private static List<Integer> numberToDigits(int number) {
    
    List<Integer> digits = new ArrayList<>();
    while (number > 0) {
      digits.add(0, number % 10);
      number /= 10;
    }
    return digits;
  }
  
  private static int digitsToNumber(List<Integer> digits) {
    
    int number = 0;
    for (Integer digit : digits) {
      number *= 10;
      number += digit;
    }
    return number;
  }
  
  private static void swap(List<Integer> digits, int i, int j) {
    
    Integer temp = digits.get(i);
    digits.set(i, digits.get(j));
    digits.set(j, temp);
  }
  
}
