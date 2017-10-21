package com.rajkumar.java.utils.collections;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Collections collecting and then example.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsCollectingThenExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tom Jones", 45, 15000.00),
        new Employee("Tom Jones", 45, 7000.00),
        new Employee("Ethan Hardy", 65, 8000.00),
        new Employee("Nancy Smith", 22, 10000.00),
        new Employee("Deborah Sprightly", 29, 9000.00));
    
    String empName = employeeList.stream()
        .collect(
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparing(Employee::getAge)), 
                emp -> emp.isPresent() ? emp.get().getName() : "none")
            );
    logger.info("Most aged employee: " + empName);
    
    String avgSalary = employeeList.stream().collect(
        Collectors.collectingAndThen(
            Collectors.averagingDouble(Employee::getSalary),
            new DecimalFormat("'$'0.00")::format));
    logger.info("Average salary in $: " + avgSalary);
    
  }
  
  private static class Employee {
    
    private String name;
    private Integer age;
    private Double salary;
    
    public Employee(String name, Integer age, Double salary) {
      
      this.setName(name);
      this.age = age;
      this.salary = salary;
    }
    
    
    public String getName() {
      
      return name;
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
  }
  
}
