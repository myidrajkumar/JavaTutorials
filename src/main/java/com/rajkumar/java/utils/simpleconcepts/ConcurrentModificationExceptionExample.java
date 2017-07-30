package com.rajkumar.java.utils.simpleconcepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concurrent Modification Exception.
 * 
 * @author Rajkumar
 *
 */
public class ConcurrentModificationExceptionExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ConcurrentModificationExceptionExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    ArrayList<Integer> intList = new ArrayList<>(
        Arrays.asList(10, 1, 2, 3, 4, 5, 6));
    
    for (Iterator<Integer> ite = intList.listIterator(); ite.hasNext();) {
      Integer value = ite.next();
      
      if (value == 4) {
        intList.remove(4);
        /*
         * ConcurrentModificationException will be thrown
         * So use the operation ==> ite.remove();  //NOSONAR
         */
      }
    }
    
    logger.info(intList);
    
  }
  
}
