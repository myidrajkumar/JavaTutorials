package com.rajkumar.java.utils.datastructures.algorithms.dynamic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case to validate Fibonacci series.
 * 
 * @author Rajkumar
 *
 */
public class FibonacciTest {
  
  /**
   * Test getting fibonacci through recursive.
   */
  @Test
  public final void testFindFibonacciUsingRecursive() {
    
    assertArrayEquals(
        new Integer[] {0, 1, 1}, 
        Fibonacci.getFibonacciSeriesUsingRecursive(3),
        "Fibonacci series is not correct");
  }
  
  /**
   * Test getting fibonacci through iteration.
   */
  @Test
  public final void testFindFibonacciUsingIteration() {
    
    assertArrayEquals(
        new int[] {0, 1, 1, 2, 3},
        Fibonacci.getFibonacciSeriesUsingIteration(5),
        "Fibonacci series is not correct");
  }
  
  /**
   * Test getting fibonacci through memoization.
   */
  @Test
  public final void testFindFibonacciUsingMemoization() {
    
    assertArrayEquals(
        new int[] {0, 1, 1, 2, 3, 5},
        Fibonacci.getFibonacciSeriesUsingMemoization(6),
        "Fibonacci series is not correct");
  }
  
}
