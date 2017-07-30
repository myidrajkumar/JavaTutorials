package com.rajkumar.java.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ArrayBlockingQueue example.
 * 
 * <p>
 * This is blocking, bounded queue and stores internally in an array.
 * It CAN'T store unlimited amounts of elements. 
 * This queue orders elements FIFO
 * </p>
 * 
 * @author Rajkumar
 *
 */
public class ArrayBlockingQueueExample {
  
  private ArrayBlockingQueueExample() { }

  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    //Since capacity is mentioned, more than capacity cannot be inserted
    BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
    
    for (int i = 1; i <= 5; i++) {
      queue.add("String" + i);
    }
    
    queue.forEach(logger::info);
    
    /*
     * Now i'll add an element. Since this addition is more than capacity,
     * exception will be thrown.
     */
    
    queue.add("String6");
  }
  
}
