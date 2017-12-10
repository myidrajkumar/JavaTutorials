package com.rajkumar.java.utils.serialize;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple externalization example.
 * 
 * @author Rajkumar
 *
 */
public class ExternalizableExample1 {
  
  private static Logger logger = LogManager.getLogger();
  
  private ExternalizableExample1() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // create a Car object
    Car car = new Car("Mitsubishi", 2009);
    Car newCar = null;
    
    // serialize the car
    try (
        FileOutputStream fo = new FileOutputStream(
            Constants.OUTPUT_DIR + "output.ser");
        ObjectOutputStream so = new ObjectOutputStream(fo)) {
      
      so.writeObject(car);
      so.flush();
      
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
      System.exit(1);
    }
    
    // de-serialize the Car
    try (
        FileInputStream fi = new FileInputStream(
            Constants.OUTPUT_DIR + "output.ser");
        ObjectInputStream si = new ObjectInputStream(fi)) {
      
      newCar = (Car) si.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      logger.error(Utils.getException(exception));
      System.exit(1);
    }
    
    /*
     * Print out the original and new car information
     */
    logger.info("The original car is ");
    logger.info(car);
    logger.info("The new car is ");
    logger.info(newCar);
  }
  
  private static class Car implements Externalizable {
    
    String name;
    int year;
    
    /*
     * mandatory public no-argument constructor
     */
    public Car() {
      super();
    }
    
    public Car(String name, int year) {
      this.name = name;
      this.year = year;
    }
    
    /**
     * Mandatory writeExernal method.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
      
      out.writeObject(name);
      out.writeInt(year);
    }
    
    /**
     * Mandatory readExternal method.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      
      name = (String) in.readObject();
      year = in.readInt();
    }
    
    /**
     * Prints out the fields. used for testing!
     */
    @Override
    public String toString() {
      
      return "Name: " + name + "\n" + "Year: " + year;
    }
  }
  
}
