//Bendik Svalastog and Riadiani Marcelita
//Project 2 - BST and AVL tree
//CS 146-Anna Shaverdian

import static java.lang.Integer.compare;
import java.util.logging.Level;
import java.util.logging.Logger;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	AVLTree
*  File:	AVLTree.java
* <pre>
*  Description:	This application creates an AVL tree.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	9/18/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 3, 2015, 07:00 AM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class AVLTree extends BinarySearchTree<Integer> {
    private BinaryNode<Integer> rootAVL;
    
    /**
     * Method: checkBalance
     * Checks if the tree is balanced.
     * @param node : the node of the AVL tree being checked.
     * @return the height of the tree if it is balanced, throws an exception if 
     * otherwise.
     * @throws Exception 
     */
    public int checkBalance(BinaryNode<Integer> node) throws Exception{
        if(node == null)
            return -1;
        if(node != null){
            int leftHeight = checkBalance(node.getLeft());
            int rightHeight = checkBalance(node.getRight());
            if( (Math.abs(height(node.getLeft()) - height(node.getRight()))) > 1
                    || (height(node.getLeft()) != leftHeight
                    || (height(node.getRight()) != rightHeight)))
                    System.out.println("Unbalanced!");
                    throw new Exception("Unbalanced trees.");
        }   
            System.out.println("Balanced.");
            return height(node);
    }
    
    /**
     * Method: isBalanced
     * Checks if the tree is balanced or not.
     * @param root : the root of the AVL tree.
     * @return true if the difference between the subtrees of the tree is lesser
     * than or equal to 1.
     */
    public boolean isBalanced(BinaryNode<Integer> root){
        return (maxHeight(root) - minHeight(root) <= 1);
    }
    
    /**
     * Method: maxDepth
     * Returns the maximum height of the AVL tree.
     * @param root : the root of the AVL tree.
     * @return 0 if the root is null, the maximum height of the AVL tree.
     */
    public int maxHeight(BinaryNode<Integer> root){
        if(root == null) return 0;
        
        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }
    
    /**
     * Method: minHeight
     * Returns the minimum height of the AVL tree.
     * @param root : the root of the AVL tree.
     * @return 0 if the root is null, the minimum height of the AVL tree.
     */
    public int minHeight(BinaryNode<Integer> root){
        if(root == null) return 0;
        
        return 1 + Math.min(minHeight(root.left), minHeight(root.right));
    }
    
    /**
     * Method: balance
     * Balances the AVL tree by rotating it four ways: single right rotation,
     * double left-right rotation, single right rotation, and double right-left
     * rotation.
     * @param node : the node of the AVL tree.
     * @return node : the balanced node.
     */
    public BinaryNode<Integer> balance(BinaryNode<Integer> node){
        if(height(node.getLeft()) - height(node.getRight()) > 1){
            if(height(node.getLeft().getLeft()) >=
                    height(node.getLeft().getRight())){
                node = singleRightRotation(node);
            }
            else{
                node = doubleLeftRightRotation(node);
            }
        }
        else if(height(node.getRight()) - height(node.getLeft()) > 1){
            if(height(node.getRight().getRight()) >=
                    height(node.getRight().getLeft())){
                node = singleLeftRotation(node);
            }
            else{
                node = doubleRightLeftRotation(node);
            }
        }
        node.height = Math.max( height(node.left), height(node.right)) + 1;
        return node;
    }
    
    /**
     * Method: singleRightRotation
     * Rotates the node with a right-right imbalance.
     * @param k2 : the node which has an imbalance.
     * @return 
     */
    private BinaryNode<Integer> singleRightRotation(BinaryNode<Integer> k2){
        BinaryNode<Integer> k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k1.getHeight()) + 1);
        System.out.println("Single Right Rotation: " + k1.element);
        
        return k1;
    }
    
    /**
     * Method: singleLeftRotation
     * Rotates the node with a left-left imbalance.
     * @param k1 : the node which has an imbalance.
     * @return 
     */
    private BinaryNode<Integer> singleLeftRotation(BinaryNode<Integer> k1){
        BinaryNode<Integer> k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
        k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
        System.out.println("Single Left Rotation: " + k1.element);
        
        return k2;
    }
    
    /**
     * Method: doubleLeftRightRotation
     * Rotates the node with a left-right imbalance.
     * @param k3 : the node with an imbalance.
     * @return 
     */
    private BinaryNode<Integer> doubleLeftRightRotation(BinaryNode<Integer> k3){
        k3.setLeft(singleLeftRotation(k3.getLeft()));
        System.out.println("Double left-right rotation: " + k3.element);
        
        return singleRightRotation(k3);
    }
    
    /**
     * Method: doubleRightLeftRotation
     * Rotates the node with a right-left imbalance.
     * @param k1 : the node with an imbalance.
     * @return 
     */
    private BinaryNode<Integer> doubleRightLeftRotation(BinaryNode<Integer> k1){
        k1.setRight(singleRightRotation(k1.getRight()));
        System.out.println("Double right-left rotation: " + k1.element);
        
        return singleLeftRotation(k1);
    }
    
    /**
     * Method: height
     * Returns the height of the tree. Calls the method height recursively.
     * @return 
     */
    @Override
    public int height(){
        return height(rootAVL);
    }
    
    /**
     * Method: height
     * Returns the height of the tree, returns -1 if the tree is empty.
     * @param root
     * @return 
     */
    public int height(BinaryNode<Integer> root){
        if(root == null)
            return -1;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }
    
    /**
     * Method: insert
     * Inserts an integer into the AVL tree. Calls the method recursiveInsert 
     * recursively.
     * @param x : the integer to be inserted.
     * @return the AVL tree with the inserted integer.
     */
    @Override
    public BinaryNode<Integer> insert(int x){
        rootAVL = recursiveInsert(x, rootAVL);
        return rootAVL;
    }
    
    /**
     * Method: recursiveInsert
     * Inserts an integer into the AVL tree, then returns the balanced tree.
     * @param x : the integer to be inserted.
     * @param rootAVL : the node of the AVL tree in which the value is to be inserted.
     * @return the balanced AVL tree with the inserted value.
     */
    @Override
    public BinaryNode<Integer> recursiveInsert(int x, BinaryNode<Integer> rootAVL){
        return balance(super.recursiveInsert(x, rootAVL));
    }
    
    /**
     * Method: findMin
     * Finds the minimum value of the AVL tree.
     * @param t : the node that the minimum value is to be found.
     * @return the node that contains the minimum value in the AVL tree.
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
     * Finds the maximum value of the AVL tree.
     * @param t : the node that the maximum value is to be found.
     * @return the node that contains the maximum value in the AVL tree.
     */
    private BinaryNode<Integer> findMax(BinaryNode<Integer> t){
        if(t != null)
            while(t.right != null){
                t = t.right;
            }
        return t;
    }

    /**
     * Method: remove
     * Removes a specific integer from the AVL tree.
     * @param x : the integer to be removed from the tree.
     * @return rootAVL : the node in which the integer to be removed is found, balanced.
     */
    public BinaryNode<Integer> remove(int x) {
        if(rootAVL.height == 0){
            rootAVL = super.recursiveRemove(x, rootAVL);
            System.out.println("Tree is now empty.");
            return rootAVL;
        }
        
        if(rootAVL != null){
            rootAVL = balance(super.recursiveRemove(x, rootAVL));
            return balance(rootAVL);
        }
        
        return rootAVL;
    }
    
    /**
     * Method: remove
     * Removes a specific integer from the AVL tree.
     * @param x : the integer to be removed.
     * @param t : the node of the AVL tree in which its value is to be removed.
     * @return the node in which the integer to be removed is found.
     */
    public BinaryNode<Integer> recursiveRemove(int x, BinaryNode<Integer> t){
        if(t == null){
            return t;
        }
        
        int compareResult = compareTo(x, t);
        
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
        
        while(!isBalanced(t)){
            t = balance(t);
            return t;
        }
        
        return t;
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
    
    /**
     * Method: getLeft
     * Returns the left element of the root.
     * @return the left element of the root.
     */
    public BinaryNode<Integer> getLeft(){
        return rootAVL.left;
    }
    
    /**
     * Method: getRight
     * Returns the right element of the root.
     * @return the right element of the root.
     */
    public BinaryNode<Integer> getRight(){
        return rootAVL.right;
    }
    
    /**
     * Method: getRoot
     * Returns the root of the AVL tree.
     * @return the root of the AVL tree.
     */
    @Override
    public BinaryNode<Integer> getRoot(){
        return rootAVL;
    }
    
    /**
     * Method: hasLeft
     * Checks if the left element of the root is null or not.
     * @return true if left element is not null, false otherwise.
     */
    @Override
    public boolean hasLeft(){
        return (rootAVL.left != null);
    }
    
    /**
     * Method: hasRight
     * Checks if the right element of the root is null or not.
     * @return true if left element is not null, false otherwise.
     */
    public boolean hasRight(){
        return (rootAVL.right != null);
    }
}