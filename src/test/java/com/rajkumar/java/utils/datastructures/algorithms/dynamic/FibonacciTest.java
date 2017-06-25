package com.rajkumar.java.utils.datastructures.algorithms.dynamic;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Test case to validate Fibonacci series.
 * 
 * @author Seenappa
 *
 */
public class FibonacciTest {

  @Test
  public final void testFindFibonacciUsingRecursive() {
    assertArrayEquals("Fibonacci series is not correct", 
        new Integer[] {0, 1, 1}, 
        Fibonacci.getFibonacciSeriesUsingRecursive(3));
  }
  
  @Test
  public final void testFindFibonacciUsingIteration() {
    assertArrayEquals("Fibonacci series is not correct", 
        new int[] {0, 1, 1, 2, 3}, 
        Fibonacci.getFibonacciSeriesUsingIteration(5));
  }
  
  @Test
  public final void testFindFibonacciUsingMemoization() {
    assertArrayEquals("Fibonacci series is not correct", 
        new int[] {0, 1, 1, 2, 3, 5}, 
        Fibonacci.getFibonacciSeriesUsingMemoization(6));
  }

}
