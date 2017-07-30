package com.rajkumar.java.utils.bettersolution;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Finding first repeating character.
 * 
 * @author Rajkumar
 *
 */
public class FindFirstRepeatingChar {
  
  private static Logger logger = LogManager.getLogger();
  
  private FindFirstRepeatingChar() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String input = "Repeat";
    
    bruteForceSolution(input);
    
    Character result = betterSolution(input);
    if (result != null) {
      logger.info("First Repeating Character in '" + input + "' is: " + result);
    } else {
      logger.info("No Repeating Character found");
    }
  }
  
  /**
   * Better approach: Using extra space
   * 
   * <p>Time complexity is O(N) and Space Complexity is O(N). </p>
   * 
   * @param input word
   * @return first repeating character
   * 
   */
  private static Character betterSolution(String input) {
    
    input = input.replaceAll("\\s+", ""); // NOSONAR
    
    Set<Character> charSet = new HashSet<>();
    
    for (Character charValue : input.toCharArray()) {
      if (charSet.contains(charValue)) {
        return charValue;
      }
      
      charSet.add(charValue);
    }
    return null;
  }
  
  /**
   * Naive approach: Solve using two nested loops. Take each char from the outer
   * loop and check the char in rest of the string using inner loop and return
   * the first char which is repeating.
   * 
   * <p>Time complexity is O(N^2). </p>
   * 
   * @param input word
   * @return first repeating character
   * 
   */
  private static Character bruteForceSolution(String input) { // NOSONAR
    
    // Ignoring this method definition
    return null;
  }
  
}
