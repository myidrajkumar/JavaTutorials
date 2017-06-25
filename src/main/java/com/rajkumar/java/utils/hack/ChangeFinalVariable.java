package com.rajkumar.java.utils.hack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Reflection example to modify final variable.
 * 
 * @author seenappa
 *
 */
public class ChangeFinalVariable {

  private static Logger logger = LogManager.getLogger();
  
  /**
   * Is final variable's value can be changed?? Assigning new value will cause
   * compilation error. What else can we do??
   * 
   * <p>
   * This solution will not work for primitive types since compiler does
   * constant folding (What is this??) </p>
   * 
   * <p>
   * Constant folding is the process of recognizing and evaluating constant
   * expressions at compile time rather than computing them at runtime. 
   * Terms in constant expressions are typically simple literals, 
   * such as the integer literal 2, but they may also be variables 
   * whose values are known at compile time </p>
   */
  private static final Integer FINAL_VARIABLE = 42;

  private ChangeFinalVariable() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    logger.info("Initially ==>");
    getFinalVariable();
    logger.info("=====================================");

    logger.info("Final variable value can be changed by reflection");

    Class<ChangeFinalVariable> finalVariableClass = ChangeFinalVariable.class;

    Field finalVariableField = null;
    try {
      finalVariableField = finalVariableClass.getDeclaredField("FINAL_VARIABLE");
    } catch (NoSuchFieldException | SecurityException exception) {
      Utils.getException(exception);
      return;
    }

    finalVariableField.setAccessible(true);

    Field fieldModifier = null;

    try {
      fieldModifier = Field.class.getDeclaredField("modifiers");
    } catch (NoSuchFieldException | SecurityException exception) {
      Utils.getException(exception);
      return;
    }
    fieldModifier.setAccessible(true);

    try {
      fieldModifier.setInt(finalVariableField,
          finalVariableField.getModifiers() & ~Modifier.FINAL);
      fieldModifier.setAccessible(true);
      finalVariableField.set(null, 40);
    } catch (IllegalArgumentException | IllegalAccessException exception) {
      Utils.getException(exception);
      return;
    }

    getFinalVariable();

  }

  private static void getFinalVariable() {
    logger.info("Final variable value = " + ChangeFinalVariable.FINAL_VARIABLE);
  }

}
