package com.rajkumar.java.utils.simpleconcepts;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lambda example.
 * 
 * @author Rajkumar
 *
 */
public class LambdaExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private LambdaExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Comparator<String> anonymousComparator = new Comparator<String>() { // NOSONAR
      
      @Override
      public int compare(String o1, String o2) {
        
        return o1.compareTo(o2);
      }
    };
    
    List<String> nameList = Arrays.asList("Raj", "Kumar", "Sasi", "Machi");
    logger.info("Before Sorting \n " + nameList);
    
    Collections.sort(nameList, anonymousComparator);
    logger.info("After Anonymous Sorting \n " + nameList);
    
    Collections.shuffle(nameList);
    logger.info("Now List is shuffled to do Lambda Sorting \n " + nameList);
    
    Comparator<String> lambdaComparator = (o1, o2) -> o1.compareTo(o2);
    Collections.sort(nameList, lambdaComparator);
    logger.info("After Lambda Sorting \n " + nameList);
    
    Collections.shuffle(nameList);
    logger.info("Now List is shuffled \n " + nameList);
    
    nameList.sort(lambdaComparator);
    logger.info("After List Lambda Sorting \n " + nameList);
    
    Collections.shuffle(nameList);
    logger.info("Now List is shuffled \n " + nameList);
    
    nameList.sort(lambdaComparator.reversed());
    logger.info("After List Lambda Reverse Sorting \n " + nameList);
    
    nameList.forEach(logger::info);
  }
  
}
