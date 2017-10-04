package com.rajkumar.java.utils.collections;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Joining collections and returning as 'String' example.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsJoiningExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    String joinedString = Stream
        .iterate(Integer.valueOf(0), (Integer integer) -> integer + 1)
        .limit(5)
        .map(integer -> integer.toString())
        .collect(Collectors.joining());
    
    logger.info("Joined String ==> " + joinedString);
    // Did you notice the output is less readable??
    
    String joinedStringByDelimiter = Stream
        .iterate(Integer.valueOf(0), (Integer integer) -> integer + 1)
        .limit(5)
        .map(integer -> integer.toString())
        .collect(Collectors.joining(", "));
    
    logger.info("Joined String By Delimiter==> " + joinedStringByDelimiter);
    
    // Prefix and suffix can be added too.
    String joinedStringByDelimiterWithPrefixPostfix = Stream
        .iterate(Integer.valueOf(0), (Integer integer) -> integer + 1)
        .limit(5)
        .map(integer -> integer.toString())
        .collect(Collectors.joining(", ", "{ ", " }"));
    
    logger.info("Joined String With Postfix and Prefix ==> "
        + joinedStringByDelimiterWithPrefixPostfix);
    
  }
  
}
