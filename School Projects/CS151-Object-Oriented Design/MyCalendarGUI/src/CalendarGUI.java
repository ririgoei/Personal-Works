import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	CalendarGUI
*  File:	CalendarGUI.java
*  Description:	The view part of the program that handles the GUI of the calendar.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/30/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on April 10, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class CalendarGUI {
	private DefaultTableModel tableModel;
	private JButton[] monthButtons;
	private JButton createButton;
	private JButton quitButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton leftMonthButton;
	private JButton rightMonthButton;
	private JLabel monthYearLabel;
	private JLabel dayLabel;

	public CalendarGUI() {
		
		tableModel = new DefaultTableModel() { public boolean isCellEditable(int rowIndex, int mColIndex) { return false; } };
		tableModel.setColumnCount(2);
        tableModel.setRowCount(24);
		tableModel.setValueAt("12 PM", 11, 0);
		tableModel.setValueAt("12 AM", 23, 0);
		
        for(int i = 0; i < 11; i++)
        	tableModel.setValueAt((i+1) + " AM", i, 0);
        for(int i = 12; i < 23; i++)
        	tableModel.setValueAt((i-11)+" PM", i, 0);

		monthYearLabel = new JLabel("");
		dayLabel = new JLabel("");

		JPanel buttonsPanel = new JPanel();
		leftButton = new JButton("< Day");
		rightButton = new JButton("Day >");
		quitButton = new JButton("QUIT");
		buttonsPanel.add(leftButton, BorderLayout.NORTH);
		buttonsPanel.add(rightButton, BorderLayout.NORTH);
		buttonsPanel.add(quitButton, BorderLayout.NORTH);

		JPanel calViewPanel = new JPanel();
		calViewPanel.setBorder(new EmptyBorder(0, 10, 40, 10));
		createButton = new JButton("CREATE");
		leftMonthButton = new JButton("< Month");
		rightMonthButton = new JButton("Month >");
		calViewPanel.add(createButton, new GridLayout(1,2));
		calViewPanel.add(leftMonthButton, new GridLayout(1, 2));
		calViewPanel.add(rightMonthButton, new GridLayout(1, 2));
		calViewPanel.add(buttonsPanel, new GridLayout(1, 2));

		JTable calendarTable = new JTable(tableModel);
		JScrollPane calendarScroll = new JScrollPane(calendarTable);
        calendarTable.setRowHeight(50);
		calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		calendarTable.getColumnModel().getColumn(0).setPreferredWidth(37);
		calendarTable.getColumnModel().getColumn(1).setPreferredWidth(420);

		JButton tmpBtn;
		JPanel dateButtonPanel = new JPanel();
		dateButtonPanel.add(monthYearLabel, BorderLayout.NORTH);
		dateButtonPanel.setLayout(new GridLayout(7, 7));
		dateButtonPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		monthButtons = new JButton[42];
		String[] days = {"S","M","T","W","Th","F","Sa"};
		for(int i = 0; i < days.length; i++) {
			tmpBtn = new JButton(days[i]);
			tmpBtn.setEnabled(false);
			tmpBtn.setBorderPainted(false);
			tmpBtn.setFocusPainted(false);
			dateButtonPanel.add(tmpBtn);
		} for(int i = 0; i < monthButtons.length; i++) {
			tmpBtn = new JButton();
			tmpBtn.setBorderPainted(false);
			tmpBtn.setFocusPainted(false);
			monthButtons[i] = tmpBtn;
			dateButtonPanel.add(monthButtons[i]);
		}

		JPanel monthDatePanel = new JPanel();
		monthDatePanel.add(monthYearLabel);
		monthDatePanel.add(dateButtonPanel);
		monthDatePanel.setLayout(new BoxLayout(monthDatePanel, BoxLayout.Y_AXIS));

		JPanel tablePanel = new JPanel();
		tablePanel.add(dayLabel);
		tablePanel.add(calendarScroll);
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setBorder(new EmptyBorder(0, 0, 10, 10));

		JFrame calFrame = new JFrame();
		calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calFrame.setSize(200, 400);
		calFrame.add(monthDatePanel, BorderLayout.WEST);
		calFrame.add(calViewPanel, BorderLayout.BEFORE_FIRST_LINE);
		calFrame.add(tablePanel);
		calFrame.setVisible(true);
		calFrame.setResizable(false);
		calFrame.pack();
	}

	/**
	 * Method: getMonthBtns
	 * Gets the array of buttons with the dates on the Calendar.
	 * @return monthButtons : array of buttons with the dates on the Calendar.
	 */
	public JButton[] getMonthBtns() {
		return monthButtons;
	}

	/**
	 * Method: getButton
	 * Gets the button that is clicked.
	 * @param str : the string on the JButton that is clicked.
	 * @return the JButton that is clicked.
	 */
	public JButton getButton(String str) {
		if(str.equals("dayPrev"))
			return leftButton;
		else if(str.equals("dayNext"))
			return rightButton;
		else if(str.equals("monthPrev"))
			return leftMonthButton;
		else if(str.equals("monthNext"))
			return rightMonthButton;
		else if(str.equals("create"))
			return createButton;
		else if(str.equals("quit"))
			return quitButton;
		else
			return null;
	}

	/**
	 * Method: getLabel
	 * Gets the label on the calendar, either the day label
	 * (current day, month/date), or month (current month and year)
	 * @param str : the String, to choose if we are to display the
	 * day label or month label.
	 * @return
	 */
	public JLabel getLabel(String str) {
		if(str.equals("dayLabel"))
			return dayLabel;
		else if(str.equals("monthLabel"))
			return monthYearLabel;
		return null;
	}

	/**
	 * Method: getTableModel
	 * Gets the table model that is used in the JTable.
	 * @return tableModel : the current table model used.
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
}