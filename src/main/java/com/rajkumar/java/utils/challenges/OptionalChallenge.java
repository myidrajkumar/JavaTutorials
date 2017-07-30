package com.rajkumar.java.utils.challenges;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Challenge1.
 * 
 * @author Rajkumar
 *
 */
public class OptionalChallenge {
  
  private static Logger logger = LogManager.getLogger();
  
  private OptionalChallenge() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    List<String> nameList = Arrays.asList("X", "Zero", "Sigma", "Willy");
    
    Optional<String> name1 = nameList.stream().findFirst();
    Optional<String> name2 = nameList.stream().filter(
        "Sigma"::equals).findAny();
    
    logger.info(name1.orElse(""));
    logger.info(name2.orElse("Double"));
    
  }
  
}
