package com.rajkumar.java.utils.stream;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Converting arrays to stream.
 * 
 * @author Rajkumar
 *
 */
public class ArrayToStreamExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ArrayToStreamExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Try with Object Array
    String[] company = {"Twitter", "Facebook", "Yahoo", "Google"};
    
    // Returns a sequential Stream
    Stream<String> stream = Arrays.stream(company);
    logger.info("\n1. Arrays.stream output for Object Array:");
    stream.forEach(logger::info);
    
    // Returns a sequential ordered stream
    Stream<String> stream2 = Stream.of(company);
    logger.info("\n2. Stream.of output for Object Array:");
    stream2.forEach(logger::info);
    
    double[] doubleValues = {11.1, 21.2, 31.3, 41.4, 51.5};
    
    DoubleStream doubleStream = Arrays.stream(doubleValues);
    logger.info("\n1. Arrays.stream output for Primitive Arrays:");
    doubleStream.forEach(logger::info);
    
    Stream<double[]> doubleStreamVal = Stream.of(doubleValues);
    // flatMapToDouble - returns an DoubleStream consisting of the results of
    // replacing each element of this stream
    // with the contents of a mapped stream produced by applying the provided
    // mapping function to each element.
    DoubleStream doubleStream2 = doubleStreamVal
        .flatMapToDouble(Arrays::stream);
    logger.info("\n2. Stream.of output for Primitive Arrays:");
    doubleStream2.forEach(logger::info);
  }
  
}
