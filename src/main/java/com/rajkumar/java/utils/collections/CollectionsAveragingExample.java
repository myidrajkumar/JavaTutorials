package com.rajkumar.java.utils.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mathematical Averaging example.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsAveragingExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tom Jones", 45, 15000.00, 190),
        new Employee("Tom Jones", 45, 7000.00, 220),
        new Employee("Ethan Hardy", 65, 8000.00, 1008),
        new Employee("Nancy Smith", 22, 10000.00, 5),
        new Employee("Deborah Sprightly", 29, 9000.00, 45));
    
    // Using Collectors.averagingInt()
    Double avgAge = employeeList.stream()
        .collect(Collectors.averagingInt(Employee::getAge));
    logger.info("Average age using Collectors.averagingInt: " + avgAge);
    
    // Using Collectors.averagingLong()
    Double avgLeaves = employeeList.stream()
        .collect(Collectors.averagingLong(Employee::getLeaves));
    logger.info("Average leaves using Collectors.averagingLong: " + avgLeaves);
    
    // Using Collectors.averagingDouble()
    Double avgSalary = employeeList.stream()
        .collect(Collectors.averagingDouble(Employee::getSalary));
    logger
        .info("Average salary using Collectors.averagingDouble: " + avgSalary);
    
  }
  
  private static class Employee {
    
    @SuppressWarnings("unused")
    private String name;
    
    private Integer age;
    private Double salary;
    private long leaves;
    
    public Employee(String name, Integer age, Double salary, long leaves) {
      
      this.setName(name);
      this.age = age;
      this.salary = salary;
      this.leaves = leaves;
    }
    
    public void setName(String name) {
      
      this.name = name;
    }
    
    public Integer getAge() {
      
      return age;
    }
    
    public Double getSalary() {
      
      return salary;
    }
    
    public long getLeaves() {
      
      return leaves;
    }
    
  }
  
}
