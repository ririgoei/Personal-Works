//Riadiani Marcelita and Bendik Svalastog
//CS 146
//Anna Shaverdian
//Project 3: WordCount.java

import java.io.IOException;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Correlator
*  File:	Correlator.java
* <pre>
*  Description:	This application correlates two documents using either one of the
*  three data structures: BST, AVL tree, or hash table, and returns the frequency
*  of the words found in both documents.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	10/05/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 21, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Correlator extends WordCount {
	
	public static void main(String[] args){
		Runtime rc = Runtime.getRuntime();
		DataCounter<String> data = new BinarySearchTree<String>();
		DataCounter<String> data2 = new BinarySearchTree<String>();
		String dataType = "";
		String fileName1 = "";
		String fileName2 = "";
		int num1 = 0;
		int num2 = 0;
		
		if(args.length != 3){
			System.err.println("java Correlator [ -b | -a | -h ] <filename1> <filename2>");
		}
		try{
			dataType = args[0];
			fileName1 = args[1];
			fileName2 = args[2];
		}
		catch(Exception e){
			System.out.println("One or more elements is missing; try again.");
			System.exit(1);
		}
		
		if(dataType.equals("-b")){
			data = new BinarySearchTree<String>();
			data2 = new BinarySearchTree<String>();
		}
		else if(dataType.equals("-a")){
			data = new AVLTree();
			data2 = new AVLTree();
		}
		else if(dataType.equals("-h")){
			data = new HashTable();
			data2 = new HashTable();
		}
		else{
			System.out.println("The option is not available. Try again.");
		}
		if(args[1] != null && args[2] != null){
			try{
				num1 = parseAndCount(fileName1, data);
				num2 = parseAndCount(fileName2, data2);
			}
			catch(Exception e){
				System.out.println("Something went wrong! Check your file name/directory, then try again.");
				System.exit(1);
			}
		}
		else{
			System.out.println("One or more elements is missing. Try again.");
		}
		
    	long startTime = System.nanoTime();
		DataCount<String>[] tester = data.getCounts();
		DataCount<String>[] tester2 = data2.getCounts();
		if(data.getSize() > data2.getSize()) {
			System.out.println(compare(tester2,data, num2, num1));
		} else {
			System.out.println(compare(tester, data2, num1, num2));
		}
		
		long endTime = System.nanoTime();
		System.out.println("Time: " + (endTime - startTime) + " seconds.");
		long memory = rc.totalMemory() - rc.freeMemory();
		System.out.println("Memory usage: " + memory);
	}
	
	/**
	 * Method: parseAndCount
	 * Parse each word from the document, insert it into the data structure chosen,
	 * and count the number of words that exist in the document.
	 * @param file the document to be read/parsed.
	 * @param counter the DataCounter object, could be in the form of hash table, BST, or AVL tree.
	 * @return the sum of the words in the document.
	 */
	private static int parseAndCount(String file, DataCounter<String> counter) {
		int sum = 0;
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) {
				counter.incCount(word);
				sum++;
				word = reader.nextWord();
			}
		} catch (IOException e) {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
		return sum;
	}
	
	/**
	 * Method: compare
	 * Compares the two documents and return the word frequency between the two.
	 * @param arrayIterate the array to be itereated through/to compare.
	 * @param counter the data structure object to be compared.
	 * @param iterSum the sum of words in the first document.
	 * @param counterSum the sum of the words in the second document.
	 * @return the correlated frequency of the two documents.
	 */
	private static <E> double compare(DataCount<E>[] arrayIterate, DataCounter<String> counter, int iterSum, int counterSum){
		double corr = 0;
		for(int i = 0; i < arrayIterate.length; i++){
			DataCount<E>  current = (DataCount<E>) arrayIterate[i];
			double j = (double) current.count;
			double freq = j/iterSum;
			if(freq < .01 && freq > .0001) {
				double iCounter = j;
				double freqCounter = iCounter/counterSum;
				if(freqCounter < .01 && freqCounter > .0001) {
					corr += Math.pow((freq - freqCounter), 2);
				}
			}
		}

		return corr;
	}
}