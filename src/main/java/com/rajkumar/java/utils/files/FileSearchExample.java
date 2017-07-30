package com.rajkumar.java.utils.files;

import com.rajkumar.java.utils.lib.Utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * File Search example using nio.
 * 
 * @author Rajkumar
 *
 */
public class FileSearchExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private FileSearchExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    Path path = Paths.get(".");
    
    try {
      Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
        //What this regular expression is??
        Pattern javaFilePattern = Pattern.compile(".*.java"); 
       
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
          
          Matcher matcher = javaFilePattern.matcher(file.toFile().getName());
          if (matcher.matches()) {
            logger.info("Visiting File =>" + file);
          }
          
          return super.visitFile(file, attrs);
        }
      });
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
    
  }
  
}
