package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


/**
 * This to check java8 replaceAll API.
 * 
 * @author Seenappa
 *
 */
public class AllListElementsReplacement {

  private static Logger logger = LogManager.getLogger();
  
  private AllListElementsReplacement() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    logger.info("Approach1: ");
    List<String> list = Arrays.asList("one", "two", "three");
    logger.info("before modification: " + list);

    ListIterator<String> iterator = list.listIterator();
    while (iterator.hasNext()) {
      String value = iterator.next();
      iterator.set(value.toUpperCase());
    }

    logger.info("after modification: " + list);
    logger.info(Constants.LINE_SEPERATOR);
    
    logger.info("Approach2: ");
    list = Arrays.asList("one", "two", "three");
    logger.info("before modification: " + list);

    list.replaceAll(String::toUpperCase);
    logger.info("after modification: " + list);

  }

}
