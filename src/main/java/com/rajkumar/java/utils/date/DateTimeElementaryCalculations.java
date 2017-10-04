package com.rajkumar.java.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Date time basic calculations example.
 * 
 * @author Rajkumar
 *
 */
public class DateTimeElementaryCalculations {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    performDateCalculations();
    performTimeCalculations();
    performDateTimeCalculations();
  }

  private static void performDateCalculations() {
    
    LocalDate baseDate = LocalDate.of(2016, Month.JANUARY, 1);
    logger.info("Base Date: " + baseDate);
    
    logger.info("Date after 10 days: " + baseDate.plusDays(10));
    logger.info("Date before 10 days: " + baseDate.minusDays(10));
    
    logger.info("Date after 2 weeks: " + baseDate.plusWeeks(2));
    logger.info("Date before 2 weeks: " + baseDate.minusWeeks(2));
    
    logger.info("Date after 2 months: " + baseDate.plusMonths(2));
    logger.info("Date before 2 months: " + baseDate.minusMonths(2));
    
    logger.info("Date after 2 years: " + baseDate.plusYears(2));
    logger.info("Date before 2 years: " + baseDate.minusYears(2));
  }
  
  private static void performTimeCalculations() {
    
    LocalTime baseTime = LocalTime.of(10, 20, 30);
    logger.info("Base Time: " + baseTime);
    
    logger.info("Time after 10 hours: " + baseTime.plusHours(10));
    logger.info("Time before 10 hours: " + baseTime.minusHours(10));
    
    logger.info("Time after 20 minutes: " + baseTime.plusMinutes(20));
    logger.info("Time before 20 minutes: " + baseTime.minusMinutes(20));
    
    logger.info("Time after 40 seconds: " + baseTime.plusSeconds(40));
    logger.info("Time before 40 seconds: " + baseTime.minusSeconds(40));

  }
  
  private static void performDateTimeCalculations() {
    
    LocalDate baseDate = LocalDate.of(2016, Month.JANUARY, 1);
    LocalTime baseTime = LocalTime.of(10, 20, 30);
    
    LocalDateTime dateTime = LocalDateTime.of(baseDate, baseTime);
    logger.info("Base Date: " + dateTime);
    
    logger.info("Date after 10 days: " + dateTime.plusDays(10));
    logger.info("Date before 10 days: " + dateTime.minusDays(10));
    
    logger.info("Time after 10 hours: " + dateTime.plusHours(10));
    logger.info("Time before 10 hours: " + dateTime.minusHours(10));

  }
  
}
