package com.rajkumar.java.utils.generics;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Specifying Upper / Lower bounds in generics.
 * 
 * @author Rajkumar
 *
 */
public class UpperLowerBoundsExample {
  
  private UpperLowerBoundsExample() {}
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
   
    GrandChild grandChild1 = new GrandChild();
    GrandChild grandChild2 = new GrandChild();
    List<GrandChild> grandChildList = Arrays.asList(grandChild1, grandChild2);
    
    upperBound(grandChildList); //This is allowed since GrandChild is extending Me
    
    GrandParent grandParent1 = new GrandParent();
    GrandParent grandParent2 = new GrandParent();
    
    //This suppress is purposefully added.
    @SuppressWarnings("unused")
    List<GrandParent> grandParentList = Arrays.asList(grandParent1, grandParent2); //NOSONAR
    
    //upperBound(grandParentList); This is not allowed since not extending me
    
    
  }
  
  
  private static <T extends Me> void upperBound(List<T> usersList) {
    usersList.forEach(T::display1);
  }
  
  private static class GrandParent {
    
    protected void display1() {
      
      logger.info(this.getClass().getName());
    }
  }
  
  private static class Parent extends GrandParent {}
  
  private static class Me extends Parent {}
  
  private static class Child extends Me {}
  
  private static class GrandChild extends Child {}
}
