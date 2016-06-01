import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Calendar
*  File:	Calendar.java
*  Description:	A class that creates a Calendar object, with accessor and mutator
*  methods of the object's variables. Variables include a Gregorian Calendar object
*  with a set date, the start and endtime of events, and the event of that particular
*  date.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	2/27/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on February 19, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Calendar implements Comparable<Calendar> {
    
    private GregorianCalendar cal;
    private String event, startTime, endTime, currentD;
    
    /**
     * Constructor.
     */
    public Calendar(){
        cal = new GregorianCalendar();
        event = "";
        startTime = "";
        endTime = "";
    }
    
    /**
     * Overloaded constructor. Creates the Calendar object.
     * @param date : the date of the event, in the format MM/DD/YYYY.
     * @param startT : the start time of the event.
     * @param endT : the end time of the event, if there is one. If not, set it to empty.
     * @param ev : the event.
     */
    public Calendar(Date date, String startT, String endT, String ev){
        cal = new GregorianCalendar();
        cal.setTime(date);
        startTime = startT;
        endTime = endT;
        event = ev;
    }
    
    /**
     * Method: getCal
     * Gets the Gregorian Calendar object and its components (time, time in millis, etc.)
     * @return cal : the Gregorian Calendar object.
     */
    public GregorianCalendar getCal(){
        return cal;
    }
    
    /**
     * Method: getYear
     * Gets the year of the Gregorian Calendar object's date.
     * @return cal.getWeekYear : int of the Gregorian Calendar object's year.
     */
    public int getYear(){
    	return cal.getWeekYear();
    }
    
    /**
     * Method: setEv
     * Sets the event of the Gregorian Calendar object.
     * @param ev : the event to be inserted into the Gregorian Calendar object.
     */
    public void setEv(String ev){
        event = ev;
    }
    
    /**
     * Method: getEv
     * Gets the event of the Gregorian Calendar object.
     * @return event : String of the event of the Gregorian Calendar object.
     */
    public String getEv(){
    	return event;
    }
    
    /**
     * Method: getStartTime
     * Gets the start time of an event in a Gregorian Calendar object.
     * @return startTime : String of the start time of an event in a Gregorian Calendar object.
     */
    public String getStartTime(){
    	return startTime;
    }
    
    /**
     * Method: getEndTime
     * Gets the end time of an event in a Gregorian Calendar object.
     * @return endTime : String of end time of an event in a Gregorian Calendar object.
     */
    public String getEndTime(){
    	return endTime;
    }
    
    /**
     * Method: getTotalTime
     * Gets the start time and end time of an event in a Gregorian Calendar object.
     * @return totalTime : the String that contains the start time and end time of an event
     * in a Gregorian Calendar object.
     */
    public String getTotalTime(){
    	String totalTime = startTime;
    	if(!endTime.equals("")){
    		totalTime += " - " + endTime;
    	}
    	return totalTime;
    }
    
    /**
     * Method: getCurrentData
     * Gets the current date in the format MM/dd/yyyy.
     * @return currentD : String of the current date in the format MM/dd/yyyy.
     */
    public String getCurrentDate(){
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	currentD = df.format(cal.getTime());
    	return currentD;
    }
    
    /**
     * Method: getEnd
     * Gets the current date in the form Day Month Date
     * @return currentDate : String that contains the current date in the
     * form Day Month Date.
     */
    public String getEnd(){
    	DateFormat df = new SimpleDateFormat("EEEE MMMM dd");
    	String currentDate = df.format(cal.getTime());
    	return currentDate;
    }
    
    /**
     * Method: toString
     * The overriden toString method.
     */
    @Override
    public String toString(){
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	String currentDate = df.format(cal.getTime());
    	String writeTo = currentDate + ": " + startTime + " ";
    	if(!endTime.equals("")){
    		writeTo += "- " + endTime + " ";
    	}
    	writeTo += event;
    	return writeTo;
    }
    
    /**
     * Method: compareTo
     * The overriden compareTo method.
     * @param c2 : the Calendar object to be compared with another 
     * Calendar object.
     */
	@Override
	public int compareTo(Calendar c2) {
		return this.getCal().getTime().compareTo(c2.cal.getTime());
	}
}