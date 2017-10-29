package com.rajkumar.java.utils.programming;

import org.apache.logging.log4j.LogManager;

/**
 * Finding out next palindrome number.
 * 
 * @author Rajkumar
 *
 */
public class NextPalindrome {
  
  private NextPalindrome() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    LogManager.getLogger().info("34541 => " + nextPalindrome(34541));
  }
  
  private static int nextPalindrome(int num) {
    
    if (num < 10) {
      return 11;  //This will be case ever
    }
    return nextPalindrome(num, true);
  }
  
  /**
   * Approach:
   *  1. Find out whether given number contains odd or even number of characters 
   *  2. Split the word into  two halves 
   *          (If the word contains odd number of characters, ignore the middle character) 
   *  3. Reverse the first half and compare the both halves
   *  4. if reversed left is greater than second half, 
   *           return the answer as (left half + middle (if exists) + reversed left half)
   *   5. else increment ( left half + middle) by 1 
   *          and do the steps 2 & 3 & 4 always
   *   
   * @param num number
   * @param firstTime first time .
   * 
   * @return next palindrome number.
   */
  private static int nextPalindrome(int num, boolean firstTime) {
    
    String numString = "" + num;
    int leftEndIndex = -1;
    int rightStartIndex = -1;
    
    boolean isOdd = numString.length() % 2 == 1;
    if (isOdd) {
      leftEndIndex = numString.length() / 2;
      rightStartIndex = leftEndIndex + 1;
    } else {
      leftEndIndex = rightStartIndex = numString.length() / 2;
    }
    
    String leftHalf = numString.substring(0, leftEndIndex);
    String rightHalf = numString.substring(rightStartIndex);
    
    String leftReversed = new StringBuffer(leftHalf).reverse().toString();
    
    if (Integer.parseInt(leftReversed) > Integer.parseInt(rightHalf) || !firstTime) {
      return Integer.parseInt(leftHalf 
          + (isOdd ? numString.charAt(leftEndIndex) : "") 
          + leftReversed);
     
    }
    
    int incrementedLeft = Integer.parseInt(
           leftHalf 
           + (isOdd ? numString.charAt(leftEndIndex) : "")
        ) 
        + 1;
    
    return nextPalindrome(Integer.parseInt(incrementedLeft + rightHalf), false);
  }
}
