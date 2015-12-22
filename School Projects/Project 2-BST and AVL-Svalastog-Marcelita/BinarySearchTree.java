//Bendik Svalastog and Riadiani Marcelita
//Project 2 - BST and AVL tree
//CS 146-Anna Shaverdian

import static java.lang.Integer.compare;
import java.util.Random;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	BinarySearchTree
*  File:	BinarySearchTree.java
* <pre>
*  Description:	This application creates a binary search tree.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	9/18/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 3, 2015, 07:00 AM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class BinarySearchTree<Integer> {
    private BinaryNode<Integer> root;
    
    /**
     * Method: insert
     * Insert an integer into the binary search tree by calling the
     * recursiveInsert method.
     * @param x : the integer to be inserted in the binary search tree.
     * @return root : the node of the binary search tree after the value is inserted.
     */
    public BinaryNode<Integer> insert(int x){
        root = recursiveInsert(x, root);
        return root;
    }

    /**
     * Method: recursiveInsert
     * Inserting an integer into the binary tree recursively. If the tree is empty,
     * put the integer as the root. If it is smaller than the root, puts it in the
     * left subtree. If it is larger than the root, puts it in the right subtree.
	 * Does not allow duplicates; do nothing if it is a duplicate of an integer
	 * already in the tree.
     * @param x : the integer to be inserted in the binary search tree.
     * @param root : the node of the binary search tree to which the integer is inserted.
     * @return t : the node of the binary search tree to which the integer is inserted.
     */
    public BinaryNode<Integer> recursiveInsert(int x, BinaryNode<Integer> root){
        
        if(root == null){
            root = new BinaryNode<>(x);
        }
        else if( compareTo(x, root) < 0 ){
            if(!hasLeft())
                root.left = new BinaryNode<>(x);
            else   
                root.left = recursiveInsert(x, root.left);
        }
        else if( compareTo(x, root) > 0 ){
            if(!hasRight())
                root.right = new BinaryNode<>(x);
            else
               root.right = recursiveInsert(x, root.right);
        }
        
        return root;
    }
    
    /**
     * Method: hasLeft
     * Checks if a node in the BST has a left subtree.
     * @return true if left subtree is not null, false otherwise.
     */
    public boolean hasLeft(){
        return (root.left != null);
    }
    
    /**
     * Method: hasRight
     * Checks if a node in the BST has a right subtree.
     * @return true if right subtree is not null, false otherwise.
     */
    public boolean hasRight(){
        return (root.right != null);
    }
    
    /**
     * Method: remove
     * Removes/deletes a node from the binary search tree by calling the method
     * recursiveRemove.
     * @param x : the integer to be removed.
     * @return root : the node of the BST after the integer is removed.
     */
    public BinaryNode<Integer> remove(int x){
        root = recursiveRemove(x, root);
        return root;
    }
    
    /**
     * Method: recursiveRemove
     * Removes an integer from the BST recursively.
     * @param x : the integer to be removed.
     * @param t : the node of the BST which element is to be removed.
     * @return t : the node of the BST after the element is removed.
     */
    public BinaryNode<Integer> recursiveRemove(int x, BinaryNode<Integer> t){
        if(t == null){
            return t;
        }
        
        int compareResult = compare(x, t.element);
        
        if(compareResult < 0){
            t.left = recursiveRemove(x, t.left);
        }
        else if(compareResult > 0){
            t.right = recursiveRemove(x, t.right);
        }
        //Two children: replace the target value with the successor value.
        //Then recursively remove the successor node.
        else if(t.left != null && t.right != null){
            t.element = findMin(t.right).element;
            t.right = recursiveRemove(t.element, t.right);
        }
        else{
            t = (t.left != null) ? t.left: t.right;
        }
        
        return t;
    }
    
    /**
     * Method: findMin
     * Finding and returning the smallest number/value in the BST.
     * @param t : the node of the BST which minimum value is to be found.
     * @return t : the node of the BST which is the minimum value of the tree.
     */
    private BinaryNode<Integer> findMin(BinaryNode<Integer> t){
        if(t == null){
            return null;
        }
        else if(t.left == null){
            return t;
        }
        else{
            return findMin(t.left);
        }
    }
    
    /**
     * Method: findMax
     * Finds the largest value in the binary search tree.
     * @param t : the node of the BST which largest value we are looking for.
     * @return t : the node of the BST which is the minimum value of the tree.
     */
    private BinaryNode<Integer> findMax(BinaryNode<Integer> t){
        if(t != null)
            while(t.right != null){
                t = t.right;
            }
        return t;
    }
    
    /**
     * Method: height
     * Determine and return the height of the root of the BST. Calls the method
     * height recursively.
     * @return the height of the root of the BST.
     */
    public int height(){
        return height(root);
    }
    
    /**
     * Method: height
     * Determine and return the height of the root of the BST.
     * @param root : the root of the BST.
     * @return the height of the root of the BST.
     */
    private int height(BinaryNode<Integer> root){
        if(root == null)
            return -1;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }
    
    /**
     * Method: getRoot
     * Gets and returns the root of the binary search tree.
     * @return root : the root of the binary search tree.
     */
    public BinaryNode<Integer> getRoot(){
        return root;
    }
    
    /**
     * Method: search
     * Searches the binary search tree for a specific integer. Calls the method
     * search recursively.
     * @param x : the integer to be searched.
     * @return true if the integer is found, false otherwise.
     */
    public boolean search(int x){
        return search(x, root);
    }
    
    /**
     * Method: search
     * Searches the binary search tree for a specific integer.
     * @param x : the integer to be searched.
     * @param t : the node at which the searched integer is located.
     * @return true if the integer is found, false otherwise.
     */
    public boolean search(int x, BinaryNode<Integer> t){
        boolean found = false;
        while((t != null) && !found){
            int tval = t.getData();
            if(x < tval)
                t = t.getLeft();
            else if(x > tval)
                t = t.getRight();
            else{
                found = true;
                break;
            }
            found = search(x, t);
        }
        return found;
    }
    
    /**
     * Method: compareTo
     * Compares an integer with the element of the node of the BST.
     * @param x : the integer being compared.
     * @param t : the node which value is being compared.
     * @return 0 if the two values are the same, 1 if x is larger than t's element,
     * and return -1 if x is lesser than t's element.
     */
    public int compareTo(int x, BinaryNode<Integer> t){
        if(x == t.element)
            return 0;
        else if(x > t.element)
            return 1;
        else
            return -1;
    }
}