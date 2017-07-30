package com.rajkumar.java.utils.datastructures;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Testing MyArrayList.
 * 
 * @author Rajkumar
 *
 */
public class MyArrayListTest {


  @Test
  public void testMyArrayList_WhenNoArgConstructor_InitialStage() {
    List<Integer> myArrayListInteger = new MyArrayList<>();
    
    assertEquals("Initial size is not maintained", 0, myArrayListInteger.size());
  }
  
  @Test
  public void testMyArrayList_WhenSizeArgConstructor_InitialStage() {
    List<Integer> myArrayListInteger = new MyArrayList<>(5);
    
    assertEquals("Initial size is not maintained", 0, myArrayListInteger.size());
  }
  
  @Test
  public void testMyArrayList_WhenCollectionArgConstructor_InitialStage() {
    
    List<Integer> myCollection = new ArrayList<>();
    myCollection.add(2);
    myCollection.add(3);
    
    List<Integer> myArrayListInteger = new MyArrayList<>(myCollection);
    
    assertEquals("Initial size is not maintained", 2, myArrayListInteger.size());
  }

  @Test
  public void testAdd() {
    
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    assertEquals("Initial size is not maintained", 0, myArrayListInteger.size());
    
    //Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    assertEquals("size is not maintained", 2, myArrayListInteger.size());
    
    //With the above, list becomes full. If i try to add now, it should not throw exception
    myArrayListInteger.add(2, 6);
    assertEquals("size is not maintained", 3, myArrayListInteger.size());
    
    assertThrows(IndexOutOfBoundsException.class, () -> myArrayListInteger.add(5, 8));
    
  }
  
  @Test
  public void testGet() {
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    //Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    assertThat("Value is not retrieved", myArrayListInteger.get(0), is(2));
    
    assertThrows(IndexOutOfBoundsException.class, () -> myArrayListInteger.get(-1));
  }
  
  
  @Test
  public void testSetIntE() {
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    //Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    
    assertThat("Old value is not retrieved", myArrayListInteger.set(1, 6), is(4));
    assertThat("Setted value is not retrieved", myArrayListInteger.get(1), is(6));
    
    assertThrows(IndexOutOfBoundsException.class, () -> myArrayListInteger.set(-1, 4));
 
  }

  @Test
  public void testRemoveInt() {
    List<Integer> myArrayListInteger = new MyArrayList<>(2);
    
    //Adding elements sequentially
    myArrayListInteger.add(0, 2);
    myArrayListInteger.add(1, 4);
    
    assertThat("Old value is not retrieved", myArrayListInteger.remove(1), is(4));
    assertThat("Size is not set when removed", myArrayListInteger.size(), is(1));
    
    assertThrows(IndexOutOfBoundsException.class, () -> myArrayListInteger.remove(2));
 
  }

}
