package com.rajkumar.java.utils.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Streams Example.
 * 
 * @author Rajkumar
 *
 */
public class StreamExamplesOne {
  
  private StreamExamplesOne() {}
  
  /**
   * Will return true if all input items are matched with our predicate.
   * 
   * @param <T> type
   * @param inputList input list
   * @param predicate test to match
   * 
   * @return boolean status
   */
  public static <T> boolean isMatchAll(List<T> inputList,
      Predicate<T> predicate) {
    
    // Returns true only if all elements are matched.
    return inputList.stream().allMatch(predicate);
  }
  
  /**
   * Will return true if any input item is matched with our predicate.
   * 
   * @param <T> type
   * @param inputList input list
   * @param predicate test to match
   * 
   * @return boolean status
   */
  public static <T> boolean isMatchAny(List<T> inputList,
      Predicate<T> predicate) {
    
    // Returns true only if any element is matched.
    return inputList.stream().anyMatch(predicate);
  }
  
  /**
   * Will return true only if no input item is matched with our predicate.
   * 
   * @param <T> type
   * @param inputList input list
   * @param predicate test to match
   * 
   * @return boolean status
   */
  public static <T> boolean isMatchNone(List<T> inputList,
      Predicate<T> predicate) {
    
    // Returns true only if no element is matched.
    return inputList.stream().noneMatch(predicate);
  }
  
  /**
   * Integer Sum will be found out.
   * 
   * @param inputList List of integers
   * @return Sum of the passed integers list.
   */
  public static int getSum(List<Integer> inputList) {
    
    // Returns addition of list elements.
    return inputList.stream().collect(Collectors.summingInt(i -> i));
  }
  
  /**
   * Smallest entry in the input list will be returned.
   * 
   * @param <T> type
   * 
   * @param inputList List of entries
   * @return smallest item in the input
   */
  public static <T extends Comparable<T>> T getSmallestItem(List<T> inputList) {
    
    // Reduce the input list.
    Optional<T> smallestOptionalCountry = inputList.stream()
        .reduce((item1, item2) -> item1.compareTo(item2) <= 0 ? item1 : item2);
    return smallestOptionalCountry.orElse(null);
  }
  
}
