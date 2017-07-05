package com.rajkumar.java.utils.lync;

public class PresenceStatus {
  
  private Integer presenceCode;
  private String userDisplayStr;
  private String actualStr;
  
  public Integer getPresenceCode() {
    
    return presenceCode;
  }
  
  public void setPresenceCode(Integer presenceCode) {
    
    this.presenceCode = presenceCode;
  }
  
  public String getUserDisplayStr() {
    
    return userDisplayStr;
  }
  
  public void setUserDisplayStr(String userDisplayStr) {
    
    this.userDisplayStr = userDisplayStr;
  }
  
  public String getActualStr() {
    
    return actualStr;
  }
  
  public void setActualStr(String actualStr) {
    
    this.actualStr = actualStr;
  }
  
}
