package com.rajkumar.java.utils.datastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * My Single Linked List.
 * 
 * @author Rajkumar
 *
 */
public class MySingleLinkedList {
  
  private static Logger logger = LogManager.getLogger();
  
  private MySingleLinkedList() {}
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    MySingleList<Integer> myList = new MySingleList<>();
    myList.addAtEnd(5);
    myList.addAtEnd(1);
    myList.addAtStart(75);
    myList.addAtEnd(4);
    myList.addAtStart(21);
    myList.addAtEnd(2);
    myList.addAtEnd(-1);
    myList.addAtStart(42);
    
    myList.display();
    
    myList.insert(32, 6);
    logger.info(Constants.LINE_SEPERATOR);
    myList.display();
    logger.info(Constants.LINE_SEPERATOR);
    myList.removeAtEnd();
    myList.removeAtStart();
    myList.display();
    
    logger.info(Constants.LINE_SEPERATOR);
    myList.removeAtIndex(6);
    myList.display();
    
    logger.info(Constants.LINE_SEPERATOR);
    logger.info("Adding duplicate entries");
    myList.addAtEnd(2);
    myList.addAtEnd(4);
    myList.addAtEnd(75);
    myList.display();
    
    logger.info(Constants.LINE_SEPERATOR);
    logger.info("After removing duplicate entries");
    myList.removeDuplicates();
    myList.display();
    
    logger.info(Constants.LINE_SEPERATOR);
    logger.info("Reversing the linked list");
    myList.reverseLinkedList();
    myList.display();
    
  }
  
  private static class Node<T> {
    
    private T data;
    private Node<T> next;
    
    public Node(T data) {
      this.data = data;
    }
    
    /**
     * Get the data.
     * 
     * @return the data data
     */
    public T getData() {
      
      return this.data;
    }
    
    /**
     * Get next pointer.
     * 
     * @return the next next pointer
     */
    public Node<T> getNext() {
      
      return this.next;
    }
    
    /**
     * Set next pointer.
     * 
     * @param next the next to set
     */
    public void setNext(Node<T> next) {
      
      this.next = next;
    }
  }
  
  private static class MySingleList<T> {
    
    private int size;
    private Node<T> head;
    
    public void addAtEnd(T data) {
      
      Node<T> node = new Node<>(data);
      if (size == 0) {
        head = node;
      } else {
        
        Node<T> currentElement = head;
        
        while (currentElement.getNext() != null) {
          currentElement = currentElement.getNext();
        }
        
        currentElement.setNext(node);
      }
      
      size++;
    }
    
    public void addAtStart(T data) {
      
      Node<T> node = new Node<>(data);
      if (size == 0) {
        head = node;
      } else {
        node.setNext(head);
        head = node;
      }
      
      size++;
    }
    
    public void insert(T data, int index) {
      
      if (index == 0) {
        addAtStart(data);
      } else if (index == size) {
        addAtEnd(data);
      } else {
        Node<T> node = new Node<>(data);
        
        Node<T> currentElement = head;
        for (int i = 1; i < index; i++) {
          currentElement = currentElement.getNext();
        }
        
        node.setNext(currentElement.getNext());
        currentElement.setNext(node);
        size++;
      }
      
    }
    
    public T removeAtStart() {
      
      Node<T> currentElement = head;
      head = currentElement.getNext();
      
      size--;
      return currentElement.getData();
    }
    
    public T removeAtEnd() {
      
      if (size == 0) {
        logger.info("No element to be removed");
        return null;
      }
      
      if (size == 1) {
        Node<T> tempData = head;
        head = null;
        return tempData.getData();
      }
      
      Node<T> currentElement = head;
      
      while (currentElement.getNext().getNext() != null) {
        currentElement = currentElement.getNext();
      }
      
      currentElement.setNext(null);
      size--;
      return currentElement.getData();
    }
    
    public T removeAtIndex(int index) {
      
      T deleteData = null;
      if (index == 0) {
        deleteData = head.getData();
        head = head.getNext();
        size--;
      } else {
        Node<T> currentElement = head;
        
        for (int i = 1; i < index; i++) {
          currentElement = currentElement.getNext();
        }
        deleteData = currentElement.getNext().getData();
        currentElement.setNext(currentElement.getNext().getNext());
      }
      
      size--;
      return deleteData;
    }
    
    public void display() {
      
      Node<T> currentElement = head;
      
      while (currentElement != null) {
        logger.info(currentElement.getData() + " ");
        currentElement = currentElement.getNext();
      }
      
    }
    
    /**
     * Removing duplicates - Maintain hashset and check whether it contains data.
     * 
     * <p>
     * Time Complexity : O(n) Space Complexity : O(n)
     * </p>
     */
    public void removeDuplicates() {
      
      Node<T> currentElement = head;
      Node<T> prevElement = currentElement;
      Set<T> valueSet = new HashSet<>();
      
      while (currentElement != null) {
        if (!valueSet.add(currentElement.getData())) {
          prevElement.setNext(currentElement.getNext());
        } else {
          prevElement = currentElement;
        }
        
        currentElement = currentElement.getNext();
      }
    }
    
    /**
     * Reverse the linked list.
     */
    public void reverseLinkedList() {
      
      Node<T> currentElement = head;
      Node<T> nextElement = null;
      Node<T> prevElement = null;
      
      while (currentElement != null) {
        nextElement = currentElement.getNext();
        currentElement.setNext(prevElement);
        prevElement = currentElement;
        currentElement = nextElement;
      }
      
      head = prevElement;
    }
  }
  
}
