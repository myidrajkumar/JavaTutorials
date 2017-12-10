package com.rajkumar.java.utils.designpattern.structure;

import java.util.HashMap;
import java.util.Random;

import org.apache.logging.log4j.LogManager;

/**
 * The Flyweight Pattern is a Structural Pattern. Its intent is to minimize
 * memory usage. The intent of the Flyweight Pattern is to use sharing to
 * support a large number of objects that have part of their internal state in
 * common while the other part of state varies.
 * 
 * @author Rajkumar
 *
 */
public class FlyWeightFactoryDesignPatternMedium {
  
  private interface Character {
    
    public void render();
  }
  
  private static class CharacterA implements Character {
    
    private String color;
    private int xPosition;
    private int yPosition;
    
    public CharacterA(String color) {
      
      this.color = color;
    }
    
    public void setX(int x) {
      
      this.xPosition = x;
    }
    
    public void setY(int y) {
      
      this.yPosition = y;
    }
    
    @Override
    public void render() {
      
      LogManager.getLogger().info("Character at position " + xPosition + ", "
          + yPosition + " with color " + color);
    }
    
  }
  
  private static class CharacterFactory {
    
    private CharacterFactory() {}
    
    private static HashMap<String, Character> characterMap = new HashMap<>();
    
    public static Character getCharacter(String color) {
      
      return characterMap.computeIfAbsent(color, CharacterA::new);
    }
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    final String[] colors = {"Red", "Green", "Blue", "White", "Black"};
    
    for (int i = 0; i < 15; ++i) {
      
      Random random = new Random();
      CharacterA character = (CharacterA) CharacterFactory
          .getCharacter(colors[random.nextInt(colors.length)]);
      character.setX(random.nextInt(100));
      character.setY(random.nextInt(100));
      character.render();
    }
    
  }
  
}
