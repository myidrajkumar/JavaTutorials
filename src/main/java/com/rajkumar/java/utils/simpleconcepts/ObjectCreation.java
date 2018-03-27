package com.rajkumar.java.utils.simpleconcepts;

import com.rajkumar.java.utils.lib.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Types object creation example.
 * 
 * @author Rajkumar
 *
 */
public class ObjectCreation {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String... args) throws Exception {
    
    //1. By using new keyword
    Employee newEmployee = new Employee();
    newEmployee.setName("Employee1");
    
    printEmployeeDetails(logger, newEmployee);
    
    //2.a By using Class class's newInstance() method
    @SuppressWarnings("deprecation")
    Employee classFirstEmployee = (Employee) Class
        .forName(
            "com.rajkumar.java.utils.simpleconcepts.ObjectCreation.Employee")
        .newInstance();  //NOSONAR
    classFirstEmployee.setName("Employee2-A");
    
    printEmployeeDetails(logger, classFirstEmployee);
    
    //2.b we can simply do this
    @SuppressWarnings("deprecation")
    Employee classSecondEmployee = Employee.class.newInstance();  //NOSONAR
    classSecondEmployee.setName("Employee2-B");
    
    printEmployeeDetails(logger, classSecondEmployee);
    
    // By using Constructor class's newInstance() method
    Constructor<Employee> constructor = Employee.class.getConstructor();
    Employee constructorEmployee = constructor.newInstance();
    constructorEmployee.setName("Employee3");
    
    printEmployeeDetails(logger, constructorEmployee);
    
    // By using clone() method
    Employee cloneEmployee = (Employee) constructorEmployee.clone();
    cloneEmployee.setName("Employee4");
    
    printEmployeeDetails(logger, cloneEmployee);
    
    // By using De-serialization
    
    // Serialization and De-serialization
    try (
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("data.obj"));
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("data.obj"))) {
      
      out.writeObject(cloneEmployee);
      
      Employee deserialzedEmployee = (Employee) in.readObject();
      
      deserialzedEmployee.setName("Employee5");
      printEmployeeDetails(logger, deserialzedEmployee);
    }
    
  }
  
  /**
   * This method just logs the details of employee.
   * 
   * @param logger to log employee details
   * @param emp the employee
   */
  private static void printEmployeeDetails(Logger logger, Employee emp) {
    
    logger.info(emp + ", hashcode : " + emp.hashCode());
  }
  
  private static class Employee implements Cloneable, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    
    public Employee() {
      
      logger.info("Employee Constructor Called..."
          + this.getClass().getCanonicalName());
    }
    
    public void setName(String name) {
      
      this.name = name;
    }
    
    @Override
    public int hashCode() {
      
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }
    
    @Override
    public boolean equals(Object obj) {
      
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Employee other = (Employee) obj;
      if (name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!name.equals(other.name)) {
        return false;
      }
      return true;
    }
    
    @Override
    public String toString() {
      
      return "Employee [name=" + name + "]";
    }
    
    @Override
    public Object clone() {   //NOSONAR
      
      Object obj = null;
      try {
        obj = super.clone();
      } catch (CloneNotSupportedException exception) {
        logger.error(Utils.getException(exception));
      }
      return obj;
    }
  }
}
