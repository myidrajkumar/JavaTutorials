package com.rajkumar.java.utils.datastructures;

import com.rajkumar.java.utils.lib.Utils;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * My Stack.
 * 
 * @author Rajkumar
 *
 */
public class MyStackExample {
  
  private static Logger logger = LogManager.getLogger();
  
  private MyStackExample() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    MyStack<String> myStack = new MyStack<>();
    myStack.push("3");
    myStack.push("-1");
    myStack.push("2");
    myStack.push("4");
    myStack.push("5");
    
    myStack.display();
    
    myStack.pop();
    myStack.pop();
    myStack.pop();
    myStack.pop();
    myStack.pop();
    
    try {
      myStack.pop();
    } catch (RuntimeException exception) {
      logger.error(Utils.getException(exception));
    }
    
    myStack.display();
  }
  
  private static class Node<T> {
    
    private T data;
    private Node<T> link;
    
    /**
     * Initialize the data.
     * 
     * @param data data
     */
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
     * Get the link.
     * 
     * @return the link
     */
    public Node<T> getLink() {
      
      return this.link;
    }
    
    /**
     * Set the link.
     * 
     * @param link the link to set
     */
    public void setLink(Node<T> link) {
      
      this.link = link;
    }
  }
  
  private static class MyStack<E> {
    
    private Node<E> topNode;
    private int size;
    
    public MyStack() {
      this.topNode = null;
      this.size = 0;
    }
    
    public void push(E data) {
      
      Node<E> node = new Node<>(data);
      
      if (this.topNode == null) {
        this.topNode = node;
      } else {
        node.setLink(topNode);
        topNode = node;
      }
      
      size++;
    }
    
    public E pop() {
      
      if (isEmpty()) {
        throw new NoSuchElementException("Underflow Exception");
      }
      
      Node<E> node = topNode;
      topNode = node.getLink();
      size--;
      return node.getData();
    }
    
    public void display() {
      
      Node<E> node = topNode;
      
      while (node != null) {
        logger.info(node.getData());
        node = node.getLink();
      }
    }
    
    /* Function to check if stack is empty */
    
    public boolean isEmpty() {
      
      return topNode == null;
    }
    
  }
  
}
