//Riadiani Marcelita and Bendik Svalastog
//CS 146
//Anna Shaverdian
//Project 3: AVLTree.java

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
*  Date:	10/05/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 21, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements DataCounter<E>
{
    
    /**
     * Method: isBalanced
     * Checks if the tree is balanced or not.
     * @param root : the root of the AVL tree.
     * @return true if the difference between the subtrees of the tree is lesser
     * than or equal to 1.
     */
    public boolean isBalanced(BSTNode root){
        return (maxHeight(root) - minHeight(root) <= 1);
    }
    
    /**
     * Method: maxDepth
     * Returns the maximum height of the AVL tree.
     * @param root : the root of the AVL tree.
     * @return 0 if the root is null, the maximum height of the AVL tree.
     */
    public int maxHeight(BSTNode root){
        if(root == null) return 0;
        
        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }
    
    /**
     * Method: minHeight
     * Returns the minimum height of the AVL tree.
     * @param root : the root of the AVL tree.
     * @return 0 if the root is null, the minimum height of the AVL tree.
     */
    public int minHeight(BSTNode root){
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
    public BSTNode balance(BSTNode node1){
    	if(node1 == null){
    		System.out.println("Node is null.");
    		return node1;
    	}
        if(height(node1.left()) - height(node1.right()) > 1){
            if(height(node1.left().left()) >=
                    height(node1.left().right())){
                node1 = singleRightRotation(node1);
            }
            else{
                node1 = doubleLeftRightRotation(node1);
            }
        }
        else if(height(node1.right()) - height(node1.left()) > 1){
            if(height(node1.right().right()) >=
                    height(node1.right().left())){
                node1 = singleLeftRotation(node1);
            }
            else{
                node1 = doubleRightLeftRotation(node1);
            }
        }
        node1.height = Math.max( height(node1.left), height(node1.right)) + 1;
        return node1;
    }
    
    /**
     * Method: singleRightRotation
     * Rotates the node with a right-right imbalance.
     * @param k2 : the node which has an imbalance.
     * @return 
     */
    private BSTNode singleRightRotation(BSTNode k2){
        BSTNode k1 = k2.left();
        k2.setLeft(k1.right());
        k1.setRight(k2);
        k2.height = Math.max(height(k2.left()), height(k2.right())) + 1;
        k1.height = Math.max(height(k1.left()), k1.getHeight()) + 1;
        //System.out.println("Rotation: SINGLE RIGHT ROTATION - " + k1.data);
        
        return k1;
    }
    
    /**
     * Method: singleLeftRotation
     * Rotates the node with a left-left imbalance.
     * @param k1 : the node which has an imbalance.
     * @return 
     */
    private BSTNode singleLeftRotation(BSTNode k1){
    	//System.out.println("K1: " + k1.data);
        BSTNode k2 = k1.right();
        k1.setRight(k2.left());
        k2.setLeft(k1);
        k1.height = Math.max(height(k1.left()), height(k1.right())) + 1;
        k2.height = Math.max(height(k2.right()), k1.getHeight()) + 1;
           
        return k2;
    }
    
    /**
     * Method: doubleLeftRightRotation
     * Rotates the node with a left-right imbalance.
     * @param k3 : the node with an imbalance.
     * @return 
     */
    private BSTNode doubleLeftRightRotation(BSTNode k3){
        k3.setLeft(singleLeftRotation(k3.left()));
        //System.out.println("Rotation: DOUBLE LEFT RIGHT ROTATION - " + k3.data);
        
        return singleRightRotation(k3);
    }
    
    /**
     * Method: doubleRightLeftRotation
     * Rotates the node with a right-left imbalance.
     * @param k1 : the node with an imbalance.
     * @return 
     */
    private BSTNode doubleRightLeftRotation(BSTNode k1){
        k1.setRight(singleRightRotation(k1.right()));
        //System.out.println("Rotation: DOUBLE RIGHT LEFT ROTATION - " + k1.data);
        
        return singleLeftRotation(k1);
    }
    
    /** 
     * Method: height
     * Returns the height of the tree. Calls the method height recursively.
     * @return 
     */
    @Override
    public int height(){
        return height(overallRoot);
    }
    
    /**
     * Method: height
     * Returns the height of the tree, returns -1 if the tree is empty.
     * @param root
     * @return 
     */
    public int height(BSTNode root){
        if(root == null)
            return -1;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }    
    
    /**
     * Method: isLeaf
     * Returns true if a node in the tree is a leaf, false if otherwise.
     * @param node the node to be checked whether it is a leaf or not.
     * @return true if node is leaf, false otherwise.
     */
    public boolean isLeaf(BSTNode node){
    	if(node != null){
    		if(node.right == null && node.left == null){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Method: incCount
     * Increment the count for a particular data element. If element doesn't
     * exist in the tree, add as a new tree node with count 1; if element exists
     * in tree, increment the node's count.
     * @param data data element whose count to increment.
     */
    public void incCount(E data) {    

    	super.incCount(data);

    	while(!isBalanced(overallRoot)){
    		BSTNode balancedNode = overallRoot;
    		
    	while(!isLeaf(balancedNode)){
    			if(!isBalanced(balancedNode)){
    				overallRoot = balance(balancedNode);
    				balancedNode = overallRoot;
    				return;
    			}
    		
    			if(!isBalanced(balancedNode.right)){
    				balancedNode.right = balance(balancedNode.right);
    			} else{
    				balancedNode = balancedNode.right;
    				return;
    			}
    			
    			if(!isBalanced(balancedNode.left)){
    				balancedNode.left = balance(balancedNode.left);
    			} else{
    				balancedNode = balancedNode.left;
    				return;
    			}
    		}
    	
    	}
    }
    
    /**
     * Method: getLeft
     * Returns the left element of the root.
     * @return the left element of the root.
     */
    public BSTNode getLeft(){
        return overallRoot.left;
    }
    
    /**
     * Method: getRight
     * Returns the right element of the root.
     * @return the right element of the root.
     */
    public BSTNode getRight(){
        return overallRoot.right;
    }
    
    /**
     * Method: getRoot
     * Returns the root of the AVL tree.
     * @return the root of the AVL tree.
     */
    public BSTNode getRoot(){
        return overallRoot;
    }
    
    /**
     * Method: hasLeft
     * Checks if the left element of the root is null or not.
     * @return true if left element is not null, false otherwise.
     */
    public boolean hasLeft(){
        return (overallRoot.left != null);
    }
    
    /**
     * Method: hasRight
     * Checks if the right element of the root is null or not.
     * @return true if left element is not null, false otherwise.
     */
    public boolean hasRight(){
        return (overallRoot.right != null);
    }
}