/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	CalendarGUITester
*  File:	CalendarGUITester.java
*  Description:	The main/tester class for CalendarGUI program.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class CalendarGUITester {
	public static void main(String[] args){
		String name = "events.txt";
		if(args.length > 0) {
			if(args[0].indexOf(".txt") < 0)
	            args[0] += ".txt";
			name = args[0];
		}
		DataModel d = new DataModel(name);
		CalendarGUI cg = new CalendarGUI();
		Controller c = new Controller(cg, d);
	}
}
