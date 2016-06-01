import java.util.*;
import java.text.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	EventManager
*  File:	EventManager.java
*  Description: Manages the events that exist in the calendar. Converts the events'
*  date and time into unix timestamp for easier conversion throughout the entire
*  program later.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class EventManager {
	
    private DateFormat dfm = new SimpleDateFormat("mm/dd/yyyy");  
    private long unixtime;
    private Event event = new Event();
    
    /**
     * Method: timeConversion
     * Converts time into its unix time to later be processed
     * in the program.
     * @param time : the time to be turned into the unix time.
     * @return unixtime : the long variable that contains the certain
     * time in unix time.
     */
    public long timeConversion(String time)
    {
        dfm.setTimeZone(TimeZone.getTimeZone("UTC-08:00"));//Specify your timezone 
    try
    {
        unixtime = dfm.parse(time).getTime();  
        unixtime = unixtime/1000;
    } 
    catch (ParseException e) 
    {
        e.printStackTrace();
    }
    return unixtime;
    }
}
