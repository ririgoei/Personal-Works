import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.math.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	View
*  File:	View.java
*  Description:	The class that takes care of the View part of the program. Draws
*  the GUI components.
*  @author:	Riadiani Marcelita
*  Environment:	PC, Windows 8.1, jdk1.7.0_80, Eclipse Mars Release 4.5.0
*  Date:	4/9/2016
*  @version	%1% %2%
*  @see       	java.util.EmptyStackException;
*  History Log:	Created on March 30, 2016, 07:00 PM
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class View extends JFrame implements ChangeListener {
    private JFrame frame;
    private ArrayList<String> a;
    private JTextArea textArea;
    private DataModel m;
    private JTextField textField;
    private JButton addButton;

    /**
     * Overloaded constructor.
     * @param m : the data model.
     */
    public View(DataModel m) {
        this.m = m;
        JPanel mvcPanel = new JPanel();
        mvcPanel.setLayout(new BorderLayout());
        textField = new JTextField("");
        addButton = new JButton("Add");
        textArea = new JTextArea();
        textArea.setText(fillUp(m));
        textArea.setEditable(false);
        mvcPanel.add(addButton, BorderLayout.NORTH);
        mvcPanel.add(textField, BorderLayout.SOUTH);
        mvcPanel.add(textArea, BorderLayout.CENTER);
        frame = new JFrame();
        frame.add(mvcPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method: fillUp()
     * Gets the string to be inputted and return it to
     * later be used to input in the text area.
     * @param m : the data model element to process.
     * @return the text to be inputted into the text area.
     */
    public String fillUp(DataModel m) {
        String text = "";
        a = m.getData();
        for(int i = 0; i < a.size(); i++)
            text += a.get(i)+"\n";
        return text;
    }

    /**
     * Method: stateChanged()
     * Handles what happened when new string is inputted into
     * the text area and the GUI elements are changed.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        a = m.getData();
        textArea.setText(fillUp(m));
        frame.pack();
    }
    
    /**
     * Method: getTextField()
     * Gets the JTextField.
     * @return the JTextField.
     */
    public JTextField getTextField() {
    	return textField;
    }
    
    /**
     * Method: getButton()
     * Gets the add JButton.
     * @return the add JButton.
     */
    public JButton getButton() {
    	return addButton;
    }
}