package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Downloading file from Web.
 * 
 * @author Rajkumar
 *
 */
public class DownloadFileFromWebExample {

  private static Logger logger = LogManager.getLogger();

  private DownloadFileFromWebExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    URL url = null;
    try {
      url = new URL("http://www.codingalpha.com");
    } catch (MalformedURLException exception) {
      logger.error(Utils.getException(exception));
      return;
    }

    
    int byteCount = 0;
    byte[] byteData = new byte[1024];
    String fileName = "CodingAlpha.html";
    
    try {
      //Better solution has to be identified for creation.
      if (!Paths.get(Constants.OUTPUT_DIR.value()).toFile().exists()) {
        Files.createDirectory(Paths.get(Constants.OUTPUT_DIR.value()));
      }
      
      if (!Paths.get(Constants.OUTPUT_DIR.value(), fileName).toFile().exists()) {
        Files.createFile(Paths.get(Constants.OUTPUT_DIR.value(), fileName));
      }
     
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
      return;
    }
    
    File file = Paths.get(Constants.OUTPUT_DIR.value(), fileName).toFile();
    
    try (
        BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
        FileOutputStream outputStream = new FileOutputStream(file)) {

      while ((byteCount = inputStream.read(byteData, 0, 1024)) != -1) {
        outputStream.write(byteData, 0, byteCount);
      }

    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
      return;
    }

    logger.info("File is downloaded successfully");
  }

}
