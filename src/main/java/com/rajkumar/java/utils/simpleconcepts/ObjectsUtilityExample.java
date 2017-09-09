package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Utils;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Objects static methods example.
 * 
 * @author Rajkumar
 *
 */
public class ObjectsUtilityExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private ObjectsUtilityExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Compute hash code for two integers, a char, and a string
    int hash = Objects.hash(10, 8900, 'x', "Hello");
    logger.info("Hash Code is " + hash);
    
    // Test for equality
    boolean isEqual = Objects.equals(null, null); // NOSONAR
    logger.info("null is equal to null: " + isEqual);
    
    isEqual = Objects.equals(null, "XYZ");
    logger.info("null is equal to XYZ: " + isEqual);
    
    // toString() method test
    logger.info("toString(null) is " + Objects.toString(null));
    
    logger.info("toString(null, \"XXX\") is " + Objects.toString(null, "XXX"));
    
    // Testing requireNonNull(T object, String message)
    try {
      printName("Doug Dyer");
      printName(null);
    } catch (NullPointerException exception) {
      logger.error(Utils.getException(exception));
    }
    
    // requireNonNull(T obj, Supplier<String> messageSupplier)
    try {
      // Using a lambda expression to create a Supplier<String> object.
      // The Supplier returns a timestamp message.
      Supplier<String> messageSupplier = () -> "Name is required "
          + Instant.now();
      getWithSuplier("Babalu", messageSupplier);
      getWithSuplier(null, messageSupplier);
    } catch (NullPointerException exception) {
      logger.error(Utils.getException(exception));
    }
  }
  
  /**
   * To check for NonNull value.
   * 
   * @param name name
   */
  public static void printName(String name) {
    
    // Test name for not null. Generate a NullPointerException if it is null.
    Objects.requireNonNull(name, "Name is required.");
    
    // Print the name if the above statement do not throw an exception
    logger.info("Found Name is " + name);
  }
  
  /**
   * To check for NonNull value.
   * 
   * @param name name
   * @param messageSupplier supplier will provide, if name is null
   */
  public static void getWithSuplier(String name,
      Supplier<String> messageSupplier) {
    
    // Test name for not null. Generate a NullPointerException if it is null.
    Objects.requireNonNull(name, messageSupplier);
    // Print the name if the above statement do not throw an exception
    logger.info("Found Name is " + name);
  }
}
