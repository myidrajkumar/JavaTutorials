package com.rajkumar.java.utils.datastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A balanced tree is a tree in which difference between heights of sub-trees of
 * any node in the tree is not greater than one.
 * 
 * @author Rajkumar
 *
 */
public class ValidatingBinaryTreeBlalance {
  
  private static Logger logger = LogManager.getLogger();
  
  private ValidatingBinaryTreeBlalance() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    /**
     * Tree is formed like this.
     *          5
     *         / \
     *       10   15
     *      / \   / \
     *     20 25 30  35   
     */
    Node root = new Node(5);
    root.left = new Node(10);
    root.right = new Node(15);
    root.left.left = new Node(20);
    root.left.right = new Node(25);
    root.right.left = new Node(30);
    root.right.right = new Node(35);
    
    logger.info(" Is Tree Balanced : " + checkBalance(root));
    
    /**
     * Tree is formed like this.
     *          5
     *         / \
     *       10   15
     *      / \   / \
     *     20 25 30  35
     *                \
     *                 40
     *                  \
     *                  45   
     */
    root.right.right.right = new Node(40);
    root.right.right.right.right = new Node(45);
    logger.info(" Is Tree Balanced : " + checkBalance(root));
    
  }
  
  /**
   * Checking Balance.
   * 
   * @param node from node
   * 
   * @return whether balanced or not
   */
  public static boolean checkBalance(Node node) {
    
    int result = isBalanced(node);
    return result > 0;
  }
  
  /**
   * Checking Balance.
   * 
   * @param node from node
   * 
   * @return balance height length
   */
  public static int isBalanced(Node node) {
    
    if (node == null) {
      return 0;
    }
    int leftH = isBalanced(node.left);
    if (leftH == -1) {
      return -1;
    }
    int rightH = isBalanced(node.right);
    if (rightH == -1) {
      return -1;
    }
    
    int diff = leftH - rightH;
    if (Math.abs(diff) > 1) {
      return -1;
    }
    
    logger.info("Node " + node.getData());
    return 1 + Math.max(leftH, rightH);
  }
  
  private static class Node {
    
    private int data;
    private Node left;
    private Node right;
    
    private Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
    
    public int getData() {
      return data;
    }
  }
  
}
