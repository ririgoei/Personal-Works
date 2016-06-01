import java.util.*;
import java.io.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Plane
*  File:	Plane.java
*  Description:	A class that contains the algorithm for adding and cancelling
*  reservations (for both individual and group), printing the availability and
*  manifest list, and quitting the program. Also handles the algorithm for
*  writing the processed/updated reservation information to a .txt file the
*  moment the user quits and ends the program.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	2/27/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on February 19, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Plane {

	private Passenger[][] economyArray = new Passenger[20][6];
	private Passenger[][] firstArray = new Passenger[2][4];
	private String passName, classType, seatPref, groupName;
	
	/**
	 * Method: addPassenger
	 * Makes an individual passenger reservation. Prompts user for passenger's name,
	 * service class type, and seat preference (Window, Center, or Aisle for Economy
	 * class, or Window or Aisle for First class). Finds the next empty seat based on
	 * passenger's preference, seats them, then prints the seat number. If no empty seat
	 * based on their seat preference is available, it prompts the user to choose another
	 * seat preference.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void addPassenger(Scanner scan){
		System.out.println("Name: ");
		passName = scan.nextLine();
		System.out.println("Service Class: ");
		classType = scan.nextLine();
		System.out.println("Seat Preference: ");
		seatPref = scan.nextLine();
		if(classType.equalsIgnoreCase("Economy")){
			if(seatPref.equalsIgnoreCase("W")){
				for(int i = 0; i < economyArray.length; i++){
					if(economyArray[i][0] == null){
						economyArray[i][0] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(0));
						break;
					}
					else if(economyArray[i][5] == null){
						economyArray[i][5] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(5));
						break;
					}
				}
			}
			else if(seatPref.equalsIgnoreCase("C")){
				for(int i = 0; i < economyArray.length; i++){
					if(economyArray[i][1] == null){
						economyArray[i][1] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(1));
						break;
					}
					else if(economyArray[i][4] == null){
						economyArray[i][4] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(4));
						break;
					}
				}
			}
			else if(seatPref.equalsIgnoreCase("A")){
				for(int i = 0; i < economyArray.length; i++){
					if(economyArray[i][2] == null){
						economyArray[i][2] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(2));
						break;
					}
					else if(economyArray[i][3] == null){
						economyArray[i][3] = new Passenger(passName, classType, seatPref, "");
						System.out.println("Your seat number is: " + i+10 + rowToAlphabet(3));
						break;
					}
				}
			}
		}
		else if(classType.equalsIgnoreCase("First")){
			if(seatPref.equalsIgnoreCase("W")){
				for(int i = 0; i < firstArray.length; i++){
					for(int j = 0; j < firstArray[i].length; j += 3){
						if(firstArray[i][j] == null){
							firstArray[i][j] = new Passenger(passName, classType, seatPref, "");
							System.out.println("Your seat number is: " + i+1 + "" + rowToAlphabet(j));
						}
						break;
					}
					break;
				}
			}
			else if(seatPref.equalsIgnoreCase("A")){
				for(int i = 0; i < firstArray.length; i++){
					for(int j = 1; j < 3; j++){
						if(firstArray[i][j]== null){
							firstArray[i][j] = new Passenger(passName, classType, seatPref, "");
							System.out.println("Your seat number is: " + i+1 + "" + rowToAlphabet(j));
						}
						break;
					}
					break;
				}
			}
		}
		else{
			System.out.println("One or more elements is not in the option. Please try again.");
		}
	}
	
	/**
	 * Method: addGroup
	 * Makes a group passenger reservation. Prompts user for group name, passenger's names separated
	 * by commas, and service class type. The method checks if there are enough empty seats in the
	 * service class type to seat every member of the group. If not, the reservation is cancelled. If
	 * yes, it will continuously find the largest adjacent seats on the plane and seat the group members.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void addGroup(Scanner scan){
		String[] groupNamer = new String[120];
		String names;
		int groupSize = 0;
		int emptySeats = 0;
		System.out.println("Group name: ");
		groupName = scan.nextLine();
		System.out.println("Names: ");
		names = scan.nextLine();
		groupNamer = names.split(",");
		groupSize = groupNamer.length;
		System.out.println("Service Type: ");
		classType = scan.nextLine();
		if(classType.equalsIgnoreCase("First")){
			for(int i = 0; i < firstArray.length; i++){
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] == null){
						emptySeats++;
					}
				}
			}
			
			if(emptySeats >= groupSize){
				while(groupSize > 0){
					int m = 0;
					for(int i = 0; i < firstArray.length; i++){
						for(int j = 0; j < firstArray[0].length; j++){
							if(firstArray[i][j] == null && groupSize > 0){
								firstArray[i][j] = new Passenger(groupNamer[m++], classType, "", groupName);
								firstArray[i][j].groupName = groupName;
								groupSize--;
							}
						}
					}				
				}
			}
			else{
				System.out.println("There are not enough seats on the plane for your group!");
			}
		}
		else if(classType.equalsIgnoreCase("Economy")){
			for(int i = 0; i < economyArray.length; i++){
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] == null){
						emptySeats++;
					}
				}
			}
			
			if(emptySeats >= groupSize){
					int k = 0;
					int m = greatestSpace(economyArray);
					System.out.println(m);
					while(groupSize > 0){
						for(int i = m; i < economyArray.length; i++){
							for(int j = 0; j < economyArray[i].length; j++){
								if(economyArray[i][j] == null && groupSize > 0){
									economyArray[i][j] = new Passenger(groupNamer[k++], classType, "", groupName);
									economyArray[i][j].groupName = groupName;
									groupSize--;
								}
							}
						}
					}	
			}
			else{
				System.out.println("There are not enough seats on the plane for your group!");
			}
		}
		else{
			System.out.println("One or more elements is not in the option. Please try again.");
		}
	}
	
	/**
	 * Method: greatestSpace()
	 * Finds the largest adjacent empty seats in the plane, then return the index of the column
	 * that contains the seats.
	 * @param seatArray : the Passenger array that contains the plane's information, including
	 * which seats are already filled.
	 */
	public int greatestSpace(Passenger[][] seatArray){
		int emptySeats = 0;
		int index = 0;
		int max = seatArray[0].length - 1;		
		int tempMax = 0;
		for(int i = 0; i < seatArray.length; i++){
			emptySeats = 0;
			for(int j = 0; j < seatArray[0].length; j++){
				if(seatArray[i][j] == null){
					emptySeats++;
				}
				else{
					emptySeats = 0;
				}
			}
			if(emptySeats > tempMax){
				index = i;
				tempMax = emptySeats;
			}
		}
		return index;
	}
	
	/**
	 * Method: cancelReservation
	 * Cancels individual or group reservation(s). Prompts user for passenger's name if cancelling
	 * individual reservation, and group name if cancelling group reservation. Checks the plane and
	 * once the name of passenger or group members are found, empties their seats.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void cancelReservation(Scanner scan){
		String cancel, individualName, groupName = "";
		System.out.println("Cancel [I]ndividual or [G]roup?");
		cancel = scan.next();
		if(cancel.equals("I")){
			System.out.println("Name: ");
			individualName = scan.next();
			System.out.println(individualName);
			for(int i = 0; i < economyArray.length; i++){
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] != null){
						if(economyArray[i][j].getPassengerName().toString().compareTo(individualName) == 0){
							economyArray[i][j] = null;
						}
						else{
							System.out.println("Name is not found!");
						}
					}
				}
			}
			for(int i = 0; i < firstArray.length; i++){
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] != null){
						if(firstArray[i][j].getPassengerName().toString().compareTo(individualName) == 0){
							firstArray[i][j] = null;
						}
						else{
							System.out.println("Name is not found!");
						}
					}
				}
			}
		}
		else if(cancel.equals("G")){
			System.out.println("Group name: ");
			groupName = scan.next();
			for(int i = 0; i < economyArray.length; i++){
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] != null){
						if(economyArray[i][j].getGroupName().equalsIgnoreCase(groupName)){
							economyArray[i][j] = null;
						}
						else{
							System.out.println("Name is not found!");
						}
					}
				}
			}
			for(int i = 0; i < firstArray.length; i++){
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] != null){
						if(firstArray[i][j].getGroupName().toString().compareTo(groupName) == 0){
							firstArray[i][j] = null;
						}
						else{
							System.out.println("Name is not found!");
						}
					}
					
				}
			}
		}
		else{
			System.out.println("One or more elements is not in the option. Please try again.");
		}
		
	}
	
	/**
	 * Method: availability
	 * Prints a list of all the available/empty seats in the plane. Prompts the user for a service
	 * class type, then displays the availability list for that particular service class.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void availability(Scanner scan){
		String serviceClass;
		System.out.println("Service class: ");
		serviceClass = scan.nextLine();
		if(serviceClass.equalsIgnoreCase("Economy")){
			System.out.println(serviceClass + ": ");
			System.out.println();
			for(int i = 0; i < economyArray.length; i++){
				System.out.print(i+10 + ": ");
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] == null){
						System.out.print(rowToAlphabet(j) + ", ");
					}
				}
				System.out.println();
			}
		}
		else if(serviceClass.equalsIgnoreCase("First")){
			System.out.print(serviceClass + ": ");
			System.out.println();
			for(int i = 0; i < firstArray.length; i++){
				System.out.print(i+1 + ": ");
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] == null){
						System.out.print(rowToAlphabet(j) + ", ");
					}
				}
				System.out.println();
			}
		}
		else{
			System.out.println("Service class type is not on the option. Please try again.");
		}
	}
	
	/**
	 * Method: rowToAlphabet
	 * Changes the number of rows on the plane to alphabets.
	 * @param y : int, the number of row to be changed to an alphabet.
	 */
	public char rowToAlphabet(int y){
		char row = '\0';
		if(y == 0){
			row = 'A';
		}
		else if(y == 1){
			row = 'B';
		}
		else if(y == 2){
			row = 'C';
		}
		else if(y == 3){
			row = 'D';
		}
		else if(y == 4){
			row = 'E';
		}
		else if(y == 5){
			row = 'F';
		}
		return row;		
	}
	
	/**
	 * Method: manifestList
	 * Prints the list of occupied seat numbers and their passenger names. Prompts the user for
	 * a service class type.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void manifestList(Scanner scan){
		System.out.println("Service class: ");
		String service = scan.next();
		char seat;
		System.out.println(service + ":");
		if(service.equalsIgnoreCase("Economy")){
			for(int i = 0; i < economyArray.length; i++){
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] != null){
						seat = rowToAlphabet(j);
						System.out.println(i+10 + "" + seat + ": " + economyArray[i][j].passengerName);
					}
				}
			}
		}
		else if(service.equalsIgnoreCase("First")){
			for(int i = 0; i < firstArray.length; i++){
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] != null){
						seat = rowToAlphabet(j);
						System.out.println(i+1 + "" + seat + ": " + firstArray[i][j].passengerName);
					}
				}
			}
		}
		else{
			System.out.println("Service class type is not on the option. Please try again.");
		}
	}
	
	/**
	 * Method: quit
	 * Quits and terminates the program, then writes the processed information/reservations made
	 * to an output .txt file.
	 * @param scan : the scanner used to input data, could be from user console or input from
	 * a .txt file.
	 */
	public void quit(String fileName){
		System.out.println("You quit.");
		writeToFile(fileName + ".txt");
	}
	
	/**
	 * Method: writeToFile
	 * Writes the processed information/reservations or changes made throughout the execution of the program
	 * to an output .txt file.
	 * @param fileName : the name of the .txt file destination.
	 */
	private void writeToFile(String fileName) {
		try{
			FileWriter fw = new FileWriter(fileName, false);
			PrintWriter output = new PrintWriter(fw);
			String totalOutput = "First 1-2, Left: A-B, Right: C-D; Economy 10-29, Left: A-C, Right: D-F\n";
			for(int i = 0; i < firstArray.length; i++){		
				for(int j = 0; j < firstArray[0].length; j++){
					if(firstArray[i][j] != null){
						totalOutput += i+1 + "" + rowToAlphabet(j) + ", " + firstArray[i][j].toString() + "\n";
					}
				}
			}
			for(int i = 0; i < economyArray.length; i++){
				for(int j = 0; j < economyArray[0].length; j++){
					if(economyArray[i][j] != null){
						totalOutput += i+10 + "" + rowToAlphabet(j) + ", " + economyArray[i][j].toString() + "\n";
					}
					
				}
			}
			output.println(totalOutput);
			output.close();
		}
		catch(Exception e){	
		}
    }
}