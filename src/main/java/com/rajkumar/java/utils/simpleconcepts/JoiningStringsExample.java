package com.rajkumar.java.utils.simpleconcepts;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Better way of concatenating strings example.
 * 
 * @author Rajkumar
 *
 */
public class JoiningStringsExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private JoiningStringsExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    final String message = "Joined String ==> ";
    
    // java8
    
    // Joining String in Java using StringJoiner
    // Example 1 - joining string by comma
    StringJoiner joiner = new StringJoiner(", ");
    joiner.add("Sony");
    joiner.add("Apple");
    joiner.add("Google");
    String joinedStr = joiner.toString();
    logger.info(message + joinedStr);
    
    // Example 2 - joining string by comma but shortened form
    joinedStr = new StringJoiner(",")
        .add("Sony")
        .add("Apple")
        .add("Google")
        .toString();
    logger.info(message + joinedStr);
    
    // Example 3 - let's join String by Whitespace
    joinedStr = String.join(" ", "Java", "is", "best");
    logger.info(message + joinedStr);
    
    // Example 4 - let's join String by Whitespace and passing array
    String[] typesOfFee = {"admin fee", "processing fee", "monthly fee"};
    joinedStr = String.join(";", typesOfFee);
    logger.info(message + joinedStr);
    
    // Example 5 - let's join String by ',' and passing list
    List<String> typesOfLoan = Arrays.asList("home loan", "personal loan",
        "car loan", "balance transfer");
    joinedStr = String.join(",", typesOfLoan);
    logger.info(message + joinedStr);
    
    // Example 6 - let's join String by using Collectors
    List<String> list = Arrays.asList("life insurance", "health insurance",
        "car insurance");
    joinedStr = list.stream().map(String::toUpperCase)
        .collect(Collectors.joining(", "));
    logger.info(message + joinedStr);
    
    // Java 7
    // Approach1
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String item : list) {
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      sb.append(item);
    }
    
    logger.info(message + sb.toString());
    
    // Approach2
    sb = new StringBuilder();
    String sepeartor = ",";
    String toBeAdded = "";
    for (String item : list) {
      sb.append(toBeAdded);
      sb.append(item);
      toBeAdded = sepeartor;
    }
    logger.info(message + sb.toString());
  }
  
}
