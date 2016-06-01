import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Event
*  File:	Event.java
*  Description:	A class that takes care of one individual event in the calendar.
*  Takes care of its date, and its starting and ending time (if available).
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Event {
    private long startTime, endTime;
    private String description, original;
    private int month, date, year, startHour, startMin, endHour, endMin;
    private static String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private static String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    /**
     * Constructs an empty Event object with a blank event description 
     * and all time numbers turned to 0.
     */
    public Event() {
        description = "Blank Event.";
        original = "";
        month = date = year = startHour = startMin = endHour = endMin = 0;
        startTime = endTime = 0;
    }

    /**
     * Overloaded constructor.
     * Constructs an event Object using a line from events.txt
     * @param line  the line of each events.txt
     */
    public Event(String line) {
        this();
        String smonth = line.substring(0, 2);
        String sdate = line.substring(3, 5);
        String syear = line.substring(6, 10);
        String shourStart = line.substring(12, 14);
        String sminStart = line.substring(15, 17);
        original = smonth+"/"+sdate+"/"+syear+": "+shourStart+":"+sminStart;
        try {
            month = Integer.parseInt(smonth);
            date = Integer.parseInt(sdate);
            year = Integer.parseInt(syear);
            startHour = Integer.parseInt(shourStart);
            startMin = Integer.parseInt(sminStart);
            String shourEnd = line.substring(20, 22);
            String sminEnd = line.substring(23, 25);
            endHour = Integer.parseInt(shourEnd);
            endMin = Integer.parseInt(sminEnd);
            description = line.substring(26);
            endTime = timeConversion(smonth+sdate+syear+shourEnd+sminEnd, "MMddyyyyHHmm");
            original += " - "+shourEnd+":"+sminEnd;
        } catch (Exception e) {
            description = line.substring(18);
        }
        startTime = timeConversion(smonth+sdate+syear+shourStart+sminStart, "MMddyyyyHHmm");
    }

    /**
     * Overloaded constructor
     * Constructs an Event object with a description, date, and start time, and without end time.
     * @param desc      : the description of the event, as a String
     * @param month     : the month of the event, as an int
     * @param date      : the date of the event, as an int
     * @param year      : the year of the event, as an int
     * @param startHour : the starting hour of the event, as an int
     * @param startMin  : the starting minute of the event, as an int
     */
    public Event(String desc, int month, int date, int year, int startHour, int startMin) {
        description = desc;
        this.month = month;
        this.date = date;
        this.year = year;
        this.startHour = startHour;
        this.startMin = startMin;
        String smonth = ""+month;
        if(month < 10)
            smonth = "0"+smonth;
        String sdate = ""+date;
        if(date < 10)
            sdate = "0"+sdate;
        String shourStart = ""+startHour;
        if(startHour < 10)
            shourStart = "0"+shourStart;
        String sStartMin = ""+startMin;
        if(startMin < 10)
            sStartMin = "0"+sStartMin;
        original = smonth+"/"+sdate+"/"+year+": "+shourStart+":"+sStartMin;
        startTime = timeConversion(smonth+sdate+year+shourStart+sStartMin, "MMddyyyyHHmm");
    }

    /**
     * Overloaded constructor.
     * Constructs an Event object with a description, date, start time and end time.
     * @param desc      : the description of the event, as a String
     * @param month     : the month of the event, as an int
     * @param date      : the date of the event, as an int
     * @param year      : the year of the event, as an int
     * @param startHour : the starting hour of the event, as an int
     * @param startMin  : the starting minute of the event, as an int
     * @param endHour   : the ending hour of the event, as an int
     * @param endMin    : the ending minute of the event, as an int
     */
    public Event(String desc, int month, int date, int year, int startHour, int startMin, int endHour, int endMin) {
        this(desc, month, date, year, startHour, startMin);
        if(endHour > -1 && endMin > -1) {
            this.endHour = endHour;
            this.endMin = endMin;
            String sendHour = ""+endHour;
            if(endHour < 10)
                sendHour = "0"+sendHour;
            String sendMin = ""+endMin;
            if(endMin < 10)
                sendMin = "0"+sendMin;
            original += " - "+sendHour+":"+sendMin;
            endTime = timeConversion(original.substring(0, 12)+sendHour+":"+sendMin, "MM/dd/yyyy: HH:mm");
        }
    }

    /**
     * Method: getHours
     * Returns array of hours where the event takes place
     * @return  the start time of in unix timestamp in long format
     */
    public int[] getHours() {
        int[] times = new int[endHour-startHour+1];
        for(int i = 0; i < times.length; i++)
            times[i] = startHour + i;
        return times;
    }

    /**
     * Method: getStartTime
     * Returns the start time of the event in Unix Time, not in Milliseconds.
     * @return  the start time of in unix timestamp in long format
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Method: getEnd
     * Returns the end time of the event in Unix Time.
     * @return  the end time of in unix timestamp in long format
     */
    public long getEnd() {
        return endTime;
    }

    /**
     * Method: getDate
     * Returns the date of the event
     * @return  an int that represents the day of the event
     */
    public int getDate() {
        return date;
    }

    /**
     * Method: getDay
     * Returns the name of the day of the event
     * @return  a string ranging from Sunday, Monday, Tuesday, Wednesday, Thursday, Friday and Saturday
     */
    public String getDay() {
        return getDay(date);
    }

    /**
     * Method: getMonth
     * Returns the name of the month of the event
     * @return  a string ranging from January, February, March, April, May, June, July, August, September, October, November, and December
     */
    public String getMonth() {
        return getMonth(month);
    }

    /**
     * Method: getMonthNum
     * Returns the index of the month the event is in, 0 for January and 11 for December.
     * @return  an int ranging from 0 - 11
     */
    public int getMonthNum() {
        return month;
    }

    /**
     * Method: getYear
     * Returns the year of the event
     * @return  an int representation of the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Method: getDay
     * Returns the name of the day of the index passed in
     * @param i : the int of the day we want, with 0 for Sunday and 6 for Saturday
     * @return  : the string representation of the day based on the index passed in
     */
    public static String getDay(int i) {
        try {
            return days[i];
        } 
        catch (Exception e) {
            return "";
        }
    }

    /**
     * Method: getMonth
     * Returns the name of the month of the index passed in
     * @param i : the int of the month we want, with 0 for January and 11 for Sunday
     * @return  : the string representation of the month based on the index passed in
     */
    public static String getMonth(int i) {
        try {
            return months[i];
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Method: timeConversion
     * Returns the long unix time of the time passed in, in the format passed in.
     * @param time      the String of the time we want to convert to unix long time
     * @param format    the format of the string time passed in to convert to unix long time
     * @return          the long representation of the time passed in
     */
    public static long timeConversion(String time, String format)
    {
        long unixtime = 0;
        DateFormat dfm = new SimpleDateFormat(format);
        dfm.setTimeZone(TimeZone.getTimeZone("UTC-08:00"));
        try {
            unixtime = dfm.parse(time).getTime()/1000;
        }
        catch (Exception e){}
        return unixtime;
    }

    /**
     * Method: getOriginal
     * Returns the first half of the event details, such as date, start time and end time
     * @return  the string representation of the date, start time and end time details of the event
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Method: getDesc
     * Returns the description of the event
     * @return  the string description of the event
     */
    public String getDesc() {
        return description;
    }

    /**
     * Method: getCalendarView
     * Returns a single amount of the event details, such as start time and end time
     * @return  the string representation of the start time and end time details of the event
     */
    public String getCalendarView() {
        return original.substring(original.indexOf(":")+2)+" "+description;
    }

    /**
     * Method: getString
     * Returns a string representation of the event
     * @return  the string representation of the event
     */
    public String getString() {
        int sizee = 15;
        if(endTime == 0)
            sizee /= 2;
        return getDay()+" "+getMonth()+" "+getDate()+" "+original.substring(original.length() - sizee)+" "+description;
    }

    /**
     * Method: toString
     * Returns the events.txt string representation of the event
     * @return  the events.txt string representation of the event
     */
    @Override
    public String toString() {
        return original + " " + description;
    }

    /**
     * Method: equals
     * Returns whether the event matches the description
     * @return  a boolean, true if event matches details
     */
    public boolean equals(int date, int month, int year) {
        return date == this.date && month == this.month && year == this.year;
    }
}