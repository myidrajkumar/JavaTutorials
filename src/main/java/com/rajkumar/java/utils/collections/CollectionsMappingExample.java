package com.rajkumar.java.utils.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mapping example in collections.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsMappingExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tom Jones", 45),
        new Employee("Harry Andrews", 45),
        new Employee("Ethan Hardy", 65),
        new Employee("Nancy Smith", 22),
        new Employee("Deborah Sprightly", 29));
    
    List<String> empNamesList = employeeList.stream()
        .collect(Collectors.mapping(Employee::getName, Collectors.toList()));
    logger.info("List of employees ==> " + empNamesList);
    
    Optional<Integer> maxAge = employeeList.stream()
        .collect(
            Collectors.mapping(Employee::getAge, 
                Collectors.maxBy(Integer::compareTo)));
    logger.info("Maximum Age ==> " + (maxAge.isPresent() ? maxAge.get() : "No one has age"));
  }
  
  private static class Employee {
    
    private String name;
    private Integer age;
    
    private Employee(String name, Integer age) {
      
      this.name = name;
      this.age = age;
    }
    
    public String getName() {
      
      return name;
    }
    
    public Integer getAge() {
      
      return age;
    }

  }
}
