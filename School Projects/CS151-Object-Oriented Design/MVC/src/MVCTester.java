import java.util.ArrayList;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MVCTester
*  File:	MVCTester.java
*  Description:	The tester class for the MVC program.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/9/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on March 30, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class MVCTester {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        DataModel d = new DataModel(data);
        View v = new View(d);
        Controller c = new Controller(d);
        d.attach(v);
        c.attach(v);
    }
}