package com.rajkumar.java.utils.datastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Binary Search Tree using array.
 * 
 * <pre>
 * The tree will be like this.
 *           30
 *           /\
 *         15  35
 *              \
 *               57
 *               /\
 *              49 63
 *                  \
 *                   89
 *                   /\
 *                  77 98
 *                  /  /
 *                 67 91
 * </pre>
 *                 
 * @author Rajkumar
 *
 */
public class MyBinarySearchTreeExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private MyBinarySearchTreeExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
    int[] numArray = {30, 35, 57, 15, 63, 49, 89, 77, 67, 98, 91};
    for (int number : numArray) {
      searchTree.insert(number);
    }
    
    searchTree.display();
    
    logger.info("Searching value = 15 and the result ==> " + searchTree.find(15));
    
  }
  
  private static class BinarySearchTree<T extends Comparable<T>> {
    
    private Node<T> root;
    
    private BinarySearchTree() { }
    
    public boolean find(T dataToBeFound) {
      
      Node<T> nodeToBeChecked = root;
      
      do {
        T nodeData = nodeToBeChecked.getData();
        if (nodeData.equals(dataToBeFound)) {
          return true;
        }
        
        if (nodeData.compareTo(dataToBeFound) > 0) {
          nodeToBeChecked = nodeToBeChecked.getLeft();
        } else {
          nodeToBeChecked = nodeToBeChecked.getRight();
        }
        
      } while (nodeToBeChecked != null);
      
      return false;
    }
    
    public void insert(T newData) {
      
      if (root == null) {
        Node<T> node = new Node<>(newData);
        root = node;
        return;
      } 
      
      Node<T> currentNode = root;
      Node<T> parentNode = null;
      
      boolean isRightTraversal = false;
      do {
        
        T existingData = currentNode.getData();
        isRightTraversal = existingData.compareTo(newData) < 0;
        parentNode = currentNode;
        if (isRightTraversal) {
          currentNode = currentNode.getRight();
        } else {
          currentNode = currentNode.getLeft();
        }
        
      } while (currentNode != null);
      
      Node<T> node = new Node<>(newData);
      if (isRightTraversal) {
        parentNode.setRight(node);
      } else {
        parentNode.setLeft(node);
      }
      
    }
    
    public void display() {
      
      logger.info("\nDisplaying inserted Values");
      logger.info("\n------------Pre Order Traversal--------------");
      preOrder();
      logger.info("\n------------Post Order Traversal--------------");
      postOrder();
      logger.info("\n------------In Order Traversal--------------");
      inOrder();
      logger.info("\n--------------------------");
    }

    /**
     *  PreOrder Traversal: Node -> Left -> Right.
     */
    public void preOrder() {
      
      if (root != null) {
        Node<T> currentNode = root;
        preOrder(currentNode);
      }
    }
    
    /**
     * PreOrder Traversal.
     * 
     * @param currentNode node to be checked
     */
    private void preOrder(Node<T> currentNode) {
      
      logger.info(currentNode.data + " ");
      
      if (currentNode.getLeft() != null) {
        preOrder(currentNode.getLeft());
      }
      
      if (currentNode.getRight() != null) {
        preOrder(currentNode.getRight());
      }
    }
    
    /**
     *  PostOrder Traversal: Left -> Right -> Node.
     */
    public void postOrder() {
      
      if (root != null) {
        Node<T> currentNode = root;
        postOrder(currentNode);
      }
    }
    
    /**
     * PostOrder Traversal.
     * 
     * @param currentNode node to be checked
     */
    private void postOrder(Node<T> currentNode) {
      
      if (currentNode.getLeft() != null) {
        postOrder(currentNode.getLeft());
      }
      
      if (currentNode.getRight() != null) {
        preOrder(currentNode.getRight());
      }
      
      logger.info(currentNode.data + " ");
    }
    
    /**
     *  InOrder Traversal: Left -> Node -> Right.
     */
    public void inOrder() {
      
      if (root != null) {
        Node<T> currentNode = root;
        inOrder(currentNode);
      }
    }
    
    /**
     * InOrder Traversal.
     * 
     * @param currentNode node to be checked
     */
    private void inOrder(Node<T> currentNode) {
      
      if (currentNode.getLeft() != null) {
        postOrder(currentNode.getLeft());
      }
      
      logger.info(currentNode.data + " ");
      
      if (currentNode.getRight() != null) {
        preOrder(currentNode.getRight());
      }
      
    }
    
    private static class Node<T> {
      
      private T data;
      private Node<T> left;
      private Node<T> right;
      
      public Node(T data) {
        this.data = data;
      }
      
      /**
       * Get the data.
       * 
       * @return the data
       */
      public T getData() {
        
        return this.data;
      }
      
      /**
       * Get left node.
       * 
       * @return the left
       */
      public Node<T> getLeft() {
        
        return this.left;
      }
      
      /**
       * Set the left node.
       * 
       * @param left the left to set
       */
      public void setLeft(Node<T> left) {
        
        this.left = left;
      }
      
      /**
       * Get right node.
       * 
       * @return the right
       */
      public Node<T> getRight() {
        
        return this.right;
      }
      
      /**
       * Set the right node.
       * 
       * @param right the right to set
       */
      public void setRight(Node<T> right) {
        
        this.right = right;
      }
      
    }
  }
  
}
