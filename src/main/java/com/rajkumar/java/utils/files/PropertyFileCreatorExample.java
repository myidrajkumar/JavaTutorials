package com.rajkumar.java.utils.files;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creating / Reading properties file.
 * 
 * @author Rajkumar
 *
 */
public class PropertyFileCreatorExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private static final String XML_FILE = "properties.xml";
  private static final String TXT_FILE = "properties.txt";
  
  private PropertyFileCreatorExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Properties prop = new Properties();
    prop.setProperty("username", "Rajkumar");
    prop.setProperty("browser", "Mozilla Firefox");
    prop.setProperty("showEmail", "no");
    
    File propFile1 = new File(Constants.OUTPUT_DIR.value(), XML_FILE);
    File propFile2 = new File(Constants.OUTPUT_DIR.value(), TXT_FILE);
    try (FileOutputStream propStream1 = new FileOutputStream(propFile1);
        FileOutputStream propStream2 = new FileOutputStream(propFile2)) {
      
      Date now = new Date();
      prop.storeToXML(propStream1, "Created on " + now);
      prop.store(propStream2, "Created on " + now);
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
    Properties readProperties = new Properties();
    
    try (FileInputStream fis = new FileInputStream(TXT_FILE)) {
      readProperties.load(fis);
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
    logger.info("Found UserName = " + readProperties.getProperty("username"));
    logger.info(
        "Non-existing Vaule = " + readProperties.getProperty("non-exiting"));
    logger.info("Non-existing Vaule using default value = "
        + readProperties.getProperty("non-exiting", "default"));
    
  }
}
