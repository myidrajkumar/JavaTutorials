package com.rajkumar.java.utils.clone;

import com.rajkumar.java.utils.lib.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clone Example.
 * 
 * @author Rajkumar
 *
 */
public class CloneExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private CloneExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Student stud = initializeStudent();
    setDeepCopy(stud, false);
    printStudentInfo(stud);
    
    stud = initializeStudent();
    setDeepCopy(stud, true);
    printStudentInfo(stud);
    
  }
  
  /**
   * Setting deep copy value.
   * 
   * @param stud student
   * @param isDeepCopy value to be set
   */
  private static void setDeepCopy(Student stud, boolean isDeepCopy) {
    
    stud.isDeepCopy = isDeepCopy;
  }
  
  /**
   * Printing student information.
   * 
   * @param stud student
   */
  private static void printStudentInfo(Student stud) {
    
    // Clone Object
    Student clonedStud = stud.clone();
    
    logger.info("Cloned Object: " + clonedStud);
    
    stud.setName("Dan");
    stud.getSubj().setName("Physics");
    
    logger.info("Original Object after it is updated: " + stud);
    
    logger.info("Cloned Object after updating original object: " + clonedStud);
    
    logger.info("\n\n----------------------------------------------------\n\n");
  }
  
  /**
   * Initializing student.
   * 
   * @return student
   */
  private static Student initializeStudent() {
    
    // Original Object
    Student stud = new Student("John", "Algebra");
    logger.info("Original Object: " + stud);
    
    return stud;
  }
  
  private static class Subject {
    
    private String name;
    
    public Subject(String name) {
      this.name = name;
    }
    
    public String getName() {
      
      return name;
    }
    
    public void setName(String name) {
      
      this.name = name;
    }
    
  }
  
  private static class Student implements Cloneable {
    
    private boolean isDeepCopy = false;
    
    // Contained object
    private Subject subj;
    
    private String name;
    
    public Student(String name, String sub) {
      this.name = name;
      subj = new Subject(sub);
    }
    
    public Subject getSubj() {
      
      return subj;
    }
    
    public void setName(String name) {
      
      this.name = name;
    }
    
    @Override
    public Student clone() {  //NOSONAR
      
      if (isDeepCopy) {
        return deepCopy();
      }
      return shallowCopy();
    }
    
    /**
     * Doing shallow copy.
     * 
     * @return student
     */
    private Student shallowCopy() {
      
      // shallow copy
      try {
        return (Student) super.clone();
      } catch (CloneNotSupportedException exception) {
        logger.error(Utils.getException(exception));
        return null;
      }
    }
    
    /**
     * Doing deep copy.
     * 
     * @return student
     */
    private Student deepCopy() {
      
      // Deep copy
      return new Student(name, subj.getName());
    }
    
    @Override
    public String toString() {
      
      return "Student { Name : " + name + ", Subject :" + subj.name + "}";
    }
  }
  
}
