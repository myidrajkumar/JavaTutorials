package com.rajkumar.java.utils.datastructures;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Testing MyArrayList.
 * 
 * @author Rajkumar
 *
 */
public class MyArrayListTest {
  
  /**
   * Test when no elements present.
   */
  @Test
  public void testMyArrayList_WhenNoArgConstructor_InitialStage() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>();
    
    assertEquals(0, myArrayListInteger.size(),
        "Initial size is not maintained");
  }
  
  /**
   * Test by giving initial size.
   */
  @Test
  public void testMyArrayList_WhenSizeArgConstructor_InitialStage() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(5);
    
    assertEquals(0, myArrayListInteger.size(),
        "Initial size is not maintained");
  }
  
  /**
   * Test by adding some elements.
   */
  @Test
  public void testMyArrayList_WhenCollectionArgConstructor_InitialStage() {
    
    List<Integer> myCollection = new ArrayList<>();
    myCollection.add(2);
    myCollection.add(3);
    
    List<Integer> myArrayListInteger = new MyArrayList<>(myCollection);
    
    assertEquals(2, myArrayListInteger.size(),
        "Initial size is not maintained");
  }
  
  /**
   * Test added elements.
   */
  @Test
  public void testAdd() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    assertEquals(0, myArrayListInteger.size(),
        "Initial size is not maintained");
    
    // Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    assertEquals(2, myArrayListInteger.size(), "size is not maintained");
    
    // With the above, list becomes full. If i try to add now, it should not
    // throw exception
    myArrayListInteger.add(2, 6);
    assertEquals(3, myArrayListInteger.size(), "size is not maintained");
    
    assertThrows(IndexOutOfBoundsException.class,
        () -> myArrayListInteger.add(5, 8));
    
  }
  
  /**
   * Test retrieving elements.
   */
  @Test
  public void testGet() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    // Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    assertThat("Value is not retrieved", myArrayListInteger.get(0), is(2));
    
    assertThrows(IndexOutOfBoundsException.class,
        () -> myArrayListInteger.get(-1));
  }
  
  /**
   * Test setting up elements.
   */
  @Test
  public void testSetIntE() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    // Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    
    assertThat("Old value is not retrieved", myArrayListInteger.set(1, 6),
        is(4));
    assertThat("Setted value is not retrieved", myArrayListInteger.get(1),
        is(6));
    
    assertThrows(IndexOutOfBoundsException.class,
        () -> myArrayListInteger.set(-1, 4));
    
  }
  
  /**
   * Test removing elements.
   */
  @Test
  public void testRemoveInt() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    // Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    
    assertThat("Old value is not retrieved", myArrayListInteger.remove(1),
        is(4));
    assertThat("Size is not set when removed", myArrayListInteger.size(),
        is(1));
    
    assertThrows(IndexOutOfBoundsException.class,
        () -> myArrayListInteger.remove(2));
    
  }
  
}
