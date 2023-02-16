package Employee;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 * Clasa folosita pentru a reseta orele lucrate in luna curenta si unde se adauga zilele libere 
 * @author Coruian Aurel-Ionut
 */
public class date_employee {
	
	/**
	 * Variabila String pentru a stoca un query MySQL
	 */
	String q;
	/**
	 * Variabila ResultSet pentru a selecta numarul de zile libere pentru un angajat folosind query-ul "q"
	 */
	ResultSet rs;
	/**
     * Constructor
     * @param emp_id Preia de la login emailul angajatului
     */
	date_employee(String emp_id){
		try {
			conn con = new conn();
			int d = 0;
			q = "select days from date where username='"+emp_id+"'";
			rs = con.st.executeQuery(q);
			if(rs.next()) {
				d=rs.getInt(1);
			}
			q = "update date set hours=0, minutes=0, days='"+(d+2)+"' where username='"+emp_id+"'";
			con.st.executeUpdate(q);
		}catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database error, could not update free days of employee", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
	}
}
