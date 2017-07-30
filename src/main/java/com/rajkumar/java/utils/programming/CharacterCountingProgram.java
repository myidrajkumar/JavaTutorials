package com.rajkumar.java.utils.programming;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * How to count frequency of each character in a string in Java.
 * 
 * @author Rajkumar
 *
 */
public class CharacterCountingProgram {
  
  private CharacterCountingProgram() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    String word = "HelloWorld";
    characterCount(word);
  }

  private static void characterCount(String word) {
    Logger logger = LogManager.getLogger();
    
    // Map with char as key and frequency as value
    Map<Character, Integer> charFreqMap = new HashMap<>();

    if (word != null) {
      char[] charArray = word.toCharArray();
      
      for (char currentChar : charArray) {
        Integer count = charFreqMap.get(currentChar);
        // increment count if character already exists in map, 
        // else set count to 1
        int newCount = (count == null ? 1 : count + 1);
        charFreqMap.put(currentChar, newCount);
      }
    }
    logger.info(charFreqMap);
  }
}

