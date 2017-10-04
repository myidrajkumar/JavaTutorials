package com.rajkumar.java.utils.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Getting current timestamp example.
 * 
 * @author Rajkumar
 *
 */
public class GettingCurrentTimestamp {
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    //This is to get current machine timestamp.
    Instant machineTimestamp = Instant.now();
    logger.info("Current Machine Timestamp in UTC ==> " + machineTimestamp);
    
    //Converting UTC to timezone
    ZonedDateTime pacificZoneTime = machineTimestamp.atZone(ZoneId.of("America/Los_Angeles"));
    logger.info("Pacific time now ==> " + pacificZoneTime);
    
    ZonedDateTime indianZoneTime = machineTimestamp.atZone(ZoneId.of("Asia/Calcutta"));
    logger.info("IndianZoneTime time now ==> " + indianZoneTime);
  }
  
}
