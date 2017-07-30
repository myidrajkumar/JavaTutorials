package com.rajkumar.java.utils.serialize;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Serialized objects consumes space.
 * 
 * @author Rajkumar
 *
 */
public class VeryLargeObjectSerializationExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private VeryLargeObjectSerializationExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    serializeUsingFileStream();
    
    logger.info(Constants.LINE_SEPERATOR);
    
    serializeUsingFileZipStream();
    
  }
  
  private static void serializeUsingFileStream() {
    
    VeryLargeObject largeObject = new VeryLargeObject("File Serial");
    
    final String serialFileName = Constants.OUTPUT_DIR + "serial.out";
    final File serializedFile = new File(serialFileName);
    try (FileOutputStream fos = new FileOutputStream(serializedFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      
      oos.writeObject(largeObject);
      oos.flush();
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
    logger.info("Object is serialized and it's size = "
        + serializedFile.length() / (1024 * 1024) + " MB.");
    logger.info("Deserialization will happen now");
    try (FileInputStream fis = new FileInputStream(serializedFile);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      
      VeryLargeObject object = (VeryLargeObject) ois.readObject();
      logger.info("Name = " + object.name);
    } catch (IOException | ClassNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
    
  }
  
  private static void serializeUsingFileZipStream() {
    
    VeryLargeObject largeObject = new VeryLargeObject("Zip File Serial");
    
    final String serialFileName = Constants.OUTPUT_DIR + "serial.out";
    final File serializedFile = new File(serialFileName);
    try (FileOutputStream fos = new FileOutputStream(serializedFile);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(gos)) {
      
      oos.writeObject(largeObject);
      oos.flush();
      
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
    logger.info("Object is serialized using zip and it's size = "
        + serializedFile.length() / (1024 * 1024) + " MB.");
    
    logger.info("Deserialization will happen now");
    
    try (FileInputStream fis = new FileInputStream(serializedFile);
        GZIPInputStream gis = new GZIPInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(gis)) {
      
      VeryLargeObject object = (VeryLargeObject) ois.readObject();
      logger.info("Name = " + object.name);
    } catch (IOException | ClassNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
    
  }
  
  private static class VeryLargeObject implements Serializable {
    
    private static final long serialVersionUID = -4610394529093887779L;
    private static final int SIZE = 1 << 12;
    private int[][] bigOne = new int[SIZE][SIZE];
    private String name = "Serial";
    
    public VeryLargeObject(String name) {
      this.name = name;
      for (int i = 0; i < SIZE; i++) {
        Arrays.fill(bigOne[i], 100);
      }
    }
  }
  
}
