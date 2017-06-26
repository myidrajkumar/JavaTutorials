package com.rajkumar.java.utils.designpattern.behaviour;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Strategy Pattern Example.
 * 
 * @author Rajkumar
 *
 */
public class StrategyDesignPatternHubberspot {
  
  private static Logger logger = LogManager.getLogger();
  
  private StrategyDesignPatternHubberspot() { }

  private static interface TeamStrategy {
    public void playGame();
  }
  
  private static class AttackStrategy implements TeamStrategy {

    @Override
    public void playGame() {
      logger.info("Playing in 'Attack' mode");
      
    }
    
  }
  
  private static class DefendStrategy implements TeamStrategy {

    @Override
    public void playGame() {
      logger.info("Playing in 'Defend' mode");
      
    }
    
  }
  
  private abstract static class Team {
    private String teamName;
    private TeamStrategy teamStrategy;
    
    public Team() {
      this.teamName = getTeamName();
    }
    
    public void play() {
      logger.info(teamName + " is ");
      teamStrategy.playGame();
      
    }
    
    public void setTeamStrategy(TeamStrategy teamStrategy) {
      this.teamStrategy = teamStrategy;
    }
    
    public abstract String getTeamName();
  }
  
  private static class Germany extends Team {

    @Override
    public String getTeamName() {
      return "Germany";
    }
    
  }
  
  private static class Hungery extends Team {

    @Override
    public String getTeamName() {
      return "Hungery";
    }
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    Team germany = new Germany();
    Team hungery = new Hungery();
    
    TeamStrategy defendMode = new DefendStrategy();
    TeamStrategy attackMode = new AttackStrategy();
    
    germany.setTeamStrategy(defendMode);
    hungery.setTeamStrategy(attackMode);
    
    germany.play();
    hungery.play();
  }

}
