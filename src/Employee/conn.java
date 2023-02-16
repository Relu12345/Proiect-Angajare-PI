package Employee;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * Clasa folosita pentru a face conexiunea la baza de date
 * @author Coruian Aurel-Ionut
 */
public class conn {

	/**
	 * Variabila Connection pentru a incepe conexiunea cu baza de date MySQL
	 */
	public Connection c;  // used to set up connection with mysql
	/**
	 * Variabila Statement pentru a executa query-uri din MySQL
	 */
	public Statement st;  // used to execute all queries of mysql
	
	/**
	 * Constructor
	 */
	public conn() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load mysql jdbc driver
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC","root","superman12");
			st = c.createStatement(); // helpful to execute query
		} catch(Exception e){
            JOptionPane.showMessageDialog(null, "Connection error, could not make connection", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
	}
	
}
