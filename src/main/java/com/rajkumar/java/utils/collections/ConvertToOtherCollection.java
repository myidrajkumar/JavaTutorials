package com.rajkumar.java.utils.collections;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Collection type can be changed to other collection type.
 * 
 * @author Rajkumar
 *
 */
public class ConvertToOtherCollection {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tim Johns", 25),
        new Employee("Tom Jones", 45), 
        new Employee("Ethan Hardy", 65),
        new Employee("Nancy Smith", 22), 
        new Employee("Deborah Sprightly", 29));
    
    LinkedList<Employee> employeeLinkedList = employeeList.stream()
        .collect(Collectors.toCollection(LinkedList::new));
    
    logger.info("Actual Employee List ==> " + employeeList);
    logger.info("Employee Linked List ==> " + employeeLinkedList);
  }
  
  private static class Employee {
    
    private String name;
    private Integer age;
    
    private Employee(String name, Integer age) {
      
      this.name = name;
      this.age = age;
    }
    
    @Override
    public String toString() {
      return "Name: " + this.name
          + " Age: " + this.age;
    }
    
  }
}
