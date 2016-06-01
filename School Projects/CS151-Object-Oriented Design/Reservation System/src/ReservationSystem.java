import java.io.*;
import java.util.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	ReservationSystem
*  File:	ReservationSystem.java
*  Description:	An executable that allows user to make and cancel individual and group
*  reservations on a plane. It also allows user to print an availability and
*  manifest list of that particular plane. User can either input data from console
*  or read a list of commands from a .txt file.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	2/27/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on February 19, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class ReservationSystem {
	
	private static Plane plane = new Plane();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException{
		String menuOption = "";
		String fileName = args[0];
		
		printMenu();
		
		menuOption = scan.nextLine();
		System.out.println(menuOption);
		while(!menuOption.equals("Q")){				//Displays menu option continually if user does not choose to quit.
			menuActions(menuOption, fileName);
			printMenu();
			menuOption = scan.nextLine();
		}
		
		plane.quit(fileName);
	}
	
	/**
	 * Method: printMenu()
	 * Prints the menu option for the reservation system program.
	 */
	public static void printMenu(){
		System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
				+ "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
	}
	
	/**
	 * Method: menuActions
	 * Executes the menu option the user chose, including adding and canceling
	 * individual/group reservations, printing availability and manifest lists,
	 * and quitting the program.
	 * @param menuOption : String, the option the user chooses from the menu list.
	 * @param file : String, the name of the .txt file being processed.
	 */
	public static void menuActions(String menuOption, String file){
		if(menuOption.equalsIgnoreCase("P")){
			plane.addPassenger(scan);
		}
		else if(menuOption.equalsIgnoreCase("G")){
			plane.addGroup(scan);
		}
		else if(menuOption.equalsIgnoreCase("C")){
			plane.cancelReservation(scan);
		}
		else if(menuOption.equalsIgnoreCase("A")){
			plane.availability(scan);
		}
		else if(menuOption.equalsIgnoreCase("M")){
			plane.manifestList(scan);
		}
		else{
			System.out.println("The menu option you chose is not on the list! Please try again.");
		}
	}
}