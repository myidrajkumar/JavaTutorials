
package com.rajkumar.java.utils.lib;

/**
 * Constants for Project.
 * 
 * @author Seenappa
 *
 */
public enum Constants {
  OUTPUT_DIR("Ouput/"),
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