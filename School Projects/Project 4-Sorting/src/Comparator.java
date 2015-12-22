//Riadiani Marcelita
//CS 146
//Anna Shaverdian
//Project 4: Comparator.java


/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Comparator
*  File:	Comparator.java
* <pre>
*  Comparator class to sort the words in alphabetical order if they have the same
*  frequency.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, NetBeans 8.0.2
*  Date:	10/27/2015
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
* </pre>
*  History Log:	Created on October 12, 2015, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Comparator<E> {

	public int compare(DataCount<E> counts1, DataCount<E> counts2){
		if(counts1.count == counts2.count){
			return -counts1.toString().compareTo(counts2.toString());
		}
		else if(counts1.count > counts2.count){
			return 1;
		}
		else{
			return -1;
		}
	}
}