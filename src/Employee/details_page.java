package Employee;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Fereastra principala a aplicatiei
 * @author Coruian Aurel-Ionut
 */
public class details_page implements ActionListener {

	/**
	 * Variabila JFrame pentru fereastra
	 */
	JFrame frame;
	/**
	 * Variabila JLabel pentru a seta imaginea ferestrei
	 */
	JLabel imagine;
	/**
	 * Variabila JLabel pentru a afisa textul pentru titlul cu detalii
	 */
	JLabel titlu;
	/**
	 * Variabila JLabel pentru a afisa textul pentru titlul de management
	 */
	JLabel titlu2;
	/**
	 * Variabila JButton pentru a adauga un angajat
	 */
	JButton buttonAdd;
	/**
	 * Variabila JButton pentru a adauga un admin
	 */
	JButton buttonAddAdmin;
	/**
	 * Variabila JButton pentru a vizualiza datele unui angajat
	 */
	JButton buttonView;
	/**
	 * Variabila JButton pentru a concedia un angajat
	 */
	JButton buttonRemove;
	/**
	 * Variabila JButton pentru a actualiza datele unui angajat
	 */
	JButton buttonUpdate;
	/**
	 * Variabila JButton pentru a vizualiza datele despre salariu
	 */
	JButton buttonSalary;
	/**
	 * Variabila JButton pentru a vizualiza calendarul pentru zile libere
	 */
	JButton buttonCalendar;
	/**
	 * Variabila JButton pentru a te deloga
	 */
	JButton buttonLog;
	
	/**
	 * Variabila statica int pentru a nu se reseta orele lucrate de fiecare data cand se revine la aceasta pagina 
	 */
	public static int ok=0;	
	
	/**
     * Constructor
     * @param emp_id Preia de la login username-ul adminului
     */
	details_page(String emp_id){
		Date d = new Date();
		if(d.getDate()==1 && login_page.s == 1 && ok==0) {
			ok=1;
			new date_employee(login_page.u);
		}
		
		frame = new JFrame("Employee Dashboard");
		if(login_page.s == 0) {
			frame.setTitle("Employee Dashboard (Admin)");
		}
		frame.setBackground(Color.WHITE);
		frame.setLayout(null);
		
		imagine = new JLabel();
		imagine.setBounds(0,0,700,500);
		imagine.setLayout(null);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
		imagine.setIcon(i1);
				
		titlu = new JLabel("Employee Details");
		titlu.setBounds(430,20,200,40);
		titlu.setFont(new Font("serif",Font.BOLD,25));
		titlu.setForeground(Color.BLUE);
		titlu.setLayout(null);
		frame.add(titlu);
		
		if(login_page.s == 1) {
			titlu2 = new JLabel("Management");
			titlu2.setBounds(60,20,200,40);
			titlu2.setFont(new Font("serif",Font.BOLD,25));
			titlu2.setForeground(Color.BLUE);
			titlu2.setLayout(null);
			frame.add(titlu2);
		}
		
		buttonAdd = new JButton("Add");
		buttonAdd.setBounds(420,80,90,30);
		buttonAdd.setBackground(Color.pink);
		buttonAdd.setFont(new Font("serif",Font.BOLD,15));
		buttonAdd.addActionListener(this);  // perform action on button click.
		if(login_page.s == 0) {
			frame.add(buttonAdd);
		}
		
		buttonAddAdmin = new JButton("Add Admin");
		buttonAddAdmin.setBounds(465,200,110,30);
		buttonAddAdmin.setBackground(Color.pink);
		buttonAddAdmin.setFont(new Font("serif",Font.BOLD,15));
		buttonAddAdmin.addActionListener(this);  // perform action on button click.
		if(login_page.s == 0) {
			frame.add(buttonAddAdmin);
		}
		
		buttonView = new JButton("View");
		buttonView.setBounds(540,80,90,30);
		if(login_page.s == 1) {
			buttonView.setBounds(470,80,90,30);
		}
		buttonView.setBackground(Color.pink);
		buttonView.setFont(new Font("serif",Font.BOLD,15));
		buttonView.addActionListener(this);  // perform action on button click.
		frame.add(buttonView);
		
		buttonRemove = new JButton("Remove");
		buttonRemove.setBounds(420,140,90,30);
		buttonRemove.setBackground(Color.pink);
		buttonRemove.setFont(new Font("serif",Font.BOLD,15));
		buttonRemove.addActionListener(this);  // perform action on button click.
		if(login_page.s == 0) {
			frame.add(buttonRemove);
		}
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(540,140,90,30);
		buttonUpdate.setBackground(Color.pink);
		buttonUpdate.setFont(new Font("serif",Font.BOLD,15));
		buttonUpdate.addActionListener(this);  // perform action on button click.
		if(login_page.s == 0) {
			frame.add(buttonUpdate);
		}
		if(login_page.s == 1) {
			buttonSalary = new JButton("Salary");
			buttonSalary.setBounds(20,80,90,30);
			buttonSalary.setBackground(Color.pink);
			buttonSalary.setFont(new Font("serif",Font.BOLD,15));
			buttonSalary.addActionListener(this);  // perform action on button click.
			frame.add(buttonSalary);
			
			buttonCalendar = new JButton("Time Off");
			buttonCalendar.setBounds(130,80,100,30);
			buttonCalendar.setBackground(Color.pink);
			buttonCalendar.setFont(new Font("serif",Font.BOLD,15));
			buttonCalendar.addActionListener(this);  // perform action on button click.
			frame.add(buttonCalendar);
		}
		
		buttonLog = new JButton("Log Out");
		buttonLog.setBounds(540,420,100,30);
		buttonLog.setBackground(Color.pink);
		buttonLog.setFont(new Font("serif",Font.BOLD,15));
		buttonLog.addActionListener(this);  // perform action on button click.
		frame.add(buttonLog);
		
		frame.add(imagine);
		frame.setVisible(true);
		frame.setSize(700,500);
		frame.setLocation(450,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == buttonAdd) {
			frame.dispose();
			new add_employee(login_page.u);
		}
		if(ae.getSource() == buttonAddAdmin) {
			frame.dispose();
			new add_admin(login_page.u);
		}
		else if(ae.getSource() == buttonView) {
			frame.dispose();
			if(login_page.s == 1) {
				String id;
				try {
					conn con = new conn();
					String q = "select emp_id from employee where email='"+login_page.u+"'";
					ResultSet rs = con.st.executeQuery(q);
					if(rs.next()) {
						id=rs.getString(1);
						new print_data(id);
					}
				} catch(Exception e){
	                JOptionPane.showMessageDialog(null, "Database error, could not get identificator for user", "Error", JOptionPane.ERROR_MESSAGE);
	                e.printStackTrace();
	            }
			}
			else {
				new view_employee(login_page.u);
			}
		}
		else if(ae.getSource() == buttonRemove) {
			frame.dispose();
			new remove_employee(login_page.u);
		}
		else if(ae.getSource() == buttonUpdate) {
			frame.dispose();
			new search_employee(login_page.u);
		}
		
		if(ae.getSource() == buttonSalary) {
			frame.dispose();
			new salary_employee(login_page.u);
		}
		
		if(ae.getSource() == buttonCalendar) {
			frame.dispose();
			new calendar_employee(login_page.u);
		}
		
		if(ae.getSource() == buttonLog) {
			frame.dispose();
			new welcome_page();
		}
		
	}
	
	/**
	 * Functia de main
	 * @param args Argumentele pentru main
	 */
	public static void main(String [] args) {
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
		details_page newDetails = new details_page(login_page.u);
	}
	
}
