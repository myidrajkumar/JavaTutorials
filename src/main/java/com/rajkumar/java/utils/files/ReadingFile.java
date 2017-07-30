package com.rajkumar.java.utils.files;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reading entire file using streams example.
 * 
 * @author Rajkumar
 *
 */
public class ReadingFile {
  
  private static Logger logger = LogManager.getLogger();
  
  private ReadingFile() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String fileName = Constants.INPUT_DIR + "Company.xml";
    
    // read file into stream, try-with-resources
    try (Stream<String> linesStream = Files.lines(Paths.get(fileName))) {
      linesStream.forEach(logger::info);
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
    logger.info(Constants.LINE_SEPERATOR);
    
    // Another
    try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
      br.lines().forEach(logger::info);
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
  }
  
}
