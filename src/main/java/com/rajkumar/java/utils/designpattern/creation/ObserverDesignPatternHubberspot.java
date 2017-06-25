package com.rajkumar.java.utils.designpattern.creation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern Example.
 * 
 * @author Seenappa
 *
 */
public class ObserverDesignPatternHubberspot {

  private static Logger logger = LogManager.getLogger();
  
  private ObserverDesignPatternHubberspot() { }
  
  private static interface Observer {
    void update(String productName);
  }
  
  private static interface Subject {
    
    void registerObserver(Observer observer);
    
    void removeObserver(Observer observer);
    
    void notifyObservers();
  }
  
  private static class Customer implements Observer {

    private String customerName;
    
    public Customer(String customerName) {
      this.customerName = customerName;
    }
    
    @Override
    public void update(String productName) {
      logger.info("Hello " + customerName + ", " + productName + " is available");
    }
  }
  
  private static class Mobile implements Subject {

    private String productName = "Samsung";
    private List<Observer> customerList = new ArrayList<>();
    
    
    @Override
    public void registerObserver(Observer observer) {
      customerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
      customerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
      logger.info(productName + " is ready for available");
      for (Observer customer : customerList) {
        customer.update(productName);
      }
    }
    
    public void setAvailable(boolean isAvailable) {
      if (isAvailable) {
        notifyObservers();
      }
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Customer customer1 = new Customer("Raj");
    Customer customer2 = new Customer("Kumar");
    
    Mobile samsung = new Mobile();
    samsung.setAvailable(false);
    
    samsung.registerObserver(customer1);
    samsung.registerObserver(customer2);
    
    samsung.setAvailable(true);
  }

}
