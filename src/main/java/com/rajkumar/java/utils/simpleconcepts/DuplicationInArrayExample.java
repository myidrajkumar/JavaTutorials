package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Finding duplications array list.
 * 
 * @author Rajkumar
 *
 */
public class DuplicationInArrayExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private DuplicationInArrayExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    int[] duplicatedNum = {2, 4, 1, 3, 6, 7, 1, 2, 3, 8, 2};
    
    // Sol1 => Sort Array and check if immediate neighbor same as current
    
    // Sol2 => Do linear search
    doLinearSearch(duplicatedNum);
    
    // Sol3 => Try to add to HashSet. If not added, then it's duplicateNumber
    Set<Integer> numSet = new HashSet<>();
    logger.info("Duplicated Numbers  ==> ");
    for (int num : duplicatedNum) {
      if (!numSet.add(num)) {
        logger.info(num);
      }
    }
    
  }
  
  /**
   * Linear search to get duplicate numbers.
   * 
   * @param duplicatedNum array which is having duplicated entries
   */
  private static void doLinearSearch(int[] duplicatedNum) {
    
    int arrayLen = duplicatedNum.length - 1;
    int finalNumIndexToCheck = arrayLen - 1; // optimized code
    
    for (int searchIndex = 0; searchIndex < finalNumIndexToCheck; searchIndex++) {
      
      int searchVal = duplicatedNum[searchIndex];
      
      logger.info("Search Value " + searchVal + " is Found At => ");
      for (int arrayElementIndex = searchIndex
          + 1; arrayElementIndex <= arrayLen; arrayElementIndex++) {
        
        if (searchVal == duplicatedNum[arrayElementIndex]) {
          logger.info(arrayElementIndex);
        }
      }
      
      logger.info("\n\n");
    }
  }
  
}
