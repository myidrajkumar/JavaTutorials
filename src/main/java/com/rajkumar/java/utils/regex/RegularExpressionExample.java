package com.rajkumar.java.utils.regex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular expression example.
 * 
 * @author Rajkumar
 *
 */
public class RegularExpressionExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private RegularExpressionExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String example = "TextExam contains test steps";
    
    Pattern matchPattern = Pattern.compile(".*test.*");
    Pattern nonmatchPattern = Pattern.compile("test");
    
    //This will look for exact match with the argument 
    Matcher nonmatcher = nonmatchPattern.matcher(example);
    logger.info("Is matches => " + nonmatcher.matches());
    
    //Sending regular expression 
    Matcher matcher = matchPattern.matcher(example);
    logger.info("Is matches => " + matcher.matches());
    
    
    logger.info("Is Looking At => " + nonmatcher.lookingAt());
    logger.info("Is Looking At => " + matcher.lookingAt());  // means Starting at
    
    boolean isFound = false;
    while (nonmatcher.find()) {
      logger.info("Start => " + nonmatcher.start());
      logger.info("End => " + nonmatcher.end());
      isFound = true;
    }
    
    if (!isFound) {
      logger.info("Oops, No records found");
    }
    
    isFound = false;
    while (matcher.find()) {
      logger.info("Start => " + matcher.start());
      logger.info("End => " + matcher.end());
      isFound = true;
    }
    
    if (!isFound) {
      logger.info("Oops, No records found");
    }
    
    nonmatcher = nonmatchPattern.matcher(example);
    matcher = matchPattern.matcher(example);
    
    nonmatcher.find();
    logger.info("Group => " + nonmatcher.group());
    
    matcher.find();
    logger.info("Group => " + matcher.group());
  }
  
}
