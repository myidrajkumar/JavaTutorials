package com.rajkumar.java.utils.files;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reading and writing using scanner.
 * 
 * @author Rajkumar
 *
 */
public class ReadAndWriteUsingScannerExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private static final String FILE_NAME = "Company.xml";
  
  private ReadAndWriteUsingScannerExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String... args) {
    
    try (
        Scanner diskScanner = new Scanner(
            new File(Constants.INPUT_DIR.value(), FILE_NAME));
        PrintStream diskWriter = new PrintStream(
            new File(Constants.OUTPUT_DIR.value(), FILE_NAME));) {
      
      while (diskScanner.hasNextLine()) {
        diskWriter.println(diskScanner.nextLine());
      }
      
      logger.info("Written successfully");
    } catch (FileNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
  }
}
