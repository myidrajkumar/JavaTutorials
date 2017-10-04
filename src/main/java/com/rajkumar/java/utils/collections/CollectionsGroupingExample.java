package com.rajkumar.java.utils.collections;

import com.rajkumar.java.utils.lib.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Collections GroupingBy Example.
 * 
 * @author Rajkumar
 *
 */
public class CollectionsGroupingExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    List<Employee> employeeList = Arrays.asList(
        new Employee("Tom Jones", 45, 12000.00,Department.MARKETING),
        new Employee("Harry Major", 26, 20000.00, Department.LEGAL),
        new Employee("Ethan Hardy", 65, 30000.00, Department.LEGAL),
        new Employee("Nancy Smith", 22, 15000.00, Department.MARKETING),
        new Employee("Catherine Jones", 21, 18000.00, Department.HR),
        new Employee("James Elliot", 58, 24000.00, Department.OPERATIONS),
        new Employee("Frank Anthony", 55, 32000.00, Department.MARKETING),
        new Employee("Michael Reeves", 40, 45000.00, Department.OPERATIONS));
    
    //This 'GroupingBy' method is just with 'classifier' argument
    Map<Department, List<Employee>> employeeMap = employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment));
    
    logger.info("Employees grouped by department");
    employeeMap.forEach((Department key, 
        List<Employee> empList) -> logger.info(key + " -> " + empList));
    logger.info(Constants.LINE_SEPERATOR);
    
    //This 'GroupingBy' method is with 'classifier' and 'downstream' arguments
    Map<Department, Set<Employee>> employeeSetMap = employeeList.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toSet()));
    
    logger.info("Employees grouped by department with unique employees");
    employeeSetMap.forEach((Department key, 
        Set<Employee> empSet) -> logger.info(key + " -> " + empSet));
    logger.info(Constants.LINE_SEPERATOR);
    
    /* This 'GroupingBy' method is with 'classifier','downstream'  and 'mapFactory' arguments.
     * Here the return type is 'TreeMap'; not 'HashMap'
     */
    Map<Department, Set<Employee>> employeeTreeHashtMap = employeeList.stream()
        .collect(
            Collectors.groupingBy(
                Employee::getDepartment, TreeMap::new, Collectors.toSet()));
    
    logger.info("Employees grouped by department with unique employees");
    employeeTreeHashtMap.forEach((Department key, 
        Set<Employee> empSet) -> logger.info(key + " -> " + empSet));
    
    
    logger.info("All the above methods are not for concurrent scenarios; \n"
        + " For Concurrent scenarios, use 'groupingByConcurrent' methods "
        + "which have same singature as above ");
  }
  
  private static class Employee {
    
    private String name;
    
    @SuppressWarnings("unused")
    private Integer age;
    
    @SuppressWarnings("unused")
    private Double salary;
    
    private Department department;
   
    public Employee(String name, Integer age, Double salary, Department department) {
      this.name = name;
      this.age = age;
      this.salary = salary;
      this.department = department;
    }
    
    
    public Department getDepartment() {
      
      return department;
    }
   
    @Override
    public String toString() {
      return "Employee Name:" + this.name;
    }
   
  }
  
  private enum Department {
    HR, OPERATIONS, LEGAL, MARKETING
  }
  
}
