package com.rajkumar.java.utils.stream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * for loop using streams.
 * 
 * @author Rajkumar
 *
 */
public class BetterForLoopUsingStreams {
  
  private static Logger logger = LogManager.getLogger();
  
  private BetterForLoopUsingStreams() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    logger.info("Using IntStream.range() & IntStream.rangeClosed()");
    // Left argument is inclusive whereas right is exclusive
    IntStream.range(1, 10).forEach(logger::info);
    
    // Both left & right arguments are inclusive
    IntStream.rangeClosed(1, 10).forEach(logger::info);
    
    logger.info("\n Using LongStream.range() & LongStream.rangeClosed()");
    LongStream.range(1000000L, 1000005L).forEach(logger::info);
    
    LongStream.rangeClosed(1000000L, 1000005L).forEach(logger::info);
  }
  
}
