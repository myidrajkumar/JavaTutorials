package com.rajkumar.java.utils.simpleconcepts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;
import com.rajkumar.java.utils.lib.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Searching files within JAR file.
 * 
 * <p> This class is badly designed. </p>
 * 
 * @author Rajkumar
 *
 */
public class SearchFilesInJarExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private SearchFilesInJarExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    String javaJarFile = System.getProperty("java.home") 
        + File.separator + "lib"
        + File.separator + "rt.jar";
    
    List<Content> classList = new ArrayList<>();
    List<Content> methodList = new ArrayList<>();
    List<Content> attributeList = new ArrayList<>();
    
    logger.info("Processing...");
    getList(new File(javaJarFile), classList, methodList, attributeList);
    
    sort(classList);
    try {
      print(classList, new PrintWriter(Constants.OUTPUT_DIR + "ClassList.txt"));
    } catch (FileNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info("Class list complete.");
    
    sort(methodList);
    try {
      print(methodList,
          new PrintWriter(Constants.OUTPUT_DIR + "MethodList.txt"));
    } catch (FileNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info("Method list complete.");
    
    sort(attributeList);
    try {
      print(attributeList,
          new PrintWriter(Constants.OUTPUT_DIR + "AttributeList.txt"));
    } catch (FileNotFoundException exception) {
      logger.error(Utils.getException(exception));
    }
    logger.info("Attribute list complete.");
    
  }
  
  /**
   * To get classes, methods and attributes.
   * 
   * @param file file to be checked
   * @param classList classes to be filled
   * @param methodList methods to be filled
   * @param attributeList attributes to be filled
   */
  private static void getList(File file, List<Content> classList,
      List<Content> methodList, List<Content> attributeList) {
    
    try (JarFile jarFile = new JarFile(file) ) {
      Enumeration<JarEntry> jarEntries = jarFile.entries();
      
      while (jarEntries.hasMoreElements()) {
        JarEntry jarEntryFile = jarEntries.nextElement();
        getClassInformation(jarEntryFile, classList, methodList, attributeList);
      }
    } catch (IOException exception) {
      logger.error(Utils.getException(exception));
    }
  }
  
  /**
   * Class information will be retrieved.
   * 
   * @param jarEntryFile Class file to be checked
   * @param classList classes to be filled
   * @param methodList methods to be filled
   * @param attributeList attributes to be filled
   */
  private static void getClassInformation(JarEntry jarEntryFile,
      List<Content> classList, List<Content> methodList,
      List<Content> attributeList) {
    
    String className = jarEntryFile.getName();
    
    if (className.endsWith(".class")) {
      className = className.replaceAll("\\.class", "");
      className = className.replaceAll("/", ".");
      
      try {
        Class<?> clazz = Class.forName(className, false,
            ClassLoader.getSystemClassLoader());
        
        if (clazz.getSimpleName().length() > 0) {
          classList.add(new Content(clazz.getSimpleName(), ""));
          getMethodList(clazz, methodList);
          getAttributeList(clazz, attributeList);
        }
      } catch (ClassNotFoundException exception) {
        logger.error(Utils.getException(exception));
      }
    }
  }
  
  private static void sort(List<Content> nameList) {
    
    Collections.sort(nameList,
        (c1, c2) -> c2.contentName.length() - c1.contentName.length());
  }
  
  private static void print(List<Content> list, PrintWriter file) {
    
    for (Content str : list) {
      file.println(str.contentName.length() + "\t" + str.contentName + "\t"
          + str.className);
    }
    file.close();
  }
  
  private static void getAttributeList(Class<?> clazz,
      List<Content> attributeList) {
    
    Field[] fields = clazz.getFields();
    for (Field field : fields) {
      attributeList.add(new Content(field.getName(), clazz.getSimpleName()));
    }
  }
  
  private static void getMethodList(Class<?> clazz, List<Content> methodList) {
    
    Method[] methods = clazz.getMethods();
    for (Method method : methods) {
      methodList.add(new Content(method.getName(), clazz.getSimpleName()));
    }
  }
  
  private static class Content {
    
    private String contentName;
    private String className;
    
    public Content(String content, String className) {
      this.contentName = content;
      this.className = className;
    }
    
  }
  
}
