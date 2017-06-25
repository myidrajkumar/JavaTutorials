package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converting duration as time unit.
 * 
 * @author Seenappa
 *
 */
public class ConvertDuration2TimeUnit {

  private static final Pattern DURATION_STRING_PATTERN = Pattern
      .compile("^(\\d+)\\s?(ns|us|ms|s|m|h|d)?$");
  
  private static Logger logger = LogManager.getLogger();

  private ConvertDuration2TimeUnit() { }

  /**
   * Convert passed duration to time unit.
   * 
   * @param duration Duration
   * @return time unit
   */
  public static long convertDurationString2Nanos(String duration) {
    final Matcher matcher = DURATION_STRING_PATTERN.matcher(duration);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("duration string format invalid");
    }

    final long sourceDuration = Long.parseLong(matcher.group(1));
    final String sourceUnitString = matcher.group(2);

    final TimeUnit sourceTimeUnit;
    if ("ns".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.NANOSECONDS;
    } else if ("us".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.MICROSECONDS;
    } else if ("ms".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.MILLISECONDS;
    } else if ("s".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.SECONDS;
    } else if ("m".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.MINUTES;
    } else if ("h".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.HOURS;
    } else if ("d".equalsIgnoreCase(sourceUnitString)) {
      sourceTimeUnit = TimeUnit.DAYS;
    } else {
      logger.info("Invalid unit; So Milliseconds will be considered");
      sourceTimeUnit = TimeUnit.MILLISECONDS;
    }

    return sourceTimeUnit.toNanos(sourceDuration);
  }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    long nanoSecs = ConvertDuration2TimeUnit
        .convertDurationString2Nanos("20 ms");
    logger.info(nanoSecs);
  }
}
