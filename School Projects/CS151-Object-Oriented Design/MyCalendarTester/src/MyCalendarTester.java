import java.util.*;
import java.io.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MyCalendarTester
*  File:	MyCalendarTester.java
*  Description:	An executable that allows user to create and delete events in a
*  Gregorian Calendar. User can also view the events stored in the calendar either
*  on a daily or monthly view, or in a list of events. User can also load a .txt file
*  containing events into the calendar. If the .txt file name does not exist, the program
*  will notify the user and create a new file. When the user chooses to quit the program,
*  all activity while the program was active and the list of events contained in the
*  calendar will be printed to separate .txt files.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	3/21/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on March 1, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class MyCalendarTester {

	private static CalendarManager calendar = new CalendarManager();
	private static Scanner scan = new Scanner(System.in);
	private static GregorianCalendar cal = new GregorianCalendar();
	
	public static void main(String[] args){
		
		String fileName = args[0];
		scan = new Scanner(System.in);
		calendar.displayCalendar(cal);
		menuOption();
		String choiceMenu = scan.nextLine();
		while(!choiceMenu.equalsIgnoreCase("Q")){	//Displays menu option continually if user does not choose to quit.
			System.out.println();
			menuActions(choiceMenu, fileName);
			menuOption();
			choiceMenu = scan.nextLine();
			System.out.println();
		}
		
		calendar.quit(fileName);
	}
	
	/**
	 * Method: printMenu()
	 * Prints the menu option for the reservation system program.
	 */
	public static void menuOption(){
		System.out.println("Select one of the following options:");
		System.out.println("[L]oad    [V]iew by    [C]reate,  [G]o to  [E]vent list   [D]elete   [Q]uit");	
	}
	
	/**
	 * Method: menuActions
	 * Executes the menu option the user chose, including loading, creating, and deleting
	 * events, and viewing the calendar daily, monthly, or in an event list,and quitting 
	 * the program.
	 * @param menuOption : String, the option the user chooses from the menu list.
	 * @param file : String, the name of the .txt file being processed.
	 */
	public static void menuActions(String menuOption, String fileName){
		if(menuOption.equalsIgnoreCase("L")){
			calendar.load(fileName);
		}
		else if(menuOption.equalsIgnoreCase("V")){
			calendar.view(scan);
		}
		else if(menuOption.equalsIgnoreCase("C")){
			calendar.create(scan);
		}
		else if(menuOption.equalsIgnoreCase("G")){
			calendar.goTo(scan);
		}
		else if(menuOption.equalsIgnoreCase("E")){
			calendar.eventList(scan);
		}
		else if(menuOption.equalsIgnoreCase("D")){
			calendar.delete(scan);
		}
		else{
			System.out.println("The menu option you chose is not in the option. Please try again.");
		}
	}
}