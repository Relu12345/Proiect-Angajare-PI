package Employee;

import java.sql.ResultSet;

public class date_employee {

	date_employee(String emp_id){
		try {
			conn con = new conn();
			int d = 0;
			String q = "select days from date where username='"+emp_id+"'";
			ResultSet rs = con.st.executeQuery(q);
			if(rs.next()) {
				d=rs.getInt(1);
			}
			q = "update date set hours=0, minutes=0, days='"+(d+2)+"' where username='"+emp_id+"'";
			con.st.executeUpdate(q);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
