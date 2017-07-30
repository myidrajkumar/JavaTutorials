package com.rajkumar.java.utils;

import com.rajkumar.java.utils.lib.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lambda Expression Example.
 * 
 * @author Rajkumar
 *
 */
public class LambdaExpressionExample {

  private static Logger logger = LogManager.getLogger();
  
  private LambdaExpressionExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
   
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable task = () -> logger.info("Hello, Lambda");
    executor.execute(task);
    executor.shutdown(); //shutting down is very important
    logger.info(Constants.LINE_SEPERATOR);
    
    final MyCalculator calculator = (int firstNum, int secondNum) -> firstNum + secondNum;
    logger.info("Addition is " + calculator.add(4, 3));
    logger.info(Constants.LINE_SEPERATOR);
    
    List<Student> studentList = getStudentList();
    
    Comparator<Student> studentComparator = (Student s1, Student s2) -> {
      if (s1.getStudentName() == null && s2.getStudentName() == null) {
        return Integer.compare(s1.getAge(), s2.getAge());
      }
      
      if (s1.getStudentName() != null && s2.getStudentName() != null) {
        int compareValue = s1.getStudentName().compareTo(s2.getStudentName());
        return compareValue != 0 ? compareValue :  Integer.compare(s1.getAge(), s2.getAge());
      }
      
      if (s1.getStudentName() == null) {
        return -1;
      } else {
        return 1;
      }
    };
    
    Collections.sort(studentList, studentComparator);
    logger.info(studentList);
    logger.info(Constants.LINE_SEPERATOR);
    
    Function<Student, String> studentInfo = 
        student -> student.getStudentName() + ", " + student.getAge();
    
    for (Student student : studentList) {
      logger.info(studentInfo.apply(student));
    }
    logger.info(Constants.LINE_SEPERATOR);

  }

  private static List<Student> getStudentList() {
    final List<Student> studentList = new ArrayList<>();
    studentList.add(new Student("Raj", 30));
    studentList.add(new Student("Kumar", 40));
    studentList.add(new Student("Raghu", 50));
    studentList.add(new Student(null, 50));
    return studentList;
  }

  @FunctionalInterface
  private static interface MyCalculator {
    int add(int firstNum, int secondNum);
  }
  
  private static class Student {
    private String studentName;
    private int age;
    
    public Student(String studentName, int age) {
      this.studentName = studentName;
      this.age = age;
    }
    
    /**
     * Returning the name of the student.
     * 
     * @return the studentName
     */
    public String getStudentName() {
      return studentName;
    }
    
    /**
     * Returning the age of the student.
     * 
     * @return the age
     */
    public int getAge() {
      return age;
    }
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("Student = ");
      sb.append(" { Name : " + this.studentName + ", ");
      sb.append("Age : " + this.age + " }");
      return sb.toString();
    }
    
  }

}
