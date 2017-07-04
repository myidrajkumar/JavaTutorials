package com.rajkumar.java.utils.serialize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/**
 * Externalize example but super class is not externalised.
 * 
 * @author Rajkumar
 *
 */
public class ExternalizableExample2 {
  
  private static Logger logger = LogManager.getLogger();
  
  private ExternalizableExample2() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // create a Car object
    Car car = new Car("Mitsubishi", 2009, "1234", "60");
    Car newCar = null;
    
    // serialize the car
    try (
        FileOutputStream fo = new FileOutputStream(
            Constants.OUTPUT_DIR + "output.ser");
        ObjectOutputStream so = new ObjectOutputStream(fo) ) {
      
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
        ObjectInputStream si = new ObjectInputStream(fi) ) {
      
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
  
  private static class Car extends Automobile implements Externalizable {
    
    private String name;
    private int year;
    
    /*
     * mandatory public no-argument constructor
     */
    public Car() {
      super();
    }
    
    public Car(String name, int year, String regNo, String mileage) {
      super(regNo, mileage);
      this.name = name;
      this.year = year;
    }
    
    /**
     * Mandatory writeExernal method.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
      
      /*
       * Since the superclass does not implement the Serializable interface we
       * explicitly do the saving.
       */
      out.writeObject(regNo);
      out.writeObject(mileage);
      
      // Now the subclass fields
      out.writeObject(name);
      out.writeInt(year);
    }
    
    /**
     * Mandatory readExternal method.
     */
    @Override
    public void readExternal(ObjectInput in)
        throws IOException, ClassNotFoundException {
      
      /*
       * Since the superclass does not implement the Serializable interface we
       * explicitly do the restoring
       */
      regNo = (String) in.readObject();
      mileage = (String) in.readObject();
      
      // Now the subclass fields
      name = (String) in.readObject();
      year = in.readInt();
    }
    
    /**
     * Prints out the fields. used for testing!
     */
    @Override
    public String toString() {
      
      return "Reg No: " + regNo + "\n" + "Mileage: " + mileage + "\n" + "Name: "
          + name + "\n" + "Year: " + year;
    }
  }
  
  /**
   * Automobile.
   * 
   * <p>The superclass does not implement externalizable</p>
   */
  private static class Automobile {
    
    protected String regNo;
    protected String mileage;
    
    /*
     * A public no-arg constructor
     */
    public Automobile() {
      this.regNo = null;
      this.mileage = null;
    }
    
    Automobile(String regNo, String mileage) {
      this.regNo = regNo;
      this.mileage = mileage;
    }
  }
  
}
