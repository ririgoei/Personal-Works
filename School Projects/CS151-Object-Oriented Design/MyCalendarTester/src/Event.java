import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Scanner;

public class Event {
    private long startTime, endTime;
    private String description, original;

    public static void main(String[] args){
        Event e1 = new Event("10/09/2015: 21:30 - 23:40 Go to a movie");
        System.out.println(e1 + " <- toString | startTime & endTime: " + e1.getStart() + ", " + e1.getEnd());
        Event e2 = new Event("Start homework", 3, 11, 2016, 04, 30, 06, 00);
        System.out.println(e2 + " <- toString | startTime & endTime: " + e2.getStart() + ", " + e2.getEnd());
        Event e3 = new Event("Procrastinate", 3, 12, 2016, 05, 00);
        System.out.println(e3 + " <- toString | startTime & endTime: " + e3.getStart() + ", " + e3.getEnd());
    }

    public Event() {
        description = "Blank Event.";
        original = "";
        startTime = 0;
        endTime = 0;
    }

    public Event(String line) {
        this();
        String smonth = line.substring(0, 2);
        String sdate = line.substring(3, 5);
        String year = line.substring(6, 10);
        String shourStart = line.substring(12, 14);
        String sminStart = line.substring(15, 17);
        original = smonth+"/"+sdate+"/"+year+": "+shourStart+":"+sminStart;
        try {
            String shourEnd = line.substring(20, 22);
            String sminEnd = line.substring(23, 25);
            Integer.parseInt(shourEnd);
            Integer.parseInt(sminEnd);
            description = line.substring(26);
            endTime = timeConversion(smonth+sdate+year+shourEnd+sminEnd, "MMddyyyyHHmm");
            original += " - "+shourEnd+":"+sminEnd;
        } catch (Exception e) {
            description = line.substring(18);
        }
        startTime = timeConversion(smonth+sdate+year+shourStart+sminStart, "MMddyyyyHHmm");
    }

    //Constructor User No End Time
    public Event(String desc, int month, int date, int year, int startHour, int startMin) {
        description = desc;
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

    //Constructor User With End Time
    public Event(String desc, int month, int date, int year, int startHour, int startMin, int endHour, int endMin) {
        this(desc, month, date, year, startHour, startMin);
        String sendHour = ""+endHour;
        if(endHour < 10)
            sendHour = "0"+sendHour;
        String sendMin = ""+endMin;
        if(endMin < 10)
            sendMin = "0"+sendMin;
        original += " - "+sendHour+":"+sendMin;
        endTime = timeConversion(original.substring(0, 12)+sendHour+":"+sendMin, "MM/dd/yyyy: HH:mm");
    }

    public long getStart() {
        return startTime;
    }

    public long getEnd() {
        return endTime;
    }

    public String getDesc() {
        return description;
    }

    @Override
    public String toString() {
        return original + " " + description;
    }

    public long timeConversion(String time, String format)
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
}