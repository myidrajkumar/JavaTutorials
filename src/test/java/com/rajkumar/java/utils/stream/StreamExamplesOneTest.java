package com.rajkumar.java.utils.stream;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class StreamExamplesOneTest {

  @Test
  public final void testIsMatchAll_Valid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(4,8,10);
    
    assertThat("Elements are not matching",
        StreamExamplesOne.isMatchAll(list, predicate),
        CoreMatchers.is(true));
    
  }
  
  @Test
  public final void testIsMatchAll_Invalid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(6,1,8);
    
    assertThat("Elements are matching",
        StreamExamplesOne.isMatchAll(list, predicate),
        CoreMatchers.is(false));
    
  }

  @Test
  public final void testIsMatchAny_Valid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(4,1,2);
    
    assertThat("No element is matching",
        StreamExamplesOne.isMatchAny(list, predicate),
        CoreMatchers.is(true));
    
  }
  
  @Test
  public final void testIsMatchAny_Invalid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(3,1,5);
    
    assertThat("Atlease one element is matching",
        StreamExamplesOne.isMatchAny(list, predicate),
        CoreMatchers.is(false));
    
  }

  @Test
  public final void testIsMatchNone_Valid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(3,1,5);
    
    assertThat("Atlease one element is matching",
        StreamExamplesOne.isMatchNone(list, predicate),
        CoreMatchers.is(true));
    
  }
  
  @Test
  public final void testIsMatchNone_Invalid() {
    
    Predicate<Integer> predicate = num -> num % 2 == 0;
    List<Integer> list = Arrays.asList(4,1,5);
    
    assertThat("No element is matching",
        StreamExamplesOne.isMatchNone(list, predicate),
        CoreMatchers.is(false));
    
  }
  
  @Test
  public final void testGetSum_Integers() {
    
    List<Integer> list = Arrays.asList(4,1,5);
    
    assertThat("Sum is not correct", 
        StreamExamplesOne.getSum(list), 
        CoreMatchers.is(10));
    
  }
  
  @Test
  public final void testReduceOperation_ValidList() {
    
    List<String> countries = Arrays.asList(
        "Germany", "England", "China",
        "Denmark", "Brazil", "France", "Australia");
    
    assertThat("This is not the smallest country", 
        StreamExamplesOne.getSmallestItem(countries), 
        CoreMatchers.is("Australia"));
    
  }
  
  @Test
  public final void testReduceOperation_InvalidList() {
    
    List<String> countries = Arrays.asList();
    
    assertThat("This is the smallest country", 
        StreamExamplesOne.getSmallestItem(countries), 
        CoreMatchers.nullValue());
    
  }
  
  @Test
  public final void testParallelStream() {
    
    final class Employee {
       
      public int salary;
      
      public Employee(int sal) {
        this.salary = sal;
      }
      
    } 
    
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee(2000));
    employeeList.add(new Employee(3000));
    employeeList.add(new Employee(4000));
    employeeList.add(new Employee(6000));
     
    Predicate<Employee> seniorEmp = employee -> employee.salary > 3000 && employee.salary < 6000;
     
    OptionalDouble averageSalary = employeeList.parallelStream()
         .filter(seniorEmp)
         .mapToDouble(employee -> employee.salary)
         .average();
     
    assertThat("Average is not correct", 
        averageSalary.getAsDouble(), 
        CoreMatchers.is(4000.0));
    
  }
  
  @Test
  public final void testSequentialStream() {
    
    final class Employee {
       
      public int salary;
      
      public Employee(int sal) {
        this.salary = sal;
      }
      
    } 
    
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee(2000));
    employeeList.add(new Employee(3000));
    employeeList.add(new Employee(4000));
    employeeList.add(new Employee(6000));
     
    Predicate<Employee> juniorEmp = employee -> employee.salary > 1000 && employee.salary < 4000;
     
    int salarySum = employeeList.stream()
         .filter(juniorEmp)
         .mapToInt(employee -> employee.salary)
         .sum();
     
    assertThat("Salary sum is not correct", 
        salarySum, 
        CoreMatchers.is(5000));
    
  }
}
