package com.rajkumar.java.utils.lync;

public class EmbeddedOnlineMeetings {
  
  private String embeddedOnlineMeetingsRel;
  private EmbeddedOnlineMeetingsLink embeddedOnlineMeetingsLink;
  
  public EmbeddedOnlineMeetingsLink getEmbeddedOnlineMeetingsLink() {
    
    return embeddedOnlineMeetingsLink;
  }
  
  public void setEmbeddedOnlineMeetingsLink(
      EmbeddedOnlineMeetingsLink embeddedOnlineMeetingsLink) {
    
    this.embeddedOnlineMeetingsLink = embeddedOnlineMeetingsLink;
  }
  
  public String getEmbeddedOnlineMeetingsRel() {
    
    return embeddedOnlineMeetingsRel;
  }
  
  public void setEmbeddedOnlineMeetingsRel(String embeddedOnlineMeetingsRel) {
    
    this.embeddedOnlineMeetingsRel = embeddedOnlineMeetingsRel;
  }
}
