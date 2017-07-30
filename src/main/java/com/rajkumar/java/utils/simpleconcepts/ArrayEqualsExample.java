package com.rajkumar.java.utils.simpleconcepts;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * How to compare two arrays?.
 * 
 * @author Rajkumar
 *
 */
public class ArrayEqualsExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ArrayEqualsExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int[] array1 = {3, 5, 9};
    int[] array2 = {3, 5, 9};
    
    boolean equal = array1.equals(array2); //NOSONAR
    logger.info("array1.equals(array2) : " + equal);
    
    // equals will only be true if both are same objects
    equal = array1.equals(array1);   //NOSONAR
    logger.info("array1.equals(array1) : " + equal);
    
    equal = Arrays.equals(array1, array2);
    logger.info("Arrays.equals(array1, array2) : " + equal);
    
    Object[] array3 = {3, 5, new int[] {6, 7, 9}};
    Object[] array4 = {3, 5, new int[] {6, 7, 9}};
    
    equal = Arrays.equals(array3, array4);
    logger.info("Arrays.equals(array3, array4) : " + equal);
    
    equal = Arrays.deepEquals(
        IntStream.of(array1).boxed().toArray(Integer[]::new),
        Arrays.stream(array2).boxed().toArray(Integer[]::new));
    logger.info("Arrays.deepEquals(array1, array2) : " + equal);
    
    equal = Arrays.deepEquals(array3, array4);
    logger.info("Arrays.deepEquals(array3, array4) : " + equal);
    
    // more nested levels
    Object[] array5 = {3, 5, new Object[] {6, 7, new int[] {11, 13}}};
    Object[] array6 = {3, 5, new Object[] {6, 7, new int[] {11, 13}}};
    
    equal = Arrays.deepEquals(array5, array6);
    logger.info("Arrays.deepEquals(array5, array6) : " + equal);
    
  }
  
}
