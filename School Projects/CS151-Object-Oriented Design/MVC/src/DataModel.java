import java.util.ArrayList;
import javax.swing.event.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	DataModel
*  File:	DataModel.java
*  Description:	The Model part of the program. Deals with the data being handled,
*  which is the text from the text field to be inputted into the text area.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/9/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on March 30, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DataModel {
    private ArrayList<String> data;
    private ArrayList<ChangeListener> listeners;
    private int index;

    /**
     * Constructor.
     */
    public DataModel() {
        this(new ArrayList<String>(0));
    }

    /**
     * Overloaded constructor.
     * @param d : the arraylist containing the list of text to be inputted.
     */
    public DataModel(ArrayList<String> d) {
        data = d;
        listeners = new ArrayList<ChangeListener>();
    }

    /**
     * Method: get()
     * Gets the arraylist elements.
     * @param i : the index of the arraylist to retrieve.
     * @return : the element of the arraylist.
     */
    public String get(int i) {
        return data.get(i);
    }

    /**
     * Method: size()
     * Returns the size of the arraylist.
     * @return : the size of the arraylist.
     */
    public int size() {
        return data.size();
    }

    /**
     * Method: add()
     * Adds elements into the arraylist.
     * @param toAdd : the string/element to add into the arraylist.
     */
    public void add(String toAdd) {
        data.add(toAdd);
        for (ChangeListener l : listeners)
            l.stateChanged(new ChangeEvent(this));
    }

    /**
     * Method: attach()
     * Attaches the listener.
     * @param c : the listener to attach.
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }

    public ArrayList<String> getData() {
        return (ArrayList<String>) (data.clone());
    }
}