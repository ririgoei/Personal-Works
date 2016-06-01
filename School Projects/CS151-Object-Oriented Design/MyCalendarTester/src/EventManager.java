import java.util.*;
import java.io.*;
import java.text.*;

public class EventManager {
	
    private DateFormat dfm = new SimpleDateFormat("mm/dd/yyyy");  
    private long unixtime;
    private Event event = new Event();
    
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
