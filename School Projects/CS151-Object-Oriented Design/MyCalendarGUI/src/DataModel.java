import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.event.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	DataModel
*  File:	DataModel.java
*  Description:	A class that handles the data model part of the assignment. It
*  updates the calendar whenever any changes are made, and takes care of the
*  Change Listeners.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DataModel {
    private GregorianCalendar cal = new GregorianCalendar();
    private ArrayList<ChangeListener> listeners;
    private ArrayList<Event> eventsArray;
    private String fname;

    /**
     * Constructor for DataModel
     * @param name : name of the .txt file that will be opened and 
     * processed in the program.
     */
    public DataModel(String name) {
        fname = name;
        listeners = new ArrayList<ChangeListener>();
        eventsArray = loadEvents();
        cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
    }

    /**
     * Method: attach
     * Attaches the listeners to the view.
     * @param c : the ChangeListener object to be attached.
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }

    /**
     * Method: update
     * Calls the stateChanged method every time a change is made
     * in the data model, so the view will be updated accordingly.
     */
    public void update() {
        for (ChangeListener l : listeners)
            l.stateChanged(new ChangeEvent(this));
    }

    /**
     * Method: goToDay
     * Goes to a specific day chosen in the calendar.
     * @param i : the date of the specific day chosen in the calendar.
     */
    public void goToDay(int i) {
        cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i);
        update();
    }

    /**
     * Method: prevDay
     * Goes to the day before the current day.
     */
    public void prevDay() {
        traverse(2);
    }

    /**
     * Method: nextDay
     * Goes to the next day after the current one.
     */
    public void nextDay() {
        traverse(4);
    }

    /**
     * Method: nextMonth
     * Goes to the next month after the current one.
     */
    public void nextMonth() {
        traverse(3);
    }

    /**
     * Method: prevMonth
     * Goes to the previous month.
     */
    public void prevMonth() {
        traverse(1);
    }

    /**
     * Menu: traverse
     * Toggles between the days and months of the calendar.
     * @param i : the integer to decide which action to choose,
     * if it will toggle between the days or months.
     */
    public void traverse(int i) {
        if(i == 0) {
            cal = new GregorianCalendar();
            cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        } else if(i == 1) // Go back in month view
            cal.add(cal.MONTH, -1);
        else if(i == 2) // Go back in day view
            cal.add(cal.DATE, -1);
        else if(i == 3) // Go forward in month view
            cal.add(cal.MONTH, 1);
        else if(i == 4) // Go forward in day view
            cal.add(cal.DATE, 1);
        update();
    }

    /**
     * Method: getDayLabel
     * Gets the day label in a specified format.
     * @return the day label in a specified format.
     */
    public String getDayLabel() {
    	return (new SimpleDateFormat("EEEE MMMM dd")).format(cal.getTime());
    }

    /**
     * Method: getMonthLabel
     * Gets the month label in a specified format.
     * @return the month label in a specified format.
     */
    public String getMonthLabel() {
        return Event.getMonth(cal.get(Calendar.MONTH))+" "+cal.getWeekYear();
    }

    /**
     * Method: getCal
     * Gets the current Calendar object.
     * @return cal : the current Calendar object.
     */
    public Calendar getCal() {
        return cal;
    }

    /**
     * Method: addEvent
     * Adds an event into the Arraylist that stores all the
     * existing events.
     * @param e : the Event object that contains the specifications
     * of the events to be added.
     */
    public void addEvent(Event e) {
    	boolean overlap = false;
    	if(e != null) {
        	for(int i = 0; i < eventsArray.size(); i++){
        		if(e.getStartTime() >= eventsArray.get(i).getStartTime() &&
        				e.getStartTime() < eventsArray.get(i).getEnd()){
        			JOptionPane.showMessageDialog(null, "Oops time overlap! Please try again.");
        			overlap = true;
        		}
        		else if(e.getStartTime() == eventsArray.get(i).getEnd())
        			overlap = false;
        	}
        	if(!overlap){
        		eventsArray.add(e);
        		update();
        	}
        } else
            System.out.println("Not Valid Input");
    }

    /**
     * Method: getEvents
     * Gets the arraylist that contains the list of existing events.
     * @return sorted events, the arraylist that contains the list of
     * existing events.
     */
    public Event[] getEvents() {
        return sortEvents(eventsArray);
    }

    /**
     * Method: getBlanks
     * Gets the number of blanks to format the calendar monthly view (how much
     * space to leave as blanks).
     * @return tmp : the integer that specifies the number of blank spaces to place
     * in the calendar monthly view.
     */
    public int getBlanks() {
        GregorianCalendar temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        int tmp = temp.get(Calendar.DAY_OF_WEEK)-1;
        while (tmp > 6) {
            tmp -= 7;
        } return tmp;
    }

    /**
     * Method: getDaysInMonth
     * Gets the number of days in a month.
     * @return the number of days in a month.
     */
    public int getDaysInMonth() {
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Method: getCalendarString
     * Gets the string of the number of days in a month.
     * @return str : the string containing the number of days in a month.
     */
    public String getCalendarString() {
        String str = "";
        for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
            str += i+" ";
        return str;
    }

    /**
     * Method: loadEvents
     * Loads the events from an outer .txt file and place it in an ArrayList of
     * Event objects.
     * @return events : the arraylist containing the existing events.
     */
	public ArrayList<Event> loadEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		System.out.println("Checking if "+fname+" is a valid filename...\n");
		final File aFile = new File("test"+fname);
		try {
			if (aFile.createNewFile())
				aFile.delete();
			Scanner sc = new Scanner(new File(fname));
			while (sc.hasNextLine())
				events.add(new Event(sc.nextLine()));
			sc.close();
			System.out.println("File is opened successfully.");
		} catch (IOException e) {
			System.out.println("Invalid input.\n\nNo such file called "+fname+" available. "
					+ "Please try again.\n");
			System.exit(0);
		} finally {
			return events;
		}
	}

	/**
	 * Method: sortEvents
	 * Sorts the events in chrolonogical order.
	 * @param eventsList : the arraylist containing the existing events.
	 * @return events : the sorted arraylist containing existing events.
	 */
    public Event[] sortEvents(ArrayList<Event> eventsList) {
        Event[] events = eventsList.toArray(new Event[eventsList.size()]);
        int j;
        for(int i = 1; i < events.length; i++) {
            Event temp = events[i];
            for(j = i-1; j >= 0 && temp.getStartTime() > events[j].getStartTime(); j--)
                events[j+1] = events[j];
            events[j+1] = temp;
        }
        return events;
    }

    /**
     * Method: saveEvents
     * Saves the existing events into an outer .txt file when the program
     * terminates.
     * @return true : if the file is saved successfully.
     */
    public boolean saveEvents() {
        String temp = dataToFile(sortEvents(eventsArray));
        System.out.println("Creating Events file "+fname+"...\n");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fname));
            bufferedWriter.write(temp);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Failed to create File "+fname+"!\n\n"+temp);
            return false;
        }
        System.out.println("File "+fname+" created!\n\n"+temp+"\n");
        return true;
    }

    /**
     * Method: dataToFile
     * Stores all the elements of the Event array into a String to later
     * be inputted into the outer .txt file upon saving the program.
     * @param events : the arraylist of Event objects containing existing events.
     * @return temp : the temporary string that contains all the existing events'
     * information.
     */
    public String dataToFile(Event[] events) {
        String temp = "";
        for(int i = 0; i < events.length; i++)
            temp += events[i].toString() + "\n";
        return temp;
    }

    /**
     * Method: printEvents
     * Prints the events in a certain order when it is appended/saved
     * into the outer .txt file.
     * @param events : the Event object array containing the existing
     * events.
     * @return the string that contains the events in a certain format/order.
     */
    public String printEvents(Event[] events) {
        String withEnd = "";
        String withoutEnd = "";
        int endYear = 9999;
        int endlessYear = 9999;
        for(int i = 0; i < events.length; i++) {
            if(events[i].getEnd() == 0) {
                if(endlessYear > events[i].getYear()) {
                    withoutEnd += events[i].getYear()+"\n";
                    endlessYear = events[i].getYear();
                }
                withoutEnd += "  "+events[i]+"\n";
            }
            else {
                if(endYear > events[i].getYear()) {
                    withEnd += events[i].getYear()+"\n";
                    endYear = events[i].getYear();
                }
                withEnd += "  "+events[i]+"\n";
            }
        }
        return withEnd + "\n" + withoutEnd;
    }
}