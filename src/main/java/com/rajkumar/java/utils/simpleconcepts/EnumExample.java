package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Enum example.
 * 
 * @author Rajkumar
 *
 */
public class EnumExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private EnumExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  @SuppressWarnings("unlikely-arg-type")
  public static void main(String[] args) {
    
    logger.info("Ordinal " + Color.GREEN.ordinal() + " for GREEN");
    
    Color greenColor = Color.GREEN;
    // Result will be true but Not good practice
    logger.info("color.equals() => " + greenColor.equals(Color.GREEN)); 
   
    // Result will be always false
    logger.info("color.equals() => " + "GREEN".equals(greenColor)); //NOSONAR
    
    // Result will be true - Good Practice
    logger.info("color == Color.GREEN " + (greenColor == Color.GREEN)); 
    
    Color redColor = Color.RED;
    
    if (greenColor.compareTo(redColor) < 0) {
      logger.info(greenColor + " declared before " + redColor);
    } else {
      logger.info(greenColor + " declared after " + redColor);
    }
  }
  
  private enum Color {
    RED(1) {
      
      @Override
      public String getSomeMessage() {
        
        return "RED color";
      }
      
    },
    BLUE(2) {
      
      @Override
      public String getSomeMessage() {
        
        return "BLUE color";
      }
      
    },
    GREEN(3) {
      
      @Override
      public String getSomeMessage() {
        
        return "GREEN color";
      }
      
    };
    
    private int value;
    
    private Color(int value) {
      this.value = value;
    }
    
    public int getValue() {
      
      return value;
    }
    
    public abstract String getSomeMessage();
    
    @Override
    public String toString() {
      
      return getSomeMessage() + " with value " + getValue();
    }
  }
  
}
