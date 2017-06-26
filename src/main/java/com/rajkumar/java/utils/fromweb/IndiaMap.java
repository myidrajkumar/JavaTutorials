package com.rajkumar.java.utils.fromweb;

/**
 * India Map will be drawn.
 * 
 * @author Rajkumar
 *
 */
public class IndiaMap {

  private IndiaMap() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    String obfs = "TFy!QJu ROo TNn(ROo)SLq SLq ULo+UHs UJq TNn*RPn/QPb"
        + "EWS_JSWQAIJO^NBELPeHBFHT}TnALVlBLOFAkHFOuFETpHCStHAUFAgcEAel"
        + "clcn^r^r\\tZvYxXyT|S~Pn SPm SOn TNn ULo0ULo#ULo-WHq!WFs XDt!";
    
    int left;
    int top = 0;
    int bottom = 10;
    left = obfs.charAt(top);
    while (left != 0) {
      if (top < 170) {
        left = obfs.charAt(top);
        top++;
        while (left > 64) {
          left--;
          if (++bottom == 'Z') {
            bottom /= 9;
            System.out.print((char) (bottom));  //NOSONAR
          } else {
            System.out.print((char) (33 ^ (top & 0x01)));  //NOSONAR
          }
        }
      } else {
        break;
      }
    }
    System.out.println("\n");  //NOSONAR

  }

}
