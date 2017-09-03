package com.rajkumar.java.utils.datastructures;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/**
 * My Own ArrayList.
 * 
 * @author Rajkumar
 * @param <E> The type will be added to array list
 *
 */
public class MyArrayList<E> extends AbstractList<E> {
  
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  private int size;
  private Object[] elements;
  
  /**
   * Initialize ArrayList with default initial capacity.
   */
  public MyArrayList() {
    
    this(DEFAULT_INITIAL_CAPACITY);
  }
  
  /**
   * Initialize ArrayList with provided initial capacity.
   * 
   * @param initialCapacity Capacity of array list
   */
  public MyArrayList(int initialCapacity) {
    
    elements = new Object[initialCapacity];
  }
  
  /**
   * ArrayList can be initialized with other collection also.
   * 
   * @param collection Collection which will populate array list
   */
  public MyArrayList(Collection<? extends E> collection) {
    
    elements = collection.toArray();
    size = collection.size();
  }
  
  private void checkIndexRange(int index) {
    
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }
  
  private void ensureCapacity() {
    
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, (elements.length + 1) * 3 / 2);
    }
  }
  
  /**
   * Retrieving item from array list.
   */
  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    
    checkIndexRange(index);
    return (E) elements[index];
  }
  
  /**
   * Size of array list.
   */
  @Override
  public int size() {
    
    return size;
  }
  
  /**
   * Adding an item to array list.
   */
  @Override
  public void add(int index, E element) {
    
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    
    ensureCapacity();
    if (index != size) {
      System.arraycopy(elements, index, elements, index + 1, size - index);
    }
    elements[index] = element;
    size++;
    modCount++;
  }
  
  /**
   * Setting an item to array list.
   */
  @Override
  public E set(int index, E element) {
    
    checkIndexRange(index);
    
    @SuppressWarnings("unchecked")
    E old = (E) elements[index];
    
    elements[index] = element;
    return old;
  }
  
  /**
   * Removing an item from array list.
   */
  @Override
  public E remove(int index) {
    
    checkIndexRange(index);
    
    @SuppressWarnings("unchecked")
    final E old = (E) elements[index];
    
    if (index != size - 1) {
      System.arraycopy(elements, index + 1, elements, index, size - index - 1);
    }
    
    elements[--size] = null;
    modCount--;
    return old;
  }
  
}
