//Riadiani Marcelita and Bendik Svalastog
//CS 146
//Anna Shaverdian
//Project 3: HashTable.java

import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	HashTable
*  File:	HashTable.java
* <pre>
*  Description:	This application creates a hash table implementation using arrays.
*  @author:	Bendik Svalastog and Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	10/05/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on September 21, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class HashTable<String> implements DataCounter<String> {

	private static final int DEFAULT_array_SIZE = 71;
	private HashEntry<String> [] array; 
	private int currentSize;
	int countTotal = 0;
	
	/**
	 * Method: HashTable
	 * Creates a new hash table with the default array size.
	 */
	public HashTable()
	{
		this(DEFAULT_array_SIZE);
	}
	
	/**
	 * Method: HashTable
	 * Creates a new hash table with the default array size, sets it as originally empty.
	 * @param size
	 */
	public HashTable(int size)
	{
		allocateArray(size);
		makeEmpty();
	}
	
	/**
	 * Method: hash
	 * Makes a hash code for our data/string.
	 * @param e the string which hashcode is to be made. The hashcode is then modulo'd by
	 * array's length to create an index for the array.
	 * @return the index of the array.
	 */
	public int hash(String e)
	{
		int hash = 7;
		
		for (int i = 0; i < e.toString().length() ; i++) {
		    hash = hash * 3 + e.toString().charAt(i);
		}
		return hash % array.length;
	}
	
	/**
	 * Method: makeEmpty
	 * Makes the hash table empty.
	 */
	public void makeEmpty()
	{
		currentSize = 0;
		for(int i = 0; i < array.length; i++)
		array[i] = null;
	}
	
	/**
	 * Method: allocateArray
	 * Creates a new HashEntry object with the size of the next prime number.
	 * @param arraySize
	 */
	public void allocateArray(int arraySize)
	{
		array = new HashEntry[(nextPrime(arraySize))];
	}
	
    /** {@inheritDoc} */
    public DataCount<String>[] getCounts() 
    {
    	DataCount<String>[] total = new DataCount[array.length];
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i] != null)
    			total[i] = new DataCount(array[i].element, array[i].count);
    		else
    			total[i] = new DataCount(null, 0);

    	}
    	
    	return total;
    }

    /** {@inheritDoc} */
    public int getSize() 
    {
        return currentSize;
    }

    /** {@inheritDoc} */
    public void incCount(String data) 
    {
    	//System.out.println(data);
    	if(array[hash(data)] == null)
    	{
    		insert(data);
    		return;
    	}
    	
    	array[hash(data)].count++;
    	countTotal++;
    }
    
    /**
     * Method: insert
     * Inserts new element into the hash table if it doesn't exist.
     * Rehash if the array is full.
     * @param e
     */
    public void insert(String e)
    {
    	int currentPos = findPos(e);
    	if(isActive(currentPos))
    	{
    		return;
    	}
    	array[currentPos] = new HashEntry<>(e, true, 1);
    	if(++currentSize > array.length / 2)
    		rehash();
    }

    /**
     * Method: rehash
     * Expands the size of the array if it is full.
     */
    private void rehash() {
    	HashEntry<String> [] oldArray = array;
    	// create a double-sized, empty table
    	allocateArray(2 * oldArray.length);
    	currentSize = 0;
    	
    	//copy table over
    	for(int i =0; i < oldArray.length; i++)
    	{
    		if(oldArray[i] != null && oldArray[i].isActive)
    			insert(oldArray[i].element);
    	}
    }

    /**
     * Method: nextPrime
     * Finds the next prime number closest to the number i.
     * @param i the integer to check for the closest prime number to.
     * @return the closest prime number to i.
     */
	private static int nextPrime(int i) {
		if(i % 2 == 0)
			i++;
		while(! isPrime(i))
		{
			i += 2;
		}
		return i;
	}
	
	/**
	 * Method: isPrime
	 * Checks if a number is prime.
	 * @param n the number to check if prime.
	 * @return true if number is prime, false otherwise.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 ) return true;
		if (n % 2 == 0) return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0) return false;
		return true;
	}
    
	/**
	 * Method: contains
	 * Checks if the data already exists in the hash table.
	 * @param e the data to be checked if it already exists.
	 * @return true if data already exists, false otherwise.
	 */
	public boolean contains(String e)
    {
    	return isActive(findPos(e));
    }
    
    //return the position where the search terminates
    private int findPos(String e)
    {
    	int offset = 1;
    	int currentPos = hash(e);
    	while(array[currentPos] != null && !array[currentPos].element.equals(e))
    	{
    		currentPos += offset; //compute ith probe
    		offset += 2;
    		if(currentPos >= array.length)
    			currentPos -= array.length;
    	}
    	return currentPos;
    }

    /**
     * Method: isActive
     * Checks if the current position/index in the array is already filled.
     * @param currentPos the index of the array which availability is to be checked.
     * @return true if the array's index is filled, false otherwise.
     */
    private boolean isActive(int currentPos)
    {
    	return array[currentPos] != null && array[currentPos].isActive;
    }
    
    //Inner class to create a HashEntry object.
    public static class HashEntry<String>
    {
    	public String element;
    	public boolean isActive;
    	public int count = 1;
    	public HashEntry(String e)
    	{
    		this(e, true, 1);
    	}
    	
    	public HashEntry(String e, boolean a, int c)
    	{
    		element = e;
    		isActive = a;
    		count = c;
    	}
    	
    }
}