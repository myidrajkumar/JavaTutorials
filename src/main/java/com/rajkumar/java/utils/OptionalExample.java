package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Avoiding NullPointer Exception.
 * 
 * @author Rajkumar
 *
 */
public class OptionalExample {

  private static final String NULL_OPTIONAL_VALUE_STRING = "Null Optional value also will be NULL."
      + " So we are";
  private static Logger logger = LogManager.getLogger();
  
  private OptionalExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Optional<String> emptyOptional = Optional.empty();
    if (emptyOptional.isPresent()) {  //NOSONAR
      logger.info("Empty Optional is non empty and the value is " + emptyOptional.get());
    } else {
      logger.info("Initially, Empty Optional is empty.");
    }
    logger.info(Constants.LINE_SEPERATOR);
    
    Optional<Integer> nullOptional = Optional.ofNullable(null);
    logger.info(NULL_OPTIONAL_VALUE_STRING
        + " printing other value " + nullOptional.orElse(5));
    
    logger.info(NULL_OPTIONAL_VALUE_STRING
        + " getting value from others " + nullOptional.orElseGet(() -> 10));
    
    try {
      nullOptional.orElseThrow(NoSuchElementException::new);
    } catch (NoSuchElementException exception) {
      String exceptionValue = Utils.getException(exception);
      logger.info(NULL_OPTIONAL_VALUE_STRING
          + " throwing exception \n" + exceptionValue);
    }
    logger.info(Constants.LINE_SEPERATOR);
    
    
    
    Optional<Integer> presentOptional = Optional.of(25);
    logger.info("Present Optional Value is " + presentOptional.orElse(null));
    
  }

}
