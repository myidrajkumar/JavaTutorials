package com.rajkumar.java.utils.datastructures;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/**
 * My Own ArrayList.
 * 
 * @author Seenappa
 *
 */
public class MyArrayList<E> extends AbstractList<E> {

  public static final int DEFAULT_INITIAL_CAPACITY = 10;
  private int size;
  private Object[] elements;

  public MyArrayList() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public MyArrayList(int initialCapacity) {
    elements = new Object[initialCapacity];
  }

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
  
  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    checkIndexRange(index);
    return (E) elements[index];
  }

  @Override
  public int size() {
    return size;
  }

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

  @Override
  public E set(int index, E element) {
    checkIndexRange(index);
    
    @SuppressWarnings("unchecked")
    E old = (E) elements[index];
    
    elements[index] = element;
    return old;
  }

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

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }
  
  @Override
  public int hashCode() {
    return super.hashCode();
  }
  
  
  
}
