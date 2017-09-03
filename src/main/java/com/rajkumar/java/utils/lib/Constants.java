
package com.rajkumar.java.utils.lib;

/**
 * Constants for Project.
 * 
 * @author Rajkumar
 *
 */
public enum Constants {
  /**
   * Output directory.
   */
  OUTPUT_DIR("Output/"),
  
  /**
   * Output directory.
   */
  INPUT_DIR("Input/"),
  
  /**
   * Line separator.
   */
  LINE_SEPERATOR("\n ===================================== \n");
  
  private String value;
  
  Constants(String value) {
    
    this.value = value;
  }
  
  /**
   * Returns Constants value.
   * 
   * @return value
   */
  public String value() {
    
    return value;
  }
  
  /**
   * String version of constant.
   */
  @Override
  public String toString() {
    
    return value;
  }
}
