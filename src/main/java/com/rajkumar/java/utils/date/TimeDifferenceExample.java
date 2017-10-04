package com.rajkumar.java.utils.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Time difference between two dates example.
 * 
 * @author Rajkumar
 *
 */
public class TimeDifferenceExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    LocalDate dateFrom = LocalDate.of(2015, Month.JULY, 12);
    LocalDate dateTo = LocalDate.of(2016, Month.AUGUST, 22);
    
    Period intervalPeriod = Period.between(dateFrom, dateTo);
    
    logger.info("Difference ==> " + intervalPeriod.getDays() + " days "
        + intervalPeriod.getMonths() + " months "
        + intervalPeriod.getYears() + " years");
    
    /* The days,months and years obtained through ChronoUnit represent 
     * the entire difference individually i.e. total numbers of days 
     * between the two dates, total number of months and so on
     */
  }
  
}
