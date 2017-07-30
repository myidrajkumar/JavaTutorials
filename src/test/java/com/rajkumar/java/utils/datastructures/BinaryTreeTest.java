package com.rajkumar.java.utils.datastructures;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.rajkumar.java.utils.datastructures.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinaryTreeTest {

  private static BinaryTree<Integer> tree = null;
  
  /**
   * Initialize the binary tree.
   */
  @BeforeClass
  public static void initializeBinaryTree() {
    
    tree = new BinaryTree<>();
    tree.root = new TreeNode<>(3);
    tree.root.left = new TreeNode<>(4);
    tree.root.right = new TreeNode<>(5);
    tree.root.left.left = new TreeNode<>(6);
    tree.root.left.right = new TreeNode<>(9);
    tree.root.right.left = new TreeNode<>(2);
  }
  
  @Test
  public final void testGetAllPaths() {
    
    List<String> pathList = tree.getAllPaths();
    
    assertNotNull(pathList);
    assertThat("Path count is not matching", pathList.size(), CoreMatchers.is(3));
    
    assertThat("First Path is not matching", pathList, CoreMatchers.hasItems("3 ==> 4 ==> 6"));
    assertThat("Second Path is not matching", pathList, CoreMatchers.hasItems("3 ==> 4 ==> 9"));
    assertThat("Third Path is not matching", pathList, CoreMatchers.hasItems("3 ==> 5 ==> 2"));
    
    List<String> expectedList = new ArrayList<>();
    expectedList.add("3 ==> 4 ==> 6");
    expectedList.add("3 ==> 4 ==> 9");
    expectedList.add("3 ==> 5 ==> 2");
    
    assertThat("List is not matching", pathList, CoreMatchers.is(expectedList));
  }

  @Test
  public final void testGetAllPaths_FromSpecificNode() {
   
    List<String> pathList = tree.getAllPaths(tree.root.left);
    
    assertNotNull(pathList);
    assertThat("Path count is not matching", pathList.size(), CoreMatchers.is(2));
    
    assertThat("First Path is not matching", pathList, CoreMatchers.hasItems("4 ==> 6"));
    assertThat("Second Path is not matching", pathList, CoreMatchers.hasItems("4 ==> 9"));
    
    List<String> expectedList = new ArrayList<>();
    expectedList.add("4 ==> 6");
    expectedList.add("4 ==> 9");
    
    assertThat("List is not matching", pathList, CoreMatchers.is(expectedList));
  }

}
