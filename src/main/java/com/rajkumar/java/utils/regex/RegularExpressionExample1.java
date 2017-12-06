package com.rajkumar.java.utils.regex;

import com.rajkumar.java.utils.lib.Constants;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Regular expression example.
 * 
 * @author Rajkumar
 *
 */
public class RegularExpressionExample1 {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    streamingResults();
    logger.info(Constants.LINE_SEPERATOR);
    
    mappingStreamedResults();
    logger.info(Constants.LINE_SEPERATOR);
    
    gettingSumFromRegEx();
    
    replaceAllMethod();
    
    replaceAllByDecorating();
    
  }

  private static void replaceAllByDecorating() {
    
    Pattern pattern = Pattern.compile("(\\d{3})-(\\d{3})-(\\d{4})");
    String input = "phone1: 111-111-1111\nphone2: 222-222-2222";
    logger.info("-- input --\n" + input);
    
    Matcher matcher = pattern.matcher(input);
    String replacedString = matcher.replaceAll(matchResult -> "($1) $2 $3");
    logger.info("-- after replacement --\n" + replacedString);
  }

  private static void replaceAllMethod() {
    
    Pattern pattern = Pattern.compile("\\s+");
    Matcher matcher = pattern.matcher("this is a test string");
    String replacedString = matcher.replaceAll(matchResult -> "-");
    logger.info(replacedString);
  }

  private static void gettingSumFromRegEx() {
    
    Pattern pattern = Pattern.compile("\\d+");
    String input = "a 1 b 2 c 3 d 4 e 5 f 6";
    Matcher matcher = pattern.matcher(input);
    int sum = matcher.results()
                     .map(MatchResult::group)
                     .mapToInt(Integer::parseInt)
                     .sum();
    logger.info(sum);
  }

  private static void mappingStreamedResults() {
    
    Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
    String input = "111-111-1111 222-222-2222 333-333-3333 444-444-444";
    Matcher matcher = pattern.matcher(input);
    String phoneNumbers = matcher.results()
                      .dropWhile(matchResult -> !matchResult.group().startsWith("222"))
                      .takeWhile(matchResult -> !matchResult.group().startsWith("444"))
                      .map(MatchResult::group)
                      .collect(Collectors.joining(", "));
    logger.info(phoneNumbers);
  }

  private static void streamingResults() {
    
    Pattern pattern = Pattern.compile("<(.|\n)*?>");
    String input = "<html><body><div>content1</div><div>content2</body></html>";
    Matcher matcher = pattern.matcher(input);
    //using the new method results()
    logger.info("Matcher Results will be streamed");
    Stream<MatchResult> stream = matcher.results();
    stream.forEach(matchResult -> logger.info(matchResult.group()));
  }
  
}
