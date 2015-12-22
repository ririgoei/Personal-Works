//Riadiani Marcelita and Bendik Svalastog
//CS 146
//Anna Shaverdian
//Project 3: WordCount.java

import java.io.IOException;
import java.util.*;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	WordCount
*  File:	WordCount.java
* <pre>
*  Description:	An executable that counts the words in a files and prints out the counts 
*  in descending order.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	10/05/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 21, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class WordCount {
    
    public static void main(String[] args) {
    	Runtime rc = Runtime.getRuntime();
    	if(args.length != 3){
    		System.err.println("java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>");
    	}

    	String typeWordCount = "";
        String freqNum = "";
    	String fileName = "";
    	
    	try{
    		typeWordCount = args[0];
    		freqNum = args[1];
    		fileName = args[2];
            countWords(typeWordCount, freqNum, fileName);
    	}
    	catch(Exception e){
    		System.out.println("One or more arguments is missing; try again.");
    	}
            
        long memory = rc.totalMemory() - rc.freeMemory();
        System.out.println("Memory usage in bytes: " + memory);
    }
	
    /**
     * Method: countWords
     * Counts the words in the chosen documents. Words will be stored in either
     * one of the data structures: BST, AVL tree, or hash tables.
     * @param dType the user chooses which data structure to be used by choosing
     * 			either "b" for BST, "a" for AVL, and "h" for hash tables.
     * @param freqNum the user chooses to display the data's word frequency or
     * 			unique number of words by choosing "-frequency" or "-num_number."
     * @param file the document to be analyzed.
     */
	private static void countWords(String dType, String freqNum, String file) {
		DataCounter<String> counter = new BinarySearchTree<String>();
		
    	if(dType.equals("-b")){
    		counter = new BinarySearchTree<String>();
    		System.out.println("Binary Search Tree Chosen.");
    	}
    	else if(dType.equals("-a")){
    		counter = new AVLTree<String>();
    		AVLTree<String> ct = (AVLTree<String>) counter;
    		System.out.println("AVL Tree Chosen.");
    	}
    	else if(dType.equals("-h")){
    		counter = new HashTable();
    		System.out.println("Hash Table Used.");
    	}
    	else{
    		System.out.println("That option is not available. Please try again.");
    		System.exit(1);
    	}
    	long startTime = System.nanoTime();
    	int totalCount = 0;
        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            
            while (word != null) {
                counter.incCount(word);
                totalCount++;
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }
        DataCount<String>[] counts = counter.getCounts();
        sortByDescendingCount(counts);
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> wordCount = new ArrayList<>();
        int numUnique = 0;
        for (DataCount<String> c : counts){
        	words.add(c.data);
        	wordCount.add(c.count);
        	if(c.data != null && c.count != 0){
        		System.out.println(c.count + " \t" + c.data);
            	numUnique += c.count;
        	}
        }
        //Counts the words' frequency if user chooses to dsiplay frequency.
        if(freqNum.equals("-frequency")){
        	System.out.println();
        	System.out.println("Frequency of the words in the file is: ");
        	float total = 0;
        	for(int i = 0; i < words.size(); i++){
        		total = (float) wordCount.get(i) / totalCount;
        		if(total != 0)
        			System.out.println(total + "           " + words.get(i));
        	}
        }
        //Counts the document's number of unique words if the user chooses to display
        //unique numbers.
        else if(freqNum.equals("-num_unique")){
        	System.out.println();
        	System.out.println("Number of unique words in the file is: " + numUnique);
        }
        else{
        	System.out.println("Option is not in the list. Try again.");
        }
        long endTime   = System.nanoTime();
		double totalTime = (double)(endTime - startTime) / 1000000000.0;
		System.out.println("Total runtime is: " + totalTime + " seconds");
    }
	
    /**
     * Method: sortByDescendingCount
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. You should modify it to use a different
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts) {
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
            	
                if (counts[j].count >= x.count) {
                    break;
                }
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }
}