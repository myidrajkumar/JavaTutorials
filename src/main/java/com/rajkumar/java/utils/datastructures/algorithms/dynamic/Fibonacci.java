package com.rajkumar.java.utils.datastructures.algorithms.dynamic;

/**
 * Dynamic programming and memoization works together.
 * So Most of the problems are solved with two components of dynamic programming (DP)
 * 
 * <p>1. Recursion — Solve the subproblems recursively <br>
 * 2. Memoization — Store the solution of these subproblems 
 *                  so that we do not have to solve them again </p>
 *
 * @author Rajkumar
 *
 */
public class Fibonacci {
  
  private Fibonacci() { }

  /**
   * Fibonacci series will be identified with number of entries mentioned.
   * 
   * <p>Time complexity:  T(n) = T(n-1) + T(n-2) + 1 = 2^n = O(2^n)</p>
   * 
   * @param number Specify the total number of items you want in fibonacci series.
   * 
   * @return Fibonacci series
   */
  public static Integer[] getFibonacciSeriesUsingRecursive(int number) {
    Integer[] result = new Integer[number];
    
    for (int i = 0; i < number; i++) {
      result[i] =  findFibonacciUsingRecursive(i);
    }

    return result;
  }
  
  private static int findFibonacciUsingRecursive(int number) {
    if (number == 0) {
      return 0;
    } else if (number == 1) {
      return 1;
    } else {
      // Avoid always temporary variables
      return findFibonacciUsingRecursive(number - 1) 
          + findFibonacciUsingRecursive(number - 2);
    }
  }
  
  /**
   * Find Fibonacci using memoization.
   * 
   * <p> Time Complexity: O(n), Space Complexity: O(n) </p>
   * 
   * @param number Specify the total number of items you want in fibonacci series.
   * 
   * @return Fibonacci Series.
   */
  public static int[] getFibonacciSeriesUsingMemoization(int number) {
    int[] result = new int[number];

    result[0] = 0;
    result[1] = 1;

    for (int i = 2; i < number; i++) {
      result[i] = result[i - 1] + result[i - 2];
    }
    
    return result;
  }
  
  /**
   * Find Fibonacci using iteration approach.
   * 
   * @param number Specify the total number of items you want in fibonacci series.
   * 
   */
  public static int[] getFibonacciSeriesUsingIteration(int number) {
    
    int[] result = new int[number];
    
    int firstNumber = 0;
    int secondNumber = 1;
    int additionResult = 0;
    
    result[0] = firstNumber;
    result[1] = secondNumber;
    
    for (int i = 2; i < number; i++) {
      additionResult = firstNumber + secondNumber;
      result[i] = additionResult;
      
      firstNumber = secondNumber;
      secondNumber = additionResult;
    }
    
    return result;
  }
}
 