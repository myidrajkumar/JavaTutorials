package com.rajkumar.java.utils.fromweb;

import com.rajkumar.java.utils.lib.Utils;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sending Gmail Example.
 * 
 * @author Rajkumar
 *
 */
public class SendEmail {
  
  private static Logger logger = LogManager.getLogger();
  
  private SendEmail() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Recipient's email ID needs to be mentioned.
    String to = null;
    
    // Sender's email ID needs to be mentioned
    String from = null;
    
    // Sender's email password needs to be mentioned
    String password = null;
    
    try (Scanner scanner = new Scanner(System.in)) {
      logger.info("Enter your mailID");
      from = scanner.nextLine();
      
      logger.info("Enter your password");
      password = scanner.nextLine();
      
      logger.info("Enter recipient mailID");
      to = scanner.nextLine();
    }
    
    // Get system properties
    Properties props = new Properties();
    props.put("mail.smtps.starttls.enable", "true");
    props.put("mail.smtps.auth", "true");
    props.put("mail.smtps.host", "smtp.gmail.com");
    props.put("mail.smtps.port", "465");
    
    // Get the default Session object.
    
    Session session = Session.getInstance(props, null);
    session.setDebug(true);
    
    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);
      
      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));
      
      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      
      // Set Subject: header field
      message.setSubject("This is the Subject Line!");
      
      // Now set the actual message
      message.setText("This is actual message");
      
      Transport transport = session.getTransport("smtps");
      transport.connect("smtp.gmail.com", from, password);
      transport.sendMessage(message, message.getAllRecipients());
      logger.info("Sent message successfully....");
    } catch (MessagingException mex) {
      logger.error(Utils.getException(mex));
    }
  }
}
