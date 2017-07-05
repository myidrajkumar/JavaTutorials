package com.rajkumar.java.utils.fromweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Nice Image Generation.
 * 
 * @author Rajkumar
 *
 */
public class AsciiArt {
  
  private static Logger logger = LogManager.getLogger();
  
  private AsciiArt() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) throws IOException {
    
    int width = 200;
    int height = 30;
    
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics ordinaryGraphics = image.getGraphics();
    ordinaryGraphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    
    Graphics2D graphics = (Graphics2D) ordinaryGraphics;
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.drawString("Rajkumar", 10, 20);
    
    // save this image
    ImageIO.write(image, "png", new File("Output/art.png"));
    
    for (int y = 0; y < height; y++) {
      StringBuilder sb = new StringBuilder();
      for (int x = 0; x < width; x++) {
        
        sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
        
      }
      
      if (sb.toString().trim().isEmpty()) {
        continue;
      }
      
      logger.info(sb);
    }
    
  }
  
}
