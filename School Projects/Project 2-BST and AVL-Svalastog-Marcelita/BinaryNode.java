//Bendik Svalastog and Riadiani Marcelita
//Project 2 - BST and AVL tree
//CS 146-Anna Shaverdian

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	BinaryNode
*  File:	BinaryNode.java
* <pre>
*  Description:	This application creates a node for the BST and AVL tree.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	9/18/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 3, 2015, 07:00 AM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BinaryNode<Integer> {
    
    int element;
    BinaryNode<Integer> left;
    BinaryNode<Integer> right;
    int height;
    
    /**
     * Method: BinaryNode
     * Constructor.
     * @param theElement the element of the node.
     */
    public BinaryNode(int theElement){
        element = theElement;
        left = null;
        right = null;
    }
    
    /**
     * Method: setLeft
     * Sets the value of left element of the node.
     * @param lt the left element of the node.
     */
    public void setLeft(BinaryNode<Integer> lt){
        left = lt;
    }
    
    /**
     * Method: setRight
     * Sets the value of the right element of the node.
     * @param rt the right element of the node.
     */
    public void setRight(BinaryNode<Integer> rt){
        right = rt;
    }
    
    /**
     * Method: setHeight
     * Sets the height of the tree.
     * @param x : the height of the tree.
     */
    public void setHeight(int x){
        height = x;
    }
    
    /**
     * Method: getHeight
     * Gets the height of the tree.
     * @return the height of the tree.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Method: getLeft
     * Gets the left element of a node in the tree.
     * @return the left element of a node in the tree.
     */
    public BinaryNode<Integer> getLeft(){
        return this.left;
    }
    
    /**
     * Method: getRight
     * Gets the right element of a node in the tree.
     * @return the right element of a node in the tree.
     */
    public BinaryNode<Integer> getRight(){
        return this.right;
    }
    
    /**
     * Method: getData
     * Returns the value of the node.
     * @return the value of the node.
     */
    public int getData(){
        return element;
    }
}