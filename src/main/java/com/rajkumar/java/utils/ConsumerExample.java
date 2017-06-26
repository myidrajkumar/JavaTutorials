package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer Example.
 * 
 * @author Rajkumar
 *
 */
public class ConsumerExample {
  
  private ConsumerExample() { }

  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main method to check.
   * 
   * @param args arguments
   */
  public static void main(String[] args) {

    List<Student> studentList = new ArrayList<>();
    studentList.add(new Student("Ram", 20));
    studentList.add(new Student("Shyam", 22));
    studentList.add(new Student("Kabir", 18));

    // Creating instance of Consumer functional interface
    Consumer<Student> studentInfo = (Student student) -> logger.info(
        "Student = { Name = " + student.name + ", Age = " + student.age + " }");

    // first way using Consumer
    studentList.forEach(studentInfo);
    logger.info(Constants.LINE_SEPERATOR);
    
    // second way using method reference
    studentList.forEach(Student::printData);
    logger.info(Constants.LINE_SEPERATOR);
    
    // third way using lambda expression
    studentList.forEach(s -> s.printData());  //NOSONAR

  }

  private static class Student {

    public String name;
    public int age;

    public Student(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public void printData() {
      logger.info("Name:" + name + " age:" + age);
    }
  }

}
