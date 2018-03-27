package com.rajkumar.java.utils.generics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lower bound wild card example.
 * 
 * @author Rajkumar
 *
 */
public class LowerBoundedWildcard {
  
  private static Logger logger = LogManager.getLogger();
  
  private LowerBoundedWildcard() { }
  
  /**
   * generics wildcard method to accept any unknown-type.
   * 
   * @param any any list which accepts only numbers
   */
  public static void addArrayListValues(List<? super Integer> any) {
    
    for (Object obj : any) {
      logger.info(obj + " ");
    }
    logger.info("\n");
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    List<Integer> intList = new ArrayList<>();
    
    // add to Integer list
    intList.add(10);
    intList.add(30);
    intList.add(70);
    intList.add(90);
    
    // invoke generics method with wildcard
    addArrayListValues(intList);
    
    //Only integer and integer's super classes are allowed
  }
}
