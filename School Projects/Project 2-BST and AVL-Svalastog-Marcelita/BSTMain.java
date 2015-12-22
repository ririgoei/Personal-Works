//Bendik Svalastog and Riadiani Marcelita
//Project 2 - BST and AVL tree
//CS 146-Anna Shaverdian

import java.util.Random;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	BSTMain
*  File:	BSTMain.java
* <pre>
*  Description:	This application compiles the tree.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	9/18/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 3, 2015, 07:00 AM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BSTMain {
    
    public static void main(String[] args) throws Exception{
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int min = 10;       //minimum random integer generated for building the trees.
        int max = 99;       //maximum random integer generated for building the trees.
        long startTime, endTime;
        
        
        // This block is for inserting the same numbers into both trees.
        final int TREE_SIZE = 10000;      //n-random integers generated for the insert and search runtimes.
										  //Can be changed into any value, depending on the user.
        int[] values = new int[TREE_SIZE];
        
        Random random = new Random();
        
        //Part I of the assignment: building BST and AVL tree.
        //Insert first BST, with height 5, random integers from 10-99.
        int counter = 0;
        while(bst.height() < 5){
            bst.insert((int) (Math.random() * ((max - min) + 1)) + min);
        }
        
        //Prints the BST. Then continuously removes the root until the tree is
        //empty, printing the tree after every deletion.
        TreePrinter treePrint = new TreePrinter(bst);
        treePrint.print("Binary Search Tree: ");
        while(bst.getRoot() != null){
            int rootOld = bst.getRoot().element;
            bst.remove(bst.getRoot().element);
            treePrint.print("Binary Search Tree, Node deleted: " + rootOld);
        }
        
        //Building AVL tree.
        AVLTree avl = new AVLTree();
        
        counter = 0;
        
        //Insert first AVL with 35 random integers from 10-99.
        while(counter < 35){
            avl.insert((int) (Math.random() * (max - min) + 1) + min);
            counter++;
        }
        
        //Prints the AVL tree, then continuously remove the root of the tree until
        //the tree is empty, printing the newly balanced tree after every deletion.
        TreePrinter treePrintAVL = new TreePrinter(avl); 
        treePrintAVL.print("AVL Tree: ");
        while(avl.getRoot() != null){
            int rootOld = avl.getRoot().element;
            avl.remove(avl.getRoot().element);
            treePrintAVL.print("AVL Tree, Node deleted: " + rootOld);
        }
        
        //Part II of the assignment: Checking runtime of insert and search for our
        //BST and AVL tree.
        AVLTree timeAVL = new AVLTree();
        BinarySearchTree<Integer> timeBST = new BinarySearchTree<>();
        
        //Generate random values for the BST and AVL tree. Range is from 1 to 
        //TREE_SIZE - 1.
        for(int i = 0; i < values.length; i++){
            values[i] = ((int) (Math.random() * ((TREE_SIZE-1) - 1) + 1) + 1);
        }
        
        //Checking insert and search runtime for AVL tree.
        //AVL tree insert.
        startTime = System.currentTimeMillis();
        for(int i = 0; i < values.length; i++){
            timeAVL.insert(values[i]);
        }
        endTime = System.currentTimeMillis();
        System.out.println("AVL inserts " + TREE_SIZE + " values in ms: " +
                (endTime - startTime));
    
        long startSearchTime, endSearchTime = 0;
        
        //AVL tree search.
        startSearchTime = System.currentTimeMillis();
        for(int i = 0; i < values.length; i++){
            timeAVL.search(values[i]);
            i++;
        }
        endSearchTime = System.currentTimeMillis();
        System.out.println("AVL searches " + TREE_SIZE + " values in ms: " +
                (endSearchTime - startSearchTime));
        //End of AVL insert and search methods.
        
        
        //Checking the insert and search runtime for BST.
        //BST insert.
        startTime = System.currentTimeMillis();
        for(int i = 0; i < values.length; i++){
            timeBST.insert(values[i]);
        }
        endTime = System.currentTimeMillis();
        System.out.println("BST inserts " + TREE_SIZE + " values in ms: " +
                (endTime - startTime));
        
        //BST search.
        startSearchTime = System.currentTimeMillis();        
        for(int i = 0; i < values.length; i++){
            timeBST.search(values[i]);
        }
        endSearchTime = System.currentTimeMillis();
        System.out.println("BST searches " + TREE_SIZE + " values in ms: " +
                (endSearchTime - startSearchTime));
    }
}
