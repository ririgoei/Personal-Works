import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	EventCreateGUI
*  File:	EventCreateGUI.java
*  Description:	Handles the GUI for the create new event panel.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class EventCreateGUI {
    private JFrame eventFrame;
    private JPanel createPanel, eventPanel, timeDatePanel;
    private JLabel toLabel, eventLabel;
    private JLabel startTimeLabel;
    private JTextField eventField;
    private JTextField dateField, monthField, yearField;
    private JTextField startHField, startMField, endHField, endMField;
    private JButton saveButton;

    /**
     * Constructor
     */
    public EventCreateGUI() {
		eventFrame = new JFrame();
		createPanel = new JPanel();
		eventPanel = new JPanel();
		timeDatePanel = new JPanel();
		eventField = new JTextField("");
		eventField.setPreferredSize(new Dimension(400, 27));
		eventLabel = new JLabel("Event: ");
		eventPanel.add(eventLabel);
		eventPanel.add(eventField);
		dateField = new JTextField();
		monthField = new JTextField();
		yearField = new JTextField();
		dateField.setPreferredSize(new Dimension(30, 30));
		monthField.setPreferredSize(new Dimension(30, 30));
		yearField.setPreferredSize(new Dimension(50, 30));
		startHField = new JTextField();
		startMField = new JTextField();
		endHField = new JTextField();
		endMField = new JTextField();
		startHField.setPreferredSize(new Dimension(30, 30));
		startMField.setPreferredSize(new Dimension(30, 30));
		endHField.setPreferredSize(new Dimension(30, 30));
		endMField.setPreferredSize(new Dimension(30, 30));
		startTimeLabel = new JLabel("Event Time: ");
		toLabel = new JLabel("to");
		JLabel dotLabel = new JLabel(":");
		JLabel dotLabel2 = new JLabel(":");
		JLabel dashLabel = new JLabel("/");
		JLabel dashLabel2 = new JLabel("/");
		saveButton = new JButton("SAVE");
		timeDatePanel.add(dateField);
		timeDatePanel.add(dashLabel);
		timeDatePanel.add(monthField);
		timeDatePanel.add(dashLabel2);
		timeDatePanel.add(yearField);
		timeDatePanel.add(startTimeLabel);
		timeDatePanel.add(startHField);
		timeDatePanel.add(dotLabel);
		timeDatePanel.add(startMField);
		timeDatePanel.add(toLabel);
		timeDatePanel.add(endHField);
		timeDatePanel.add(dotLabel2);
		timeDatePanel.add(endMField);
		timeDatePanel.add(saveButton);
		createPanel.add(eventPanel);
		createPanel.add(timeDatePanel);
		createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));
		eventFrame.add(createPanel);
		eventFrame.pack();
		eventFrame.setVisible(true);
		eventFrame.setLayout(new FlowLayout());
		eventFrame.setTitle("Create A New Event");
		eventFrame.setResizable(false);
	}

    /**
     * Overloaded constructor. Sets the date field in the
     * Create New Event GUI as the day selected.
     * @param c : the Calendar object that shows the currently
     * selected day.
     */
	public EventCreateGUI(Calendar c) {
		this();
		monthField.setText(""+c.get(Calendar.DATE));
		dateField.setText("" + (c.get(Calendar.MONTH) + 1));
		yearField.setText("" + c.get(Calendar.YEAR));
	}

	/**
	 * Method: getTextField
	 * Gets the text fields used in the program.
	 * @param i : the integer that decides which text field is
	 * to be called.
	 * @return the textfield specified by i.
	 */
    public JTextField getTextField(int i) {
		if(i == 0)
			return eventField;
		else if(i == 1)
			return dateField;
		else if(i == 2)
			return startHField;
		else
			return endHField;
    }

    /**
     * Method: getButton
     * Gets the save button.
     * @return saveButton : the save button in the calendar.
     */
    public JButton getButton() {
        return saveButton;
    }

    /**
     * Method: getNew Event
     * Gets the newly created Event object.
     * @return the newly created Event object.
     */
	public Event getNewEvent() {
		if(!validInput())
			return null;
		else
			return new Event(eventField.getText(), Integer.parseInt(dateField.getText()), 
					Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()), 
					Integer.parseInt(startHField.getText()), Integer.parseInt(startMField.getText()), 
					Integer.parseInt(endHField.getText()), Integer.parseInt(endMField.getText()));
	}

	/**
	 * Method: validInput
	 * Checks if the input by the user is valid.
	 * @return true if valid, false otherwise.
	 */
	public boolean validInput() {
		int tmp;
		try {
			tmp = Integer.parseInt(monthField.getText());
			if(tmp < 1 || tmp > 31)
				return false;
			tmp = Integer.parseInt(dateField.getText());
			if(tmp < 1 || tmp > 12)
				return false;
			tmp = Integer.parseInt(yearField.getText());
			if(tmp < 1000)
				return false;
			tmp = Integer.parseInt(startHField.getText());
			if(tmp < 0 || tmp > 24)
				return false;
			tmp = Integer.parseInt(startMField.getText());
			if(tmp < 0 || tmp > 60)
				return false;
			tmp = Integer.parseInt(endHField.getText());
			if(tmp < 0 || tmp > 24)
				return false;
			tmp = Integer.parseInt(endMField.getText());
			if(tmp < 0 || tmp > 60)
				return false;
		} catch(Exception e) {
			return false;
		}
		eventFrame.setVisible(false);
		eventFrame.dispose();
		return true;
	}
}