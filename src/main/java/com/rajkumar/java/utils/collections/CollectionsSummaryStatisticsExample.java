package com.rajkumar.java.utils.collections;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Summarising functions in collection streams.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsSummaryStatisticsExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tom Jones", 45, 15000.00,190),
        new Employee("Tom Jones", 45, 7000.00,220),
        new Employee("Ethan Hardy", 65, 8000.00,1008),
        new Employee("Nancy Smith", 22, 10000.00,5),
        new Employee("Deborah Sprightly", 29, 9000.00,45));
    
    
    IntSummaryStatistics intSummaryStatistics = employeeList
        .stream()
        .collect(Collectors.summarizingInt(Employee::getAge));
    
    logger.info("IntSummaryStatistics for age: " + intSummaryStatistics);
    
    LongSummaryStatistics longSummaryStatistics = employeeList
        .stream()
        .collect(Collectors.summarizingLong(Employee::getLeaves));
    logger.info("LongSummaryStatistics for leaves: " + longSummaryStatistics);
    
    DoubleSummaryStatistics doubleSummaryStatistics = employeeList
        .stream()
        .collect(Collectors.summarizingDouble(Employee::getSalary));
    logger.info("DoubleSummaryStatistics for salary: " + doubleSummaryStatistics);
  }
  
  
  private static class Employee {
    
    private Integer age;
    private Double salary;
    private long leaves;
   
    private Employee(@SuppressWarnings("unused") String name, //NOSONAR
        Integer age, Double salary, long leaves) {
      this.age = age;
      this.salary = salary;
      this.leaves = leaves;
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
