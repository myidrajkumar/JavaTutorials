package com.rajkumar.java.utils.designpattern.creation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Template Pattern Example.
 * 
 * @author Seenappa
 *
 */
public class TemplateDesignPatternHubberspot {
  
  private static Logger logger = LogManager.getLogger();
  
  private TemplateDesignPatternHubberspot() { }

  protected abstract static class FileProcessor {
    
    public final void processFile(boolean isReadMode) {
      openFile();
      
      if (isReadMode) {
        readFile();
      } else {
        writeFile();
      }
      
      closeFile();
      
    }

    protected abstract void readFile();
    
    protected abstract void writeFile();

    private static void closeFile() {
      logger.info("Closing the file...");
      
    }

    private static void openFile() {
      logger.info("Trying to open the file...");
    }

  }
  
  private static class PdfFileProcessor extends FileProcessor  {

    @Override
    protected void readFile() {
      logger.info("Reading the PDF file...");
    }

    @Override
    protected void writeFile() {
      logger.info("Writing to the PDF file...");
    }
    
  }
  
  private static class WordFileProcessor extends FileProcessor  {

    @Override
    protected void readFile() {
      logger.info("Reading the Word file...");
    }

    @Override
    protected void writeFile() {
      logger.info("Writing to the Word file...");
    }
    
  }
  
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    boolean isReadMode = true;

    FileProcessor fileProcessor = new PdfFileProcessor();
    fileProcessor.processFile(isReadMode);
    
    logger.info("========================");
    
    isReadMode = false;
    
    fileProcessor = new WordFileProcessor();
    fileProcessor.processFile(isReadMode);
  }

}
