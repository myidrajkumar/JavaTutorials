package com.rajkumar.java.utils.designpattern.creation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * State Pattern Example.
 * 
 * @author Seenappa
 *
 */
public class StateDesignPatternHubberspot {
  
  private static Logger logger = LogManager.getLogger();
  
  private StateDesignPatternHubberspot() { }

  private static interface Season {
    String getSeasonName();

    Season nextSeason(SeasonContext seasonContext);
  }
  
  private static class Monsoon implements Season {

    @Override
    public String getSeasonName() {
      return "Monsoon";
    }

    @Override
    public Season nextSeason(SeasonContext seasonContext) {
      Season season = new Winter();
      seasonContext.setSeason(season);
      return season;
    }
  }
  
  private static class Summer implements Season {

    @Override
    public String getSeasonName() {
      return "Summer";
    }

    @Override
    public Season nextSeason(SeasonContext seasonContext) {
      Season season = new Monsoon();
      seasonContext.setSeason(season);
      return season;
    }
  }
  
  private static class Winter implements Season {

    @Override
    public String getSeasonName() {
      return "Winter";
    }

    @Override
    public Season nextSeason(SeasonContext seasonContext) {
      Season season = new Summer();
      seasonContext.setSeason(season);
      return season;
    }
  }
  
  private static class SeasonContext {
    
    Season season;
    
    public SeasonContext(Season season) {
      this.season = season;
    }
    
    public Season nextSeason() {
      return this.season.nextSeason(this);
    }
    
    public void setSeason(Season season) {
      this.season = season;
    }
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    SeasonContext seasonContext = new SeasonContext(new Monsoon());
    Season nextSeason = seasonContext.nextSeason();
    logger.info(nextSeason.getSeasonName());
    
    nextSeason = seasonContext.nextSeason();
    logger.info(nextSeason.getSeasonName());
    
    nextSeason = seasonContext.nextSeason();
    logger.info(nextSeason.getSeasonName());
    
    nextSeason = seasonContext.nextSeason();
    logger.info(nextSeason.getSeasonName());

  }

}
