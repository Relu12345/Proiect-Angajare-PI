package Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.toedter.calendar.JCalendar;
import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Fereastra unde se pot atribui zile libere
 * @author Coruian Aurel-Ionut
 */
public class calendar_employee {
	
    /**
     * Variabila JFrame pentru fereastra
     */
    JFrame frame;
    /**
     * Variabila JPanel pentru a imparti fereastra, avand pe acest panou calendaul
     */
    JPanel calendarPanel;
    /**
     * Variabila JPanel pentru a imparti fereastra, avand pe acest panou zilele ramase
     */
    JPanel remainingPanel;
    /**
     * Variabila JPanel pentru a imparti fereastra, avand pe acest panou containerele interactive si date despre ziua respectiva
     */
    JPanel eventPanel;
    /**
     * Variabila JCalendar din pachetul toedter folosita pentru a afisa un calendar
     */
    JCalendar calendar;
    /**
     * Variabila JLabel pentru a afisa textul pentru data selectata
     */
    JLabel selectedDateLabel;
    /**
     * Variabila JLabel pentru a afisa textul pentru zile libere ramase
     */
    JLabel daysLabel;
    /**
     * Variabila JLabel pentru a afisa numarul de zile libere ramase 
     */
    JLabel daysText;
    /**
     * Variabila JComboBox pentru a putea selecta ce fel de zi libera poti alege
     */
    JComboBox eventField;
    /**
     * Variabila JButton pentru a adauga o zi libera
     */
    JButton addEventButton;
    /**
     * Variabila JButton pentru a sterge o zi libera
     */
    JButton removeEventButton;
    /**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
    JButton backButton;
    /**
     * Variabila Map(String, String) pentru a retine data si tipul de zi libera luat
     */
    Map<String, String> events;
    /**
     * Variabila int pentru a stoca valoarea din baza de date pentru numarul de ore lucrate
     */
    int hours;
    /**
     * Variabila int pentru a stoca valoarea din baza de date pentru numarul de zile libere ramase
     */
    int days;   
    
    /**
     * Constructor
     * @param emp_mail Preia de la login emailul angajatului
     */
    public calendar_employee(String emp_mail) {
    	selectedDateLabel = new JLabel();
    	try {
    		conn con = new conn();
    		String q = "select hours, days from date join employee on employee.email = date.username where username='"+emp_mail+"'";
            ResultSet rs = con.st.executeQuery(q);
            if(rs.next()) {
            	hours = rs.getInt(1);
            	days = rs.getInt(2);
            }
    		
    	} catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Database error, could not get free days left from database", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();   
    	}
        // Initialize the calendar and events list
        calendar = new JCalendar();
        calendar.setFont(new Font("serif",Font.BOLD,25));
        events = new HashMap<String, String>();
        try {
        	conn con = new conn();
        	String q = "select date, type from timeoff where username='"+emp_mail+"'";
        	System.out.println(emp_mail);
        	ResultSet rs = con.st.executeQuery(q);
        	while(rs.next()) {
        		String d = rs.getString(1);
        		String s = rs.getString(2);
            	events.put(d, s);
        	}
        	updateSelectedDateLabel();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database error, could not get date and type leave", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        // Create the frame
        frame = new JFrame("Planner");
        frame.setLayout(new GridBagLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        // Create the calendar panel
        daysLabel = new JLabel("Days Remaining: ");
        daysLabel.setFont(new Font("serif",Font.BOLD,30));
        daysText = new JLabel(String.valueOf(days));
        daysText.setFont(new Font("serif",Font.BOLD,30));
        
        JPanel remainingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;


        constraints.gridx = 0;
        constraints.gridy = 0;
        remainingPanel.add(daysLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        remainingPanel.add(daysText, constraints);

        
        calendarPanel = new JPanel();
        calendarPanel.setSize(new Dimension(600, 400));
        calendarPanel.add(calendar);
        // Create the event panel
        eventPanel = new JPanel();
        selectedDateLabel = new JLabel();
        selectedDateLabel.setFont(new Font("serif",Font.BOLD,20));
        String[] choices = {"Vacation", "Sick Leave"};
        eventField = new JComboBox(choices);
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Date dateC = calendar.getDate();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            	String date = sdf.format(dateC);
            	Date dC = new Date();
            	sdf = new SimpleDateFormat("yyyy-MM-dd");
            	String d = sdf.format(dC);
                String event = eventField.getSelectedItem().toString();
                if(date.compareTo(d)<=0){
                	JOptionPane.showMessageDialog(null, "Invalid date!");
                }
                else {
	                if (events.containsKey(date)) {
	                    int option = JOptionPane.showConfirmDialog(frame, "There is already an event for this day. Do you want to overwrite it?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	                    if (option == JOptionPane.YES_OPTION) {
	                        events.remove(date, event);
	                        events.put(date, event);
	                        try {
	                        	conn con = new conn();
	                        	String q = "update timeoff set type='"+event+"' where username='"+emp_mail+"' and date='"+date+"'";
	                        	con.st.executeUpdate(q);
	                        } catch(Exception ex){
	        	                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
	        	                ex.printStackTrace();
	        	            }
	                        updateSelectedDateLabel();
	                    }
	                }
	                else {
		                if(days>0) {
	                        events.put(date, event);
		                    try {
		                    	conn con = new conn();
		                    	String q = "insert into timeoff values('"+emp_mail+"', '"+date+"', '"+event+"')";
		                    	con.st.executeUpdate(q);
		                    	q = "update date set days='"+(days-1)+"' where username='"+emp_mail+"'";
		                        con.st.executeUpdate(q);
		                    } catch(Exception ex){
		    	                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
		    	                ex.printStackTrace();
		    	            }
		                    updateSelectedDateLabel();
		                	days--;
			                daysText.setText(String.valueOf(days));
		                }
		                else {
		                	JOptionPane.showMessageDialog(null, "You don't have any free days left!");
		                }	
	                }  
                }
                
                
            }
        });

        removeEventButton = new JButton("Remove Event");
        removeEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Date dateC = calendar.getDate();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            	String date = sdf.format(dateC);
            	Date dC = new Date();
            	sdf = new SimpleDateFormat("yyyy-MM-dd");
            	String d = sdf.format(dC);
            	if(date.compareTo(d)<=0){
                	JOptionPane.showMessageDialog(null, "Invalid date!");
                }
            	else
                {
            		if (events.containsKey(date)) {
	                	try {
	                    	conn con = new conn();
	                    	String q = "delete from timeoff where username='"+emp_mail+"' and date='"+date+"'";
	                    	con.st.executeUpdate(q);
	                    	q = "update date set days='"+(days+1)+"' where username='"+emp_mail+"'";
	                        con.st.executeUpdate(q);
	                    } catch(Exception ex){
	    	                JOptionPane.showMessageDialog(null, "Database error, could not delete from database", "Error", JOptionPane.ERROR_MESSAGE);
	    	                ex.printStackTrace();
	    	            }
	                    events.remove(date);
	                    updateSelectedDateLabel();
	                    days++;
	                    daysText.setText(String.valueOf(days));
            		}
	                else{
	                	JOptionPane.showMessageDialog(null, "There is no event on this date!");
	                }
                }
            }
        });
        
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
                new details_page(login_page.u);
            }
        });
        eventPanel.add(selectedDateLabel);
        eventPanel.add(eventField);
        eventPanel.add(addEventButton);
        eventPanel.add(removeEventButton);
        eventPanel.add(backButton);
        // Add the change listener to the calendar
        calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                updateSelectedDateLabel();
            }
        });
        // Add the calendar and event panel to the frame
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.01;
        constraints.weighty = 0.01;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        frame.add(remainingPanel, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        frame.add(calendarPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.SOUTH;
        frame.add(eventPanel, constraints);
        // Show the frame
        frame.setVisible(true);
    }

    /**
     * Functie ce actualizeaza data selectata
     */
    private void updateSelectedDateLabel() {
    	Date dateC = calendar.getDate();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String date = sdf.format(dateC);
        String event = "";
        if (events.containsKey(date)) {
            event = events.get(date);
        }
        selectedDateLabel.setText(date + " : " + event);
    }

    /**
	 * Functia de main
	 * @param args Argumentele pentru main
	 */
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {

			    long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				long sec = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
				long min = TimeUnit.MINUTES.convert(totalTime, TimeUnit.NANOSECONDS);
				long ora = TimeUnit.HOURS.convert(totalTime, TimeUnit.NANOSECONDS);
				System.out.println(sec + " secunde\n" + min + " minute\n" + ora + " ore");
		    }
		});
        new calendar_employee(login_page.u);
    }
}