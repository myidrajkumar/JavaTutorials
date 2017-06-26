package com.rajkumar.java.utils.designpattern.creation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract Factory Pattern Example.
 * 
 * @author Rajkumar
 *
 */
public class AbstractFactoryDesignPatternHubberspot {

  private static Logger logger = LogManager.getLogger();
  
  private AbstractFactoryDesignPatternHubberspot() { }
  
  private static interface Car {
    void drive();
  }
  
  private static class I10 implements Car {

    @Override
    public void drive() {
      logger.info("Driving I10...");
    }
  }
  
  private static class I20 implements Car {

    @Override
    public void drive() {
      logger.info("Driving I20...");
    }
  }
  
  private static class Brio implements Car {

    @Override
    public void drive() {
      logger.info("Driving Brio...");
    }
  }
  
  private static class HyndaiCity implements Car {

    @Override
    public void drive() {
      logger.info("Driving HyndaiCity...");
    }
  }
  
  private static interface CarFactory {
    Car createCar(String carType);
  }
  
  private static class HyndaiFactory implements CarFactory {

    @Override
    public Car createCar(String carType) {
      if ("brio".equals(carType)) {
        return new Brio();
      } else if ("hyndaicity".equals(carType)) {
        return new HyndaiCity();
      } 
      throw new UnsupportedOperationException("Please mention valid Hyndai car type");
    }
    
  }
  
  private static class HondaFactory implements CarFactory {

    @Override
    public Car createCar(String carType) {
      if ("i10".equals(carType)) {
        return new I10();
      } else if ("i20".equals(carType)) {
        return new I20();
      } 
      
      throw new UnsupportedOperationException("Please mention valid Honda car type");
    }
    
  }
  
  private static class CarFactoryProducer {
    
    private CarFactoryProducer() { }
    
    private static CarFactory getCarFactory(String factoryType) {
      if ("honda".equals(factoryType)) {
        return new HondaFactory();
      } else if ("hyndai".equals(factoryType)) {
        return new HyndaiFactory();
      }
      
      throw new UnsupportedOperationException("Please mention valid car type");
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    CarFactory hyndaiFactory = CarFactoryProducer.getCarFactory("hyndai");
    
    Car brio = hyndaiFactory.createCar("brio");
    brio.drive();

  }

}
