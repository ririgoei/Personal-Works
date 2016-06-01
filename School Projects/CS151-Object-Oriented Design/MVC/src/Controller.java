import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Controller
*  File:	Controller.java
*  Description:	A class that serves as the Controller. Calls action listener to
*  add the text inputted in the text field into the text area upon clicking the
*  add button.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/9/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on March 30, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Controller {

	DataModel model;
	
	/**
	 * Constructor
	 * @param model : the data (or text) to be inputted.
	 */
	public Controller(DataModel model) {
		this.model = model;
	}
	
	/**
	 * Method: attach()
	 * The action listener to add the text into the text area
	 * upon clicking the add button.
	 * @param v : View, the class containing the View parts.
	 */
    public void attach(final View v) {
        v.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		model.add(v.getTextField().getText());
        		v.getTextField().setText("");
                System.out.println("You clicked the button");
            }
        });
    }
}
