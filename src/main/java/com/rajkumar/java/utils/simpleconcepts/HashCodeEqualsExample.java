package com.rajkumar.java.utils.simpleconcepts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hash code equals example.
 * 
 * @author Rajkumar
 *
 */
public class HashCodeEqualsExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private static Example example1 = new Example(10, "Str1");
  private static Example example2 = new Example(20, "Str2");
  private static Example example3 = new Example(30, "Str3");
  private static Example example4 = new Example(10, "Str1");
  private static Example example5 = new Example(20, "Str2");
  private static Example example6 = new Example(10, "Str10");
  private static Example example7 = new Example(20, "Str1");
  private static Example example8 = new Example(10, null);
  
  private static List<Example> exampleList = new ArrayList<>();
  
  private HashCodeEqualsExample() {}
  
  private static class Example implements Comparable<Example> {
    
    private int intValue;
    private String strValue;
    
    /**
     * Always initialize with those.
     * 
     * @param intValue Integer value
     * @param strValue String value
     */
    public Example(int intValue, String strValue) {
      this.intValue = intValue; 
      this.strValue = strValue;
    }
    
    @Override
    public boolean equals(Object obj) {
      //I should not call Objects.equal method since adding null will cause problem
      if (obj == this) {
        return true;
      }
      
      if (obj == null || !(obj instanceof Example)) {
        return false;
      }
      
      Example example = (Example) obj;
      if (intValue != example.intValue) {
        return false;
      }
      
      if (strValue == null && example.strValue == null) {
        return true;
      }
      
      if (strValue != null && example.strValue != null) {
        return strValue.compareTo(example.strValue) == 0 ? true : false;
      }
      
      return false;
      
    }
    
    @Override
    public int hashCode() {
      
      return 31 * intValue + (strValue == null ? 0 : strValue.hashCode());
    }
    
    @Override
    public String toString() {
      
      return "Example { int = " + intValue + ", str = " + strValue + "}";
    }
    
    @Override
    public int compareTo(Example example) {
      
      if (example == null || this.equals(example)) {
        return 0;
      }
      
      if (intValue != example.intValue) {
        return Integer.compare(intValue, example.intValue);
      }
      
      if (strValue == null && example.strValue == null) {
        return 0;
      }
      
      if (strValue != null && example.strValue != null) {
        return strValue.compareTo(example.strValue);
      }
      
      return 0;
      
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    getExampleData(exampleList);
    
    logger.info("List =>" + exampleList);
    logger.info("List Size = " + exampleList.size());
    
    Set<Example> exampleSet = new HashSet<>();
    getExampleData(exampleSet);
    
    logger.info("Set =>" + exampleSet);
    logger.info("Set Size = " + exampleSet.size());
    
    Map<Example, Example> exampleMap = new HashMap<>();
    getExampleDataAsMap(exampleMap);
    
    logger.info("Map =>" + exampleMap);
    logger.info("Map Size = " + exampleMap.size());
    
    // Mutable Problem
    logger.info("Before Key Modication");
    logger.info(" Example1 = " + exampleMap.get(example1));
    
    example1.strValue = "Test";
    
    logger.info("After Key Modication");
    logger.info(" Example1 = " + exampleMap.get(example1));
    
    example1.strValue = "Str1";
    
    Set<Example> exampleTreeSet = new TreeSet<>();
    getExampleData(exampleTreeSet);
    
    logger.info("TreeSet =>" + exampleTreeSet);
    
    exampleList.add(null);
    Collections.sort(exampleList); // Cannot sort if it contains null
    
    logger.info(exampleList);
  }
  
  /**
   * Updating map.
   * 
   * @param exampleMap map to be filled
   */
  private static void getExampleDataAsMap(Map<Example, Example> exampleMap) {
    
    exampleMap.put(example1, example1);
    exampleMap.put(example2, example2);
    exampleMap.put(example3, example3);
    exampleMap.put(example4, example4);
    exampleMap.put(example5, example5);
    exampleMap.put(example6, example6);
    exampleMap.put(example7, example7);
    exampleMap.put(example8, example8);
  }
  
  /**
   * Updating list.
   * 
   * @param exampleList list to be filled
   */
  private static void getExampleData(Collection<Example> exampleList) {
    
    exampleList.add(example1);
    exampleList.add(example2);
    exampleList.add(example3);
    exampleList.add(example4);
    exampleList.add(example5);
    exampleList.add(example6);
    exampleList.add(example7);
    exampleList.add(example8);
    
    if (!(exampleList instanceof TreeSet)) {
      exampleList.add(null);
    }
    
  }
  
}
