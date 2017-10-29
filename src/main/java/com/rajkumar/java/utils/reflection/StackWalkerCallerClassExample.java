package com.rajkumar.java.utils.reflection;

/**
 * StackWalker.getCallerClass() returns the caller's Class.
 * 
 * @author Rajkumar
 *
 */
public class StackWalkerCallerClassExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments.
   */
  public static void main(String[] args) {
    TheCallerClass sc = new TheCallerClass();
    sc.doSomething();
  }
  
  private static final class TheCallerClass {
    
    public void doSomething() {
      
      TheCalleeClass theCalleeClass = new TheCalleeClass();
      theCalleeClass.work();
    }
  }
  
  private static final class TheCalleeClass {
    
    public void work() {
      
      StackWalker instance = StackWalker
          .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
      Class<?> callerClass = instance.getCallerClass();
      System.out.println(callerClass);
    }
  }
  
}
