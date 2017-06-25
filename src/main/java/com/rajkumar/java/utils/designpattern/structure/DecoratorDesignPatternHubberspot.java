package com.rajkumar.java.utils.designpattern.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Decorator Pattern Example.
 *  
 * @author Seenappa
 *
 */
public class DecoratorDesignPatternHubberspot {

  private static Logger logger = LogManager.getLogger();
  
  private DecoratorDesignPatternHubberspot() { }
  
  private static interface Pizza {
    
    String getDescription();
    
    Double cost();
  }
  
  private static class PanPizza implements Pizza {

    @Override
    public String getDescription() {
      return "Pan Pizza";
    }

    @Override
    public Double cost() {
      return 10.0;
    }
    
  }
  
  private abstract static class PizzaDecorator implements Pizza {
    Pizza pizza;
  }
  
  private static class Onions extends PizzaDecorator {

    public Onions(Pizza pizza) {
      this.pizza = pizza;
    }
    
    @Override
    public String getDescription() {
      return "Onions";
    }

    @Override
    public Double cost() {
      return pizza.cost() + 2.0;
    }
    
  }
  
  private static class Cheese extends PizzaDecorator {

    public Cheese(Pizza pizza) {
      this.pizza = pizza;
    }
    
    @Override
    public String getDescription() {
      return "Onions";
    }

    @Override
    public Double cost() {
      return pizza.cost() + 4.0;
    }
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Pizza pizza = new PanPizza();
    pizza = new Onions(pizza);
    pizza = new Cheese(pizza);
    
    logger.info(pizza.cost());

  }

}
