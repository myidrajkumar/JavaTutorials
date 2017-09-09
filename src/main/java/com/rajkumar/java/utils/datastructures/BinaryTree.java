package com.rajkumar.java.utils.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * My Binary Tree. As of now, Only traversal is implemented.
 * 
 * @author Rajkumar
 * @param <E> Any data type can be accepted.
 *
 */
public class BinaryTree<E> {
  
  private TreeNode<E> root;
  
  /**
   * All paths of binary tree.
   * 
   * @return paths of binary tree.
   */
  public List<String> getAllPaths() {
    
    return getAllPaths(root);
  }
  
  /**
   * All paths of binary tree from the given node.
   * 
   * @param node Node from which we want paths
   * 
   * @return paths of binary tree.
   */
  public List<String> getAllPaths(TreeNode<E> node) {
    
    List<String> pathList = new ArrayList<>();
    return getAllPaths(node, pathList, "");
  }
  
  private List<String> getAllPaths(TreeNode<E> node, List<String> pathList,
      String path) {
    
    if (node == null) {
      return pathList;
    }
    
    String pathToBePrefixed = getPath(node, path);
    if (node.getLeft() == null && node.getRight() == null) {
      pathList.add(pathToBePrefixed);
    } else {
      getAllPaths(node.getLeft(), pathList, pathToBePrefixed);
      getAllPaths(node.getRight(), pathList, pathToBePrefixed);
    }
    
    return pathList;
  }
  
  /**
   * Root Node.
   * 
   * @return the root
   */
  public TreeNode<E> getRoot() {
    
    return root;
  }
  
  /**
   * Setting up root node.
   * 
   * @param root Root Node
   */
  public void setRoot(TreeNode<E> root) {
    
    this.root = root;
  }
  
  private String getPath(TreeNode<E> rootNode, String existingPath) {
    
    if (rootNode == null) {
      return existingPath;
    }
    
    return ("").equals(existingPath)
        ? rootNode.getData().toString()
        : (existingPath + " ==> " + rootNode.getData());
  }
  
  static class TreeNode<T> {
    
    private TreeNode<T> left = null;
    private TreeNode<T> right = null;
    private T data = null;
    
    public TreeNode(T data) {
      
      this.data = data;
    }
    
    public T getData() {
      
      return data;
    }
    
    public TreeNode<T> getLeft() {
      
      return left;
    }
    
    public void setLeft(TreeNode<T> left) {
      
      this.left = left;
    }
    
    public TreeNode<T> getRight() {
      
      return right;
    }
    
    public void setRight(TreeNode<T> right) {
      
      this.right = right;
    }
  }
}
