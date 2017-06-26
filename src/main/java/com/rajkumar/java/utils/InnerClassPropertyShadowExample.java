package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Property shadowing example.
 * 
 * @author Rajkumar
 *
 */
public class InnerClassPropertyShadowExample {

  private int firstProperty = 10;

  private InnerClassPropertyShadowExample() { }

  class InnerClass {

    private int firstProperty = 20;

    private void printPropertyValues(int firstProperty) {
      
      Logger logger = LogManager.getLogger();
      
      logger.info("Just FirstProperty = " + firstProperty);
      logger.info("This FirstProperty = " + this.firstProperty);
      logger.info("Enclosing FirstProperty = "
          + InnerClassPropertyShadowExample.this.firstProperty);
    }

  }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    InnerClassPropertyShadowExample example = new InnerClassPropertyShadowExample();
    InnerClass innerClass = example.new InnerClass();
    innerClass.printPropertyValues(30);
  }

}
