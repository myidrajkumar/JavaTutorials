package com.rajkumar.java.utils.knownissues;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * streams cannot be reused.
 * 
 * @author Rajkumar
 *
 */
public class ReusingStreamExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ReusingStreamExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String[] array = {"a", "b", "c", "d", "e"};
    Stream<String> stream = Arrays.stream(array);
    
    // loop a stream
    stream.forEach(logger::info);
    
    // reuse it to filter again! throws IllegalStateException
    // Stream cannot be reused, once it is consumed or used, the stream will be
    // closed
    
    // To reuse
    Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
    // get new stream
    streamSupplier.get().forEach(logger::info);
    
    // get another new stream
    long count = streamSupplier.get().filter("b"::equals).count();
    logger.info(count);
    
  }
  
}
