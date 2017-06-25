package com.rajkumar.java.utils.designpattern.creation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory Pattern Example.
 * 
 * @author Seenappa
 *
 */
public class FactoryDesignPatternHubberspot {

  private static Logger logger = LogManager.getLogger();
  
  private FactoryDesignPatternHubberspot() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    AnimalFactory factory = new AnimalFactory();
    logger.info(factory.createAnimal("dog").eat());
  }
  
  private static interface Animal {
    public String eat();
  }
  
  private static class Dog implements Animal {

    @Override
    public String eat() {
      return "Dog is eating";
    }
  }
  
  private static class Cat implements Animal {

    @Override
    public String eat() {
      return "Cat is eating";
    }
  }
  
  private static class Fox implements Animal {

    @Override
    public String eat() {
      return "Fox is eating";
    }
  }
  
  private static class AnimalFactory {
    public Animal createAnimal(String animalType) {
      switch (animalType.toLowerCase()) {
        case "cat" :
          return new Cat();
        case "dog" :
          return new Dog();
        case "fox":
          return new Fox();
        default:
          throw new UnsupportedOperationException("Provide valid animal type");
      }
    }
  }
}
