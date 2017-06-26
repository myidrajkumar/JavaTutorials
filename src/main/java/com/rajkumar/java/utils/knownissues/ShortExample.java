
package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Since set contain short values, ints will not be removed.
 * 
 * @author Rajkumar
 *
 */
public class ShortExample {

  private ShortExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    Set<Short> dataSet = new HashSet<>();

    for (short i = 0; i < 10; i++) {
      dataSet.add(i);
    }
    
    logger.info("Set Size = " + dataSet.size());
    
    dataSet.remove(2); // NOSONAR
    //Integers so will not be removed
    
    logger.info("Set Size Now Also= " + dataSet.size());
  }

}
