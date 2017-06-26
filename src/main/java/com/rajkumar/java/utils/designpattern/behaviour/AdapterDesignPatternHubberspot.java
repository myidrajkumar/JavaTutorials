package com.rajkumar.java.utils.designpattern.behaviour;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *  Adapter Pattern Example.
 *  
 * @author Rajkumar
 *
 */
public class AdapterDesignPatternHubberspot {
  
  private static Logger logger = LogManager.getLogger();

  private AdapterDesignPatternHubberspot() { }
  
  private static interface Chargeable {
    
    void setMobileName(String mobileName);
    
    void doCharge();
  }
  
  private static class AppleCharger implements Chargeable {

    String mobileName;
    
    public AppleCharger() {
      setMobileName("IPhone6");
    }
    
    @Override
    public void setMobileName(String mobileName) {
      this.mobileName = mobileName;
    }

    @Override
    public void doCharge() {
      logger.info("Charging " + this.mobileName);
      
    }
    
  }
  
  private static class ChargeUtils {
    
    private ChargeUtils() { }
    
    static void chargeMobile(Chargeable charagable) {
      charagable.doCharge();
    }
  }
  
  private static interface Charger {
    
    void setMobileName(String mobileName);
    
    void supplyCharge();
  }
  
  private static class SamsungCharger implements Charger {

    String mobileName;
    
    public SamsungCharger() {
      setMobileName("Galaxy Note 4");
    }
    
    @Override
    public void setMobileName(String mobileName) {
      this.mobileName = mobileName;
      
    }

    @Override
    public void supplyCharge() {
      logger.info("Charging " + this.mobileName);
    }
    
  }
  
  private static class ChargingAdapter implements Chargeable {

    Charger samsungCharger;
    
    public void setSamsungCharger(Charger samsungCharger) {
      this.samsungCharger = samsungCharger;
    }
    
    @Override
    public void setMobileName(String mobileName) {
      //This method should not do anything in adapter
    }

    @Override
    public void doCharge() {
      samsungCharger.supplyCharge();
      
    }
    
  }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Chargeable appleCharger = new AppleCharger();
    ChargeUtils.chargeMobile(appleCharger);
    
    Charger samsungCharger = new SamsungCharger();
    
    ChargingAdapter adpater = new ChargingAdapter();
    adpater.setSamsungCharger(samsungCharger);
    
    ChargeUtils.chargeMobile(adpater);

  }

}
