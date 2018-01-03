package com.rajkumar.java.utils.generics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unbounded wildcard example.
 * 
 * @author Rajkumar
 *
 */
public class UnboundedWildcard {
  
  private static Logger logger = LogManager.getLogger();
  
  private UnboundedWildcard() {}
  
  // generics wildcard method to accept any unknown-type
  public static void addArrayListValues(List<?> any) {
    
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
    
    // create ArrayList of String
    ArrayList<String> strList = new ArrayList<>();
    
    // add to String list
    strList.add("East");
    strList.add("West");
    strList.add("North");
    strList.add("South");
    
    // invoke generics method with wildcard
    addArrayListValues(strList);
  }
}
