/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Passenger
*  File:	Passenger.java
*  Description:	A class that instantiates and sets a passenger's information,
*  including passenger/group name(s), service class type, seating preference,
*  and group name.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	2/27/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on February 19, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Passenger {

	public String passengerName;
	public String groupName;
	public String classType;
	public String seatPref;
	public boolean isFilled;
	
	/**
	 * Constructor.
	 */
	public Passenger(){
		passengerName = "";
		classType = "";
		seatPref = "";
		isFilled = false;
		groupName = "";
	}
	
	/**
	 * Method: Passenger
	 * Overloaded constructor, sets the passenger's name, service class type, seat preference,
	 * group name if part of a group, and sets the seat to filled.
	 * @param passName  String, the passenger's name.
	 * @param classPref  String, the passenger's service class type preference.
	 * @param seatPrefer  String, the passenger's seat preference.
	 * @param isFilled boolean, true if seat is filled, false otherwise.
	 */
	public Passenger(String passName, String classPref, String seatPrefer, String group){
		passengerName = passName;
		classType = classPref;
		seatPref = seatPrefer;
		groupName = group;
		isFilled = true;
	}
	
	/**
	 * Method: getPassengerName
	 * Gets the passenger's name.
	 * @return passengerName : String, the passenger's name.
	 */
	public String getPassengerName(){
		return passengerName;
	}
	
	/**
	 * Method: getClassType
	 * Gets the service class type.
	 * @return classType : String, the service class type.
	 */
	public String getClassType(){
		return classType;
	}
	
	/**
	 * Method: getSeatPref
	 * Gets the passenger's seat preference.
	 * @return seatPref : String, the passenger's seat preference.
	 */
	public String getSeatPref(){
		return seatPref;
	}
	
	/**
	 * Method: getGroupName
	 * Gets the group name.
	 * @return groupName : String, the group name.
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * Method: toString
	 * Returns the String in the format that should appear in the output .txt file.
	 */
	@Override
	public String toString(){
		String personType = "I, " + getPassengerName();
		if(!getGroupName().equals("")){
			personType = "G, " + getGroupName() + ", " + getPassengerName();
		}
		return personType;
	}
}