import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	CalendarManager
*  File:	CalendarManager.java
*  Description:	A class that handles all of the actions that the user could execute
*  on the Gregorian Calendar. All the algorithm for the menu actions are created and
*  executed here. Data structure used: an ArrayList of Calendar objects.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	2/27/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on February 19, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class CalendarManager {

	private Calendar c = new Calendar();
	private ArrayList<Calendar> c1 = new ArrayList<>();
	private GregorianCalendar cal = new GregorianCalendar();
    private MONTHS[] arrayOfMonths = MONTHS.values();
    private DAYS[] arrayOfDays = DAYS.values();
	private int[][] gCal = new int[arrayOfDays.length][7];
	private String dateString, calendarDisplay, monthAndYear, dayLabel;
	private ArrayList<String> date = new ArrayList<String>();
	private int blankSpace, maxDays, maxDaysInMonth;

	/**
	 * Method: displayCalendar
	 * Displays the current month's calendar on the initial program run.
	 * Today's date is highlighted with brackets.
	 * @param calendar : a Gregorian Calendar reference of the current time.
	 */
	public String displayCalendar(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar = new GregorianCalendar(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), 1);
		String[] days = {"S","M","T","W","Th","F","Sa"};
		String mo = arrayOfMonths[calendar.get(calendar.MONTH)].toString();
		maxDays = calendar.getMaximum(calendar.DAY_OF_MONTH);
		maxDaysInMonth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		blankSpace = calendar.get(calendar.DAY_OF_WEEK)-1;
	    //System.out.println(blankSpace);
	    int daysOfMonth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
	    int weekDayIndex = 0;
	    int date = 1;
	    calendarDisplay = "            " + mo + " " + calendar.getWeekYear() + "\n";
	    monthAndYear = mo + " " + calendar.getWeekYear();
		for(int i = 0; i < arrayOfDays.length; i++){
			calendarDisplay += days[i] + "   ";
		}
		calendarDisplay += "\n";
		if(blankSpace < 7){
			for(int i = 0; i < blankSpace; i++){
				dateString += "      ";
				weekDayIndex++;
			}
		}
		for(int i = 0; i < daysOfMonth; i++){
			if(date < 10){
				if(date == cal.get(calendar.DAY_OF_MONTH)){
					dateString += "[" + date++ + "]" + "  ";
				}
				else{
					dateString += date++ + "    ";
				}
				weekDayIndex++;
			}
			else{
				if(date == cal.get(calendar.DAY_OF_MONTH)){
					dateString += "[" + date++ + "]" + " ";
				}
				else{
					dateString += date++ + " ";
				}
				weekDayIndex++;
			}
			if(weekDayIndex == 7){
				weekDayIndex = 0;
				dateString += "\n";
			}
		}
		return calendarDisplay + dateString;
	}

	public String dayLabel(){
		return c.getEnd();
	}

	public String getMonthAndYear(){
		return monthAndYear;
	}

	public int getMaxDays(){
		return maxDaysInMonth;
	}

	public int getBlank(){
		return blankSpace;
	}

	public String getCalLabels(){
		return calendarDisplay;
	}

	public ArrayList<String> getDateString(){
		Scanner in = new Scanner(dateString);
		while(in.hasNext()){
			date.add(in.next());
		}
		return date;
	}

	/**
	 * Method: load
	 * Loads a .txt file into the calendar. If the file doesn't exist, it informs
	 * the user it is the first run then creates the .txt file. If the file already
	 * exists, it reads the date, start time, end time, and event listed in the file
	 * and store it into the Gregorian Calendar.
	 * @param fileName : the name of the .txt file to load.
	 */
	public void load(String fileName){
		File file = new File(fileName);
		boolean fileExists = file.exists();
		Scanner in;
		String event = "";
		String startTime = "";
		String endTime = "";
		Date loadDate = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if(fileExists){
			try {
				in = new Scanner(file);
				while(in.hasNextLine()){
					String dateString = in.nextLine();
	                String Title = dateString.substring(0, dateString.indexOf(" "));
	                String Date = dateString.substring(dateString.indexOf(" ") + 1, dateString.length());
	                String Time = Date.substring(Date.indexOf(" ") + 1, Date.length());
	                endTime = "";
	                startTime = Date.substring(0, Date.indexOf(" "));
	                if(Time.startsWith("- ")){
	                	endTime = Time.substring(2, 7);
	                	event = Time.substring(7, Time.length());
	                }
	                else
	                	event = Time.substring(0, Time.length());
					try {
						loadDate = df.parse(Title);
					} catch (ParseException e) {
						System.out.println("Doesn't work");
						e.printStackTrace();
					}
					c = new Calendar(loadDate, startTime, endTime, event);
					c1.add(c);
					Collections.sort(c1);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("This is the first run.");
		}
	}

	/**
	 * Method: view
	 * Views the calendar in either daily or monthly view. If user
	 * views the calendar daily, the events found on that date is also
	 * printed. User can toggle between days or months of the calendar view.
	 * @param scan : the scanner that takes the user's input.
	 */
	public void view(Scanner scan) {
		String viewOp = "";
		System.out.println("[D]ay view or [M]view ?");
		viewOp = scan.nextLine();
		if(viewOp.equalsIgnoreCase("D")){
			viewOption(cal);
			System.out.println("[P]revious or [N]ext or [M]ain Menu?");
			String view = scan.nextLine();
			GregorianCalendar calToggle = new GregorianCalendar();
			while(!view.equalsIgnoreCase("M")){
				if(view.equalsIgnoreCase("P"))
					calToggle.add(calToggle.DATE, -1);
				else if(view.equalsIgnoreCase("N"))
					calToggle.add(calToggle.DATE, 1);
				viewOption(calToggle);
				System.out.println("[P]revious or [N]ext or [M]ain Menu?");
				view = scan.nextLine();
			}
		}
		else if(viewOp.equalsIgnoreCase("M")){
			displayCalendarMonthly(cal);
			System.out.println("[P]revious or [N]ext or [M]ain Menu?");
			String monthView = scan.nextLine();
			GregorianCalendar monthToggle = new GregorianCalendar();
			while(!monthView.equalsIgnoreCase("M")){
				if(monthView.equalsIgnoreCase("P"))
					monthToggle.add(monthToggle.MONTH, -1);
				else if(monthView.equalsIgnoreCase("N"))
					monthToggle.add(monthToggle.MONTH, 1);
				displayCalendarMonthly(monthToggle);
				System.out.println("[P]revious or [N]ext or [M]ain Menu?");
				monthView = scan.nextLine();
			}
		}
		else{
			System.out.println("That option is not in the menu. Please try again.");
		}
	}

	/**
	 * Method: viewOption
	 * Manages the daily view of the calendar.
	 * @param cal : the Gregorian Calendar used in the program.
	 */
	public void viewOption(GregorianCalendar cal){
		DateFormat curDate = new SimpleDateFormat("MM/dd/YYYY");
		DateFormat currentDatePrint = new SimpleDateFormat("EEEE, MMM dd, YYYY");
		String cur = curDate.format(cal.getTime());
		String current = "";
		System.out.println(currentDatePrint.format(cal.getTime()));
		for(int i = 0; i < c1.size(); i++){
			current = curDate.format(c1.get(i).getCal().getTime());
			if(cur.equals(current)){
				System.out.println(c1.get(i).getEv());
			}
		}
		System.out.println();
	}

	/**
	 * Method: displayCalendarMonthly
	 * Manages the monthly view of the calendar.
	 * @param calendar : the Gregorian Calendar used in the program.
	 */
	public void displayCalendarMonthly(GregorianCalendar calendar){
		String[] days = {"Su","Mo","Tu","We","Th","Fr","Sa"};
		String mo = arrayOfMonths[calendar.get(calendar.MONTH)].toString();
		int maxDays = calendar.getMaximum(calendar.DAY_OF_MONTH);
	    int blankSpace = calendar.get(calendar.getInstance().DAY_OF_WEEK);
	    int daysOfMonth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
	    int weekDayIndex = 0;
	    int date = 1;
		System.out.println(mo + " " + calendar.getWeekYear());
		for(int i = 0; i < arrayOfDays.length; i++){
			System.out.print(days[i] + "  ");
		}
		System.out.println();
		if(blankSpace < 7){
			for(int i = 0; i < blankSpace; i++){
				System.out.print("    ");
				weekDayIndex++;
			}
		}
		for(int i = 0; i < daysOfMonth; i++){
			if(date < 10){
				System.out.print(date++ + "   ");
				weekDayIndex++;
			}
			else{

				System.out.print(date++ + "  ");
				weekDayIndex++;
			}
			if(weekDayIndex == 7){
				weekDayIndex = 0;
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * Method: create
	 * Creates a new event to put into a certain date in the Gregorian Calendar.
	 * Includes a date, start time, end time if it has one, and the event itself.
	 * @param scan : the scanner that takes the user's input.
	 */
	public void create(Scanner scan){
		System.out.print("Title: ");
		String ev = "";
		ev = scan.nextLine();
		System.out.print("Date (MM/DD/YYYY): ");
		String line = scan.nextLine();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = new Date();
		String endTime = "";
		try {
			startDate = df.parse(line);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat dTime = new SimpleDateFormat("hh:mm");
		System.out.print("Starting time (HH:MM): ");
		String startTime = scan.nextLine();
		System.out.println("Ending time (HH:MM), if no end time please skip: ");
		endTime = scan.nextLine();
		c = new Calendar(startDate, startTime, endTime, ev);
		c1.add(c);
		System.out.println("Create successful!");
		Collections.sort(c1);
	}

	/**
	 * Method: goTo
	 * Allows user to go to a certain date and checks if there is an event that day.
	 * @param scan : the scanner that takes the user's input.
	 */
	public void goTo(Scanner scan){
		System.out.println("Enter date to visit (MM/DD/YYYY): ");
		DateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
		String date = scan.nextLine();
		Date inputDate = new Date();
		try {
			inputDate = currentDate.parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		date = currentDate.format(inputDate);
		Date goDate = new Date();
		DateFormat currentDatePrint = new SimpleDateFormat("EEEE, MMM dd, YYYY");
		try {
			goDate = currentDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String current = "";
		System.out.println(currentDatePrint.format(goDate));
		for(int i = 0; i < c1.size(); i++){
			if(date.equals(c1.get(i).getCurrentDate())){
				System.out.println(c1.get(i).getEv());
			}
		}
	}

	/**
	 * Method: eventList
	 * Prints a list of all the events found in the calendar, sorted in
	 * order based on the year, and if the events have an endtime or not.
	 * @param scan : the scanner that takes the user's input.
	 */
	public void eventList(Scanner scan){
		Collections.sort(c1);
        String withEnd = "";
        String withoutEnd = "";
        int endYear = 9999;
        int endlessYear = 9999;
        for(int i = 0; i < c1.size(); i++) {
            if(!c1.get(i).getEndTime().equals("")) {
                if(endlessYear > c1.get(i).getYear()) {
                    withoutEnd += c1.get(i).getYear()+"\n";
                    endlessYear = c1.get(i).getYear();
                }
                else if(endlessYear < 9999 && endlessYear < c1.get(i).getYear()){
                	withoutEnd += c1.get(i).getYear() + "\n";
                	endlessYear = c1.get(i).getYear();
                }
                withoutEnd += "  "+c1.get(i).getEnd() + " " + c1.get(i).getTotalTime() + " " + c1.get(i).getEv() +"\n";
            }
            else {
                if(endYear > c1.get(i).getYear()) {
                    withEnd += c1.get(i).getYear()+"\n";
                    endYear = c1.get(i).getYear();
                }
                else if(endYear < 9999 && endYear < c1.get(i).getYear()){
                	withEnd += c1.get(i).getYear() + "\n";
                	endlessYear = c1.get(i).getYear();
                }
                withEnd += "  "+c1.get(i).getEnd() + " " + c1.get(i).getTotalTime() + " " + c1.get(i).getEv() +"\n";
            }
        }
        System.out.println(withEnd + "\n" + withoutEnd);
	}

	/**
	 * Method: delete
	 * Deletes either an event in a selected date or all events in the
	 * calendar.
	 * @param scan : the scanner that takes the user's input.
	 */
	public void delete(Scanner scan){
		System.out.println("[S]elected or [A]ll?");
		DateFormat delDate = new SimpleDateFormat("MM/dd/yyyy");
		String delChoice = scan.nextLine();
		String date = "";
		if(delChoice.equalsIgnoreCase("s")){
			System.out.println("Enter date to delete (MM/DD/YYYY):");
			date = scan.nextLine();
			System.out.println("Date: " + date);
			Date deleteDate = new Date();
			try {
				deleteDate = delDate.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			date = delDate.format(deleteDate);
			String del = delDate.format(deleteDate);
			String fromArray = "";
			for(int i = 0; i < c1.size(); i++){
				fromArray = c1.get(i).getCurrentDate();
				if(del.equals(fromArray))
					c1.remove(i);
			}

		}
		else if(delChoice.equalsIgnoreCase("a"))
			c1 = new ArrayList<>();
		else
			System.out.println("The option is not on the list. Please try again.");
	}

	/**
	 * Method: quit
	 * Terminates the program and writes the results of the program execution
	 * to a .txt file.
	 * @param fileName : the name of the direction .txt file.
	 */
	public void quit(String fileName){
		System.out.println("You quit!");
		writeToFile(fileName);
	}

	/**
	 * Method: writeToFile
	 * Writes the result of the program execution or any modifications
	 * made on the initial list of events to an external .txt file.
	 * @param fileName : the name of the direction .txt file.
	 */
	public void writeToFile(String fileName){
		try{
			FileWriter fw = new FileWriter(fileName, false);
			PrintWriter output = new PrintWriter(fw);
			String totalOutput = "";
			for(int i = 0; i < c1.size(); i++){
				totalOutput += c1.get(i).toString() + "\n";
			}
			output.println(totalOutput);
			output.close();
		}
		catch(Exception e){
		}
	}
}