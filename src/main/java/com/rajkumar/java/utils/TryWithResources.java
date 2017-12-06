package com.rajkumar.java.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Auto closable resources do not need to be initialized in try() block.
 * 
 * @author Rajkumar
 *
 */
public class TryWithResources {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) throws IOException {
    
    InputStream inputStream = getInputStream();
    
    try (inputStream) {
      String readValue = new String(inputStream.readAllBytes());
      logger.info(readValue);
    }
    
    logger.info("after try-with-resource block");
    
  }
  
  private static InputStream getInputStream() {
    
    return new ByteArrayInputStream("test string".getBytes()) {
      
      @Override
      public void close() throws IOException {
        
        logger.info("closing");
        super.close();
      }
    };
  }
  
}
