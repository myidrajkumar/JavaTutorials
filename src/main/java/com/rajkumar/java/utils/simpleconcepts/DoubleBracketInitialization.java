package com.rajkumar.java.utils.simpleconcepts;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * This avoids ordinary verbose initialization of collections. But this approach
 * should be avoided due to the reasons performance
 * 
 * @author Rajkumar
 *
 */
public class DoubleBracketInitialization {
  
  private static Logger logger = LogManager.getLogger();
  
  private DoubleBracketInitialization() { }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    
    //Ordinary list initialization
    List<String> ordinaryIntializedList = new ArrayList<>();
    ordinaryIntializedList.add("String1");
    ordinaryIntializedList.add("String2");
    
    logger.info("Ordinary List ==> " + ordinaryIntializedList);
    
    List<String> doubleBracketList = new ArrayList<>() {
      
      /**
      * 
      */
      private static final long serialVersionUID = -1648005136352142770L;
      
      {   //NOSONAR    
        /*
         * This strategy should be avoided. The outer brace creates subclass of
         * ArrayList and inner brace initializes the 'add' statements.
         */
        add("String1");
        add("String2");
      }
    };
    logger.info("Double Bracketted List ==> " + doubleBracketList);
  }
  
}
