package com.rajkumar.java.utils.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple Reflection Example.
 * 
 * @author Rajkumar
 *
 */
public class GettersAndSettersReflectionExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private GettersAndSettersReflectionExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   * @throws SecurityException Security Exception
   * @throws NoSuchMethodException No Method Exception
   * @throws InvocationTargetException Invocation Target Exception
   * @throws IllegalArgumentException  Illegal Argument Exception
   * @throws IllegalAccessException  Illegal Access Exception
   */
  public static void main(String[] args) throws NoSuchMethodException, 
            SecurityException, IllegalAccessException, 
            IllegalArgumentException, InvocationTargetException {
    
    InnerClass innerClass = new InnerClass();
    logger.info("Initially innerClass property " + System.lineSeparator());
    logger.info(innerClass);
    
    Method prop1Setter = innerClass.getClass().getDeclaredMethod("setProperty1", String.class);
    prop1Setter.setAccessible(true);
    prop1Setter.invoke(innerClass, "Hello");
    
    Method prop1Getter = innerClass.getClass().getDeclaredMethod("getProperty1");
    prop1Getter.setAccessible(true);
    String prop1Result = (String) prop1Getter.invoke(innerClass);
    logger.info("Prop1 ==> " + prop1Result);
    
    Method prop2Setter = innerClass.getClass().getDeclaredMethod("setProperty2", int.class);
    prop2Setter.setAccessible(true);
    prop2Setter.invoke(innerClass, 4);
    
    Method prop2Getter = innerClass.getClass().getDeclaredMethod("getProperty2");
    prop2Getter.setAccessible(true);
    int prop2Result = (int) prop2Getter.invoke(innerClass);
    logger.info("Prop2 ==> " + prop2Result);
    
    logger.info("After invoking private innerClass methods " + System.lineSeparator());
    logger.info(innerClass);
    
  }
  
  
  private static class InnerClass {
    
    private String property1;
    
    private int property2;

    
    /**
     * Private method but still can be accessed by using reflection.
     * 
     * @return the property1
     */
    @SuppressWarnings("unused")
    private String getProperty1() {
      
      return property1;
    }

    
    /**
     * Private method but still can be accessed by using reflection.
     * 
     * @param property1 the property1 to set
     */
    @SuppressWarnings("unused")
    private void setProperty1(String property1) {
      
      this.property1 = property1;
    }

    
    /**
     * Private method but still can be accessed by using reflection.
     * 
     * @return the property2
     */
    @SuppressWarnings("unused")
    private int getProperty2() {
      
      return property2;
    }

    
    /**
     * Private method but still can be accessed by using reflection.
     * 
     * @param property2 the property2 to set
     */
    @SuppressWarnings("unused")
    private void setProperty2(int property2) {
      
      this.property2 = property2;
    }
    
    @Override
    public String toString() {

      return "InnerClass = { Prop1 : " + property1 + ", Prop2 : " + property2 + " }";
    }
    
  }
}
