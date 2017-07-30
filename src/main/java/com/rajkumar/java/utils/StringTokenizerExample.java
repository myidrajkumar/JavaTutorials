package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Strings will be split using StringTokenizer.
 * StringTokenizer is deprecated so do not use at all.
 * 
 * @author Rajkumar
 *
 */
public class StringTokenizerExample {

  private static final String SPLITTED_STRING = "Splitted String \n";
  private static Logger logger = LogManager.getLogger();
  
  private StringTokenizerExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    String exampleString = "Welcome,to,the,world,of,practising,java";
    List<String> splitterStrings = Collections
        .list(new StringTokenizer(exampleString, ","))
        .stream()
        .map(token -> (String) token)
        .collect(Collectors.toList());

    logger.info(SPLITTED_STRING);
    splitterStrings.forEach(logger::info);

    logger.info(Constants.LINE_SEPERATOR);
    
    /*
     * StringTokenizer construction variants
     * 
     * new StringTokenizer(String value, String delimiter, boolean returnDelimiter) 
     * if boolean value is true, then delimiter itself will be considered as token
     * 
     * new StringTokenizer(String value, String delimiter) 
     * boolean value will be false
     * 
     * new StringTokenizer(String value) 
     * delimiter will be \t\n\r\ and boolean will be false
     */

    splitterStrings = Collections
        .list(new StringTokenizer(exampleString, ",", true))
        .stream()
        .map(token -> (String) token)
        .collect(Collectors.toList());
    logger.info(SPLITTED_STRING);
    splitterStrings.forEach(logger::info);
    logger.info(Constants.LINE_SEPERATOR);

    splitterStrings = Collections.list(new StringTokenizer(exampleString))
        .stream().map(token -> (String) token).collect(Collectors.toList());
    logger.info(SPLITTED_STRING);
    splitterStrings.forEach(logger::info);
    logger.info(Constants.LINE_SEPERATOR);

  }

}
