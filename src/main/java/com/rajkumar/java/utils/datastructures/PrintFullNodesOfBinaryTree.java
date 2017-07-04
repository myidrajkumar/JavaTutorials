package com.rajkumar.java.utils.datastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Full Nodes: Nodes Which has both the children, left and right.
 * 
 * @author Rajkumar
 *
 */
public class PrintFullNodesOfBinaryTree {
  
  private static Logger logger = LogManager.getLogger();
  
  private PrintFullNodesOfBinaryTree() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.left.left.right = new Node(8);
    
    findFullNodes(root);
    
  }
  
  /**
   * Get the full node.
   * 
   * @param node node to be checked
   */
  private static void findFullNodes(Node node) {
    
    if (node != null) {
      findFullNodes(node.left);
      
      if (node.left != null && node.right != null) {
        logger.info(node.data + " ");
      }
      
      findFullNodes(node.right);
    }
    
  }
  
  private static class Node {
    
    private int data;
    private Node left;
    private Node right;
    
    public Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }
  
}
