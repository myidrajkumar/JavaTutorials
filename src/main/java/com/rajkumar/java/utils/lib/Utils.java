
package com.rajkumar.java.utils.lib;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Library for Project.
 * 
 * @author Rajkumar
 *
 */
public class Utils {
  
  private Utils() {}
  
  /**
   * Exception will be returned as String.
   * 
   * @param exception Exception
   * @return converted Exception
   */
  public static String getException(Exception exception) {
    
    StringWriter writer = new StringWriter();
    exception.printStackTrace(new PrintWriter(writer));
    return (writer.toString() + "\n" + exception);
  }
  
  /**
   * Getting class information.
   * 
   * @param <T> Class type
   * @param type Input class
   * 
   * @return Class type
   */
  public static <T> Class<?> getClass(T type) {
    
    Class<?> enclosingClass = type.getClass().getEnclosingClass();
    return enclosingClass == null ? type.getClass() : enclosingClass;
  }
}
