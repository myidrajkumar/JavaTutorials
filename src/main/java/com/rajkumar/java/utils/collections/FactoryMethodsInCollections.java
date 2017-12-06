package com.rajkumar.java.utils.collections;

import com.rajkumar.java.utils.lib.Utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory methods to initialize collections. 
 * 1. They are structurally immutable. Elements cannot be added, removed, or replaced. 
 *    If we try, always UnsupportedOperationException will be thrown. 
 * 2. No null elements. Attempts to create them with null elements result in NullPointerException. 
 * 3. For Lists, The list element order is same as the order of the provided arguments. 
 *    For Sets, Unspecified 
 * 4. For Sets, will reject duplicate elements at creation time. 
 *    If we try to add to existing set, throws IllegalArgumentException. 
 * 5. They are serializable if all elements are serializable.
 * 
 * @author Rajkumar
 *
 */
public class FactoryMethodsInCollections {
  
  private static final String PHILIP = "Philip";
  private static final String PETER = "Peter";
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    displayList();
    
    // In sets and maps, already shown general characters are ignored
    displaySet();
    
    displayMap();
    
  }
  
  /**
   * Map characteristics.
   */
  private static void displayMap() {
    
    Map<Integer, String> immutableMap = Map.of(1, PETER, 2, "Gerhard");
    logger.info("Immutable Map = " + immutableMap);
    
    Map<Integer, String> mapEntries = Map.ofEntries(Map.entry(1, PETER),
        Map.entry(2, "Gerhad"), Map.entry(3, PHILIP));
    
    logger.info("Immutable Map Entries= " + mapEntries);
  }
  
  /**
   * Set characteristics.
   */
  private static void displaySet() {
    
    try {
      Set.of(PETER, PHILIP, PETER);
    } catch (IllegalArgumentException exception) {
      logger.info(Utils.getException(exception));
      logger.info("Cannot add duplicate element in set at creation time");
      // If you try to add, UnsupportedOperationException will be thrown.
    }
  }
  
  /**
   * List characteristics.
   */
  private static void displayList() {
    
    // Creating empty list
    List<String> emptyList = List.of();
    
    // Creating immutable list
    List<String> immutableList = List.of(PETER, PHILIP);
    
    logger.info("emptyList = " + emptyList);
    logger.info("Immutable List = " + immutableList);
    
    // Try using null
    try {
      
      List.of(PETER, null, PHILIP);
      
    } catch (NullPointerException exception) {
      logger.info(Utils.getException(exception));
      logger.info("nulls are not allowed in List.of()");
    }
    
    // Try adding an element to immutable list
    try {
      
      immutableList.add("Gerhard");
      
    } catch (UnsupportedOperationException exception) {
      logger.info(Utils.getException(exception));
      logger.info("Cannot add an element to the immutable List");
    }
    
    // Try removing an element from immutable List
    try {
      
      immutableList.remove(1);
    } catch (UnsupportedOperationException exception) {
      logger.info(Utils.getException(exception));
      logger.info("Cannot remove an element from immutable List");
    }
  }
  
}
