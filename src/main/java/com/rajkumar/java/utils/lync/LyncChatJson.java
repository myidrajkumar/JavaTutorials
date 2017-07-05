package com.rajkumar.java.utils.lync;

class LyncChatJson {
  
  private String culture;
  private String userAgent;
  
  private Link link;
  private Embedded embedded;
  private String rel;
  
  public String getRel() {
    
    return rel;
  }
  
  public void setRel(String rel) {
    
    this.rel = rel;
  }
  
  public String getCulture() {
    
    return culture;
  }
  
  public void setCulture(String culture) {
    
    this.culture = culture;
  }
  
  public String getUserAgent() {
    
    return userAgent;
  }
  
  public void setUserAgent(String userAgent) {
    
    this.userAgent = userAgent;
  }
  
  public Link getLink() {
    
    return link;
  }
  
  public void setLink(Link link) {
    
    this.link = link;
  }
  
  public Embedded getEmbedded() {
    
    return embedded;
  }
  
  public void setEmbedded(Embedded embedded) {
    
    this.embedded = embedded;
  }
  
}
