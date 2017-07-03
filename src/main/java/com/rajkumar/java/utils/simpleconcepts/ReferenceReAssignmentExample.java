package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * References re-assigned with different values example.
 * 
 * @author Rajkumar
 *
 */
public class ReferenceReAssignmentExample {
  
  private String name = "Raj";
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    ReferenceReAssignmentExample refExample = new ReferenceReAssignmentExample();
    logger.info("Initialized Value = " + refExample.name);
    
    refExample.name = "Rajkumar";
    logger.info("Modified Immediately = " + refExample.name);
    
    changeValue(refExample);
    logger.info("After Method returns = " + refExample.name);
    
  }
  
  /**
   * Change value.
   * 
   * @param refExample class reference value to be modified
   */
  private static void changeValue(ReferenceReAssignmentExample refExample) {
    
    logger.info("Modified value in method = " + refExample.name);
    
    refExample.name = "Rajkumar.S";
    logger.info("Modifying inside method = " + refExample.name);
    
    logger.info("Now, This will be re-assigned");
    
    refExample = new ReferenceReAssignmentExample(); // NOSONAR
    logger.info("After ReAssignment = " + refExample.name);
    
    refExample.name = "Rajkumar Seenappa";
    logger.info("After ReAssignment Modified = " + refExample.name);
    
  }
  
}
