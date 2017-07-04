package com.rajkumar.java.utils.datastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Printing only leaves.
 * 
 * @author Rajkumar
 *
 */
public class PrintNoSiblingNodes {
  
  private static Logger logger = LogManager.getLogger();
  
  private PrintNoSiblingNodes() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    /**
     * Tree is formed like this.
     *          1
     *         / \
     *        2    3
     *      / \     \
     *     4   5     8
     *    /   /     / 
     *   6    7    9    
     *                  
     *                     
     */
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.left.right.left = new Node(7);
    root.left.left.left = new Node(6);
    root.right.right = new Node(8);
    root.right.right.left = new Node(9);
    
    printOnlyLeaves(root);
  }
  
  /**
   * Printing nodes.
   * 
   * @param node node to be checked to print
   */
  private static void printOnlyLeaves(Node node) {
    
    if (node != null) {
      printOnlyLeaves(node.left);
      
      if ((node.left == null) && (node.right != null)) {
        logger.info(" " + node.right.data);
      }
      if ((node.left != null) && (node.right == null)) {
        logger.info(" " + node.left.data);
      }
      
      printOnlyLeaves(node.right);
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
