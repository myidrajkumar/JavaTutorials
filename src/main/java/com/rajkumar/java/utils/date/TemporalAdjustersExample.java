package com.rajkumar.java.utils.date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Temporal Adjusters example to get last working day and so.
 * 
 * @author Rajkumar
 *
 */
public class TemporalAdjustersExample {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Logger logger = LogManager.getLogger();
    
    // Last day of current month
    LocalDate lastDayofCurrentMonth = LocalDate.now()
        .with(TemporalAdjusters.lastDayOfMonth());
    logger.info("Last day of current month ==> " + lastDayofCurrentMonth);
    
    // Last day of month for year-month combination-"Apr, 2017"
    LocalDate lastDayofMonthYear = YearMonth.of(2017, 04).atEndOfMonth();
    logger.info("Last day of month for 'Apr, 2017' ==> "
        + lastDayofMonthYear.getDayOfWeek() + "," + lastDayofMonthYear);
  }
  
}
