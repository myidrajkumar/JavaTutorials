package com.rajkumar.java.utils.datastructures.algorithms.dynamic;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

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
    
    assertArrayEquals("Fibonacci series is not correct",
        new Integer[] {0, 1, 1}, Fibonacci.getFibonacciSeriesUsingRecursive(3));
  }
  
  /**
   * Test getting fibonacci through iteration.
   */
  @Test
  public final void testFindFibonacciUsingIteration() {
    
    assertArrayEquals("Fibonacci series is not correct",
        new int[] {0, 1, 1, 2, 3},
        Fibonacci.getFibonacciSeriesUsingIteration(5));
  }
  
  /**
   * Test getting fibonacci through memoization.
   */
  @Test
  public final void testFindFibonacciUsingMemoization() {
    
    assertArrayEquals("Fibonacci series is not correct",
        new int[] {0, 1, 1, 2, 3, 5},
        Fibonacci.getFibonacciSeriesUsingMemoization(6));
  }
  
}
