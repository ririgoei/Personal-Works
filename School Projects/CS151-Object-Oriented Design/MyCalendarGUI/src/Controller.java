import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Controller implements ChangeListener {
    private DataModel dataModel;
    private CalendarGUI gui;
    private EventCreateGUI eGui;

    /**
     * Constructor for Controller
     * @param g : the CalendarGUI object to be modified.
     * @param d : the DataModel object to be modified.
     */
    public Controller(CalendarGUI g, DataModel d) {
        gui = g;
        dataModel = d;
        dataModel.attach(this);
        attachNavButtons(gui);
        stateChanged(null);
    }

    /**
     * Method: attachNavButtons
     * The method that attaches the listeners in the buttons on the calendar.
     * Depending on the button chosen, the listeners enable the buttons to
     * toggle between days or months in the calendar.
     * @param g : the CalendarGUI object, will define access to the buttons to
     * be clicked.
     */
    public void attachNavButtons(CalendarGUI g) {
        g.getButton("dayPrev").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModel.prevDay();
            }
        });
        g.getButton("dayNext").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModel.nextDay();
            }
        });
        g.getButton("monthPrev").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModel.prevMonth();
            }
        });
        g.getButton("monthNext").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModel.nextMonth();
            }
        });
        g.getButton("create").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eGui = new EventCreateGUI(dataModel.getCal());
                attachCreateComps(eGui);
            }
        });
        g.getButton("quit").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!dataModel.saveEvents())
                    System.out.println("Oops! The data could not be saved. It's printed out "
                    		+ "for your convinience above.\n");
                System.out.println("Thank you!\n");
                System.exit(0);
            }
        });
        for(final JButton btn : g.getMonthBtns())
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int day = Integer.parseInt(btn.getText());
                        dataModel.goToDay(day);
                    } catch(Exception err) {
                        System.out.println("Not Proper Button");
                    }
                }
            });
    }

    /**
     * Method: attachCreateComps
     * Attach all the listeners when data model is called.
     * @param g : the EventCreateGUI object to access the
     * components in the GUI.
     */
    public void attachCreateComps(final EventCreateGUI g) {
        g.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModel.addEvent(g.getNewEvent());
            }
        });
    }

    /**
     * Method: stateChanged
     * Called whenever there is a change in the data model. It will update
     * components of the view, such as adding a text into the JTable.
     * @param e : the ChangeEvent object that detects the change being made.
     */
    public void stateChanged(ChangeEvent e) {
		gui.getLabel("dayLabel").setText(dataModel.getDayLabel());
        gui.getLabel("monthLabel").setText(dataModel.getMonthLabel());
        DefaultTableModel t = gui.getTableModel();
        for(int i = 0; i < 24; i++)
            t.setValueAt("", i, 1);
        long startTime = dataModel.getCal().getTimeInMillis()/1000;
        long endTime = startTime+86400;
        for(Event ev : dataModel.getEvents())
            if(ev.getStartTime()+25200 > startTime && ev.getStartTime()+25200 < endTime){
                for(int i : ev.getHours())
                	t.setValueAt(" " + t.getValueAt(i-1, 1) + ev.getDesc()+ "  |  ", i-1, 1);
            }
        int days = dataModel.getDaysInMonth();
        int blanks = dataModel.getBlanks();
        int index = 1;
        for(JButton btn : gui.getMonthBtns()) {
            if(index <= blanks || index > blanks+days) {
                btn.setBorderPainted(false);
			    btn.setFocusPainted(false);
                btn.setText("");
            } else {
                btn.setBorderPainted(true);
			    btn.setFocusPainted(true);
                btn.setText((index-blanks)+"");
            }
            index++;
        }
    }
}