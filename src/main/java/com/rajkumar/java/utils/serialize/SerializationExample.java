package com.rajkumar.java.utils.serialize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serialization Example.
 * 
 * @author Rajkumar
 *
 */
public class SerializationExample {

  private static final String SERIALIZED_FILE = Constants.OUTPUT_DIR + "serial.out";
  private static Logger logger = LogManager.getLogger();
  
  private SerializationExample() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    // 1. Simple Serialization
    serializeSimply();
    logger.info(Constants.LINE_SEPERATOR);
    
    // 2. Customize Serialization
    customizeSerialization();

  }

  private static void serializeSimply() {

    Lion leo = new Lion();

    // serialize
    try (
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(leo);
    } catch (IOException exception) {
      Utils.getException(exception);
    }

    logger.info("Serialization done.");

    // deserialize
    try (
        FileInputStream fis = new FileInputStream(SERIALIZED_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);) {
      Lion deserializedObj = (Lion) ois.readObject();
      logger.info("DeSerialization done. Lion: " + deserializedObj.getSound());
    } catch (IOException | ClassNotFoundException exception) {
      Utils.getException(exception);
    }

  }

  private static void customizeSerialization() {

    CustomizedLion leo = new CustomizedLion();
    // serialize
    try (
        FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(leo);
    } catch (IOException exception) {
      Utils.getException(exception);
    }

    logger.info("Serialization done.");

    // deserialize
    try (
        FileInputStream fis = new FileInputStream(SERIALIZED_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);) {
      CustomizedLion deserializedObj = (CustomizedLion) ois.readObject();
      logger.info("DeSerialization done. CustomizedLion: " + deserializedObj.getSound());
    } catch (IOException | ClassNotFoundException exception) {
      Utils.getException(exception);
    }
  }

  private static class Lion implements Serializable {

    private static final long serialVersionUID = -5306749800404876530L;

    private String sound;

    public Lion() {
      logger.info("Lion's constructor invoked.");
      setSound("roar");
    }

    public String getSound() {
      return sound;
    }

    public void setSound(String sound) {
      this.sound = sound;
    }

  }

  private static class CustomizedLion implements Serializable {

    private static final long serialVersionUID = -5306749800404876530L;

    private String sound;

    public CustomizedLion() {
      logger.info("CustomizedLion's constructor invoked.");
      setSound("roar");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
      setSound("meow");
      out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException {
      in.defaultReadObject();
    }

    public String getSound() {
      return sound;
    }

    public void setSound(String sound) {
      this.sound = sound;
    }

  }

}
