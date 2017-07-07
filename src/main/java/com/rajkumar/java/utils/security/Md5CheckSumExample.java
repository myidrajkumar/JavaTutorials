package com.rajkumar.java.utils.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * MD5 checksum example.
 * 
 * @author Rajkumar
 *
 */
public class Md5CheckSumExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private Md5CheckSumExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String checksum = null;
    try {
      checksum = DigestUtils
          .md5Hex(new FileInputStream(Constants.INPUT_DIR + "Company.xml"));
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info("Checksum => " + checksum);
    
  }
  
}
