package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Getting Local host name.
 * 
 * @author Rajkumar
 *
 */
public class GetIpAddressAndHostName {

  private GetIpAddressAndHostName() { }

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    Logger logger = LogManager.getLogger();
    
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException exception) {
      Utils.getException(exception);
      return;
    }

    logger.info("IP Address: " + inetAddress.getHostAddress());
    logger.info("Hostname: " + inetAddress.getHostName());
  }

}
