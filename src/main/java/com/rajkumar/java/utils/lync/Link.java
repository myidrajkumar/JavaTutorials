package com.rajkumar.java.utils.lync;

public class Link implements TargetDomain {
  
  private String linkSelf;
  private String linkPolicy;
  private String linkBatch;
  private String linkEvents;
  
  public String getLinkSelf() {
    
    return linkSelf;
  }
  
  public void setLinkSelf(String linkSelf) {
    
    this.linkSelf = linkSelf;
  }
  
  public String getLinkPolicy() {
    
    return linkPolicy;
  }
  
  public void setLinkPolicy(String linkPolicy) {
    
    this.linkPolicy = linkPolicy;
  }
  
  public String getLinkBatch() {
    
    return linkBatch;
  }
  
  public void setLinkBatch(String linkBatch) {
    
    this.linkBatch = linkBatch;
  }
  
  public String getLinkEvents() {
    
    return URL_STRING + linkEvents;
  }
  
  public void setLinkEvents(String linkEvents) {
    
    this.linkEvents = linkEvents;
  }
  
}
