//Riadiani Marcelita
//CS 146
//Anna Shaverdian
//Project 4: WordCount.java

import java.io.IOException;
import java.util.*;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	WordCount
*  File:	WordCount.java
* <pre>
*  Description:	An executable that counts the words in a files and prints out the counts 
*  in descending order using either insertion sort, merge sort, or quick sort.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	10/27/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on October 12, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class WordCount {
    
    public static void main(String[] args) {
    	Runtime rc = Runtime.getRuntime();
    	if(args.length != 3){
    		System.err.println("java WordCount [ -b | -a | -h ] [ -is | -qs | -ms ] <filename>");
    	}

    	String typeWordCount = "";
        String sortAlg = "";
    	String fileName = "";
    	
    	try{
    		typeWordCount = args[0];
    		sortAlg = args[1];
    		fileName = args[2];
            countWords(typeWordCount, sortAlg, fileName);
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
	private static void countWords(String dType, String sortAlg, String file) {
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
        long startTime = System.currentTimeMillis();
        sortByDescendingCount(counts, sortAlg);
        long endTime = System.currentTimeMillis();
        long finalTime = endTime - startTime;
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
        if(sortAlg.equals("-is")){
        	System.out.println("INSERTION SORT successful.");
        }
        else if(sortAlg.equals("-qs")){
        	System.out.println("QUICK SORT successful.");
        }
        else if(sortAlg.equals("-ms")){
        	System.out.println("MERGE SORT successful.");
        }
        System.out.println("Running time for this sorting algorithm: " + finalTime + " ms.");
    }
	
    /**
     * Method: sortByDescendingCount
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This method sorts the data using three different sorting algorithms:
     * insertion sort, merge sort, and quick sort. The sorting algorithm to use
     * is based on the input the user gives during runtime.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(
            DataCount<E>[] counts, String sortAlg) {
    	if(sortAlg.equals("-is")){
    		System.out.println("Using INSERTION SORT.");
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
    	else if(sortAlg.equals("-qs")){
    		System.out.println("Using QUICKSORT.");
    		quickSort(counts);
    	}
    	else if(sortAlg.equals("-ms")){
    		System.out.println("Using MERGE SORT.");
    		doMergeSort(0, counts.length - 1, counts);
    	}
    }

    /**
     * Method: doMergeSort
     * Does merge sort for the data.
     * @param lowerIndex: the 0th index of the data.
     * @param higherIndex: the last index of the data.
     * @param counts: the DataCount array object that stores the words and its counts; the object whose
     * quantity is to be sorted.
     */
    private static<E> void doMergeSort(int lowerIndex, int higherIndex, DataCount<E>[] counts) {
        
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            //Sorts the left side of the array
            doMergeSort(lowerIndex, middle, counts);
            //Sorts the right side of the array
            doMergeSort(middle + 1, higherIndex, counts);
            //Merge both sides
            mergeParts(lowerIndex, middle, higherIndex, counts);
        }
    }
 
    /**
     * Method: mergeParts
     * After merge sort divides the data into separate numbers, they are sorted and merged
     * together again by this method.
     * @param lowerIndex: the 0th index of counts.
     * @param middle: the middle index of counts.
     * @param higherIndex: the last index of counts.
     * @param counts: the DataCounter object array to be sorted.
     */
    private static<E> void mergeParts(int lowerIndex, int middle, int higherIndex, DataCount<E>[] counts) {
 
    	DataCount<E>[] tempMergArr = new DataCount[counts.length];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = counts[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i].count >= tempMergArr[j].count) {
                counts[k] = tempMergArr[i];
                i++;
            } else {
                counts[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            counts[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
    
    /**
     * Method: partition
     * Divides the values into the left side if they are smaller than the pivot value, and to the right if
     * they are bigger than the pivot value. Then sorts the values.
     * @param array: the DataCount object array to be sorted.
     * @param low: the 0th index of array.
     * @param hi: the last index of array.
     * @param comparator: the comparator method.
     * @return left: the value that will be the pivot of the quick sort algorithm.
     */
    private static <E> int partition(DataCount<E>[] array, int low, int hi, Comparator<E> comparator) {
		int left = low;
		int right = hi-1;
		int pivIndex = (low+hi)/2;
		DataCount<E> pivot = array[pivIndex];
		DataCount<E> temp = array[low];
		array[low] = pivot;
		array[pivIndex] = temp;  
		while (left < right) {
			if (comparator.compare(array[right],pivot) < 0) {
				right--;
			}
			else if (comparator.compare(array[left],pivot) >= 0) {
				left++;
			} else {
				DataCount<E> swap = array[left];
				array[left] = array[right];
				array[right] = swap;
			}
		}
		array[low] = array[left];
		array[left] = pivot;
		return left;
	} 
    
    /**
     * Method: quickSort
     * Sorts the data using quick sort.
     * @param array: the DataCount object array to be sorted.
     * @param low: the 0th index of array.
     * @param hi: the last index of array.
     * @param comparator: the comparator object.
     */
    public static <E> void quickSort(DataCount<E>[] array, int low, int hi, Comparator<E> comparator) {
		if(hi-low > 1) {
			int pivot = partition(array, low, hi, comparator);
			quickSort(array, low, pivot, comparator);
			quickSort(array, pivot+1, hi, comparator);
		}
	} 
    
    /**
     * Method: quickSort
     * Helper method for the above quickSort method.
     * @param counts: the DataCount object array to be sorted.
     */
    public static<E> void quickSort(DataCount<E>[] counts){
    	Comparator<E> compare = new Comparator();
    	quickSort(counts, 0, counts.length, compare);
    }
}