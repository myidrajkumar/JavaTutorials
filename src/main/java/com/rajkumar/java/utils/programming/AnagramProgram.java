package com.rajkumar.java.utils.programming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Arrays;

/**
 * Java program to check if two strings are Anagrams.
 * 
 * @author Seenappa
 *
 */
public class AnagramProgram {
  
  private AnagramProgram() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    String firstWord = "cat";
    String secondWord = "tca";
    String thirdWord = "tbc";
    
    logger.info("Is '" + firstWord + "' and"
        + "'" + secondWord + "' are anagrams??? "
        + isAnagram(firstWord, secondWord));
    
    logger.info(Constants.LINE_SEPERATOR);
    
    logger.info("Is '" + firstWord + "' and"
        + "'" + thirdWord + "' are anagrams??? "
        + isAnagram(firstWord, thirdWord));
    
  }
  
  private static boolean isAnagram(String word1, String word2) {
    char[] charArr1 = word1.replaceAll("[\\s]", "").toLowerCase().toCharArray();
    char[] charArr2 = word2.replaceAll("[\\s]", "").toLowerCase().toCharArray();
      
    Arrays.sort(charArr1);
    Arrays.sort(charArr2);
      
    return (Arrays.equals(charArr1, charArr2));
  }

}
