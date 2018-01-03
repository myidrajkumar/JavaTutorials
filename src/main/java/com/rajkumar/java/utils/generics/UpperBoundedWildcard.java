package com.rajkumar.java.utils.generics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Upper bound wildcard example.
 * 
 * @author Rajkumar
 *
 */
public class UpperBoundedWildcard {
  
  private static Logger logger = LogManager.getLogger();
  
  private UpperBoundedWildcard() { }
  
  // generics wildcard method to accept any unknown-type
  public static void addArrayListValues(List<? extends Number> any) {
    
    for (Object obj : any) {
      logger.info(obj + " ");
    }
    logger.info("\n");
  }
  
  public static void main(String[] args) {
    
    ArrayList<Integer> intList = new ArrayList<>();
    
    // add to Integer list
    intList.add(10);
    intList.add(30);
    intList.add(70);
    intList.add(90);
    
    // invoke generics method with wildcard
    addArrayListValues(intList);
    
    // create ArrayList of Double
    ArrayList<Double> dblList = new ArrayList<>();
    
    // add to Double list
    dblList.add(10.25);
    dblList.add(20.50);
    dblList.add(30.75);
    dblList.add(40.99);
    
    // invoke generics method with wildcard
    addArrayListValues(dblList);
    
    //Only number and number's subclasses are allowed
  }
}
