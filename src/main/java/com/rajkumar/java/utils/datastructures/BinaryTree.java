package com.rajkumar.java.utils.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * My Binary Tree.   As of now, Only traversal is implemented.
 * 
 * @author Rajkumar
 *
 */
public class BinaryTree<E> {

  public TreeNode<E> root;
  
  public List<String> getAllPaths() {
    return getAllPaths(root);
  }
  
  public List<String> getAllPaths(TreeNode<E> node) {
    List<String> pathList = new ArrayList<>();
    return getAllPaths(node, pathList, "");
  }
  
  private List<String> getAllPaths(TreeNode<E> node, List<String> pathList, String path) {
    
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
  
  private String getPath(TreeNode<E> rootNode, String existingPath) {
    if (rootNode == null) {
      return existingPath;
    }
    
    return ("").equals(existingPath) 
        ? rootNode.getData().toString() 
        : (existingPath + " ==> " + rootNode.getData());
  }

  static class TreeNode<T> {
    public TreeNode<T> left = null;
    public TreeNode<T> right = null;
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
    
    public TreeNode<T> getRight() {
      return right;
    }
  }
}
