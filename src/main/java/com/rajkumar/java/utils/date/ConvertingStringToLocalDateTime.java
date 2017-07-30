package com.rajkumar.java.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Date and Time formatter example.
 * 
 * @author Rajkumar
 *
 */
public class ConvertingStringToLocalDateTime {
  
  private static Logger logger = LogManager.getLogger();
  
  private ConvertingStringToLocalDateTime() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Converting String in format 'dd-MMM-yyyy' to LocalDate
    String dateStr = "28-Sep-2016 22:11:20";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    LocalDate localDate = LocalDate.parse(dateStr, formatter);
    logger.info("Input String with value: " + dateStr);
    logger.info("Converted Date in default ISO format: " + localDate + "\n");
    
    // We will use current date from the system clock
    LocalDateTime today = LocalDateTime.now();
    logger.info(
        "Current date using toString(same as ISO Standard): " + today);
    
    // Converting Date to a user specific format 1 - dd-MMM-yyyy
    String format = today.format(formatter);
    logger.info("Current date in format 'dd-MMM-yyyy hh:mm:ss': " + format);
  }
  
}
