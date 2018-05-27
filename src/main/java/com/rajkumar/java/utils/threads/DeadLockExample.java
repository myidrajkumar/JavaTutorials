package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creating DeadLock example.
 * 
 * @author Rajkumar
 *
 */
public class DeadLockExample {
  
  private static Logger logger = LogManager.getLogger();
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    final Friend raghu = new Friend("Raghu");
    final Friend sasi = new Friend("Sasi");
    
    new Thread(() -> raghu.bow(sasi)).start();
    new Thread(() -> sasi.bow(raghu)).start();
    
  }
  
  private static class Friend {
   
    private final String name;
    
    public Friend(String name) {
      this.name = name;
    }
    
    public String getName() {
      return name;
    }
    
    public synchronized void bow(Friend bower) {
      logger.info("{} : {} has bowed to me!", this.name, bower.getName());
      bower.bowBack(this);
    }
    
    public synchronized void bowBack(Friend bower) {
      logger.info("{} : {} has bowbacked to me!", this.name, bower.getName());
    }
  }
}
