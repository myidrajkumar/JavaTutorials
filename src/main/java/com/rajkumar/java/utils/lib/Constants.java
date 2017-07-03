
package com.rajkumar.java.utils.lib;

/**
 * Constants for Project.
 * 
 * @author Rajkumar
 *
 */
public enum Constants {
  OUTPUT_DIR("Output/"),
  INPUT_DIR("Input/"),
  LINE_SEPERATOR("\n ===================================== \n");

  private String value;

  Constants(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
  
  @Override
  public String toString() {
    return value;
  }
}