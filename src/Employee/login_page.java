package Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;


/**
 * Fereastra unde se poate loga un user
 * @author Coruian Aurel-Ionut
 */
class login_page implements ActionListener {

	/**
	 * Variabila JFrame pentru fereastra
	 */
	JFrame frame;
	/**
	 * Variabila JLabel pentru a afisa textul pentru username
	 */
	JLabel labelUser;
	/**
	 * Variabila JLabel pentru a afisa textul pentru parola
	 */
	JLabel labelPass;
	/**
	 * Variabila JTextField pentru stocarea datelor introduse pentru username
	 */
	JTextField textUser;
	/**
	 * Variabila JTextField pentru stocarea datelor introduse pentru parola
	 */
	JPasswordField textPass;
	/**
	 * Variabila JButton pentru a te loga in aplicatie
	 */
	JButton butonLogin;
	/**
	 * Variabila JButton pentru a iesi din aplicatie
	 */
	JButton butonCancel;
	/**
	 * Variabila JButton pentru a schimba parola
	 */
	JButton butonPass;
	
	/**
	 * Variabila statica String folosita pentru a retine username-ul / emailul
	 */
	public static String u;
	/**
	 * Variabila statica int folosita pentru a retine statusul userului (0 - admin / 1 - angajat)
	 */
	public static int s=2;
	
	/**
	 * Constructor
	 */
	login_page(){
	
		frame = new JFrame("Login");
		frame.setBackground(Color.WHITE);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(380,230);
		
		labelUser = new JLabel("Username");
		labelUser.setBounds(40,20,100,30);
		frame.add(labelUser);		
		
		labelPass = new JLabel("Password");
		labelPass.setBounds(40,70,100,30);
		frame.add(labelPass);
		
		textUser = new JTextField();
		textUser.setBounds(150,20,150,30);
		frame.add(textUser);
				
		textPass = new JPasswordField();
		textPass.setBounds(150,70,150,30);
		frame.add(textPass);
		
		ImageIcon imageLoad = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
		Image imageGet = imageLoad.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT); // resize our image size.
		ImageIcon imageVar = new ImageIcon(imageGet);  // convert image into imageicon
		JLabel labelPic =  new JLabel(imageVar); // set imageicon on label
		labelPic.setBounds(350,20,150,150);
		frame.add(labelPic); // finally, add label on welcome frame.
		
		butonLogin = new JButton("Login");
		butonLogin.setBackground(Color.BLACK);
		butonLogin.setForeground(Color.WHITE);
		butonLogin.setBounds(40,140,120,30);
		butonLogin.setFont(new Font("serif",Font.BOLD,15));
		butonLogin.addActionListener(this);  // perform action on button click.
		frame.add(butonLogin);		
		
		butonCancel = new JButton("Cancel");
		butonCancel.setBackground(Color.BLACK);
		butonCancel.setForeground(Color.WHITE);
		butonCancel.setBounds(180,140,120,30);
		butonCancel.setFont(new Font("serif",Font.BOLD,15));
		butonCancel.addActionListener(this);  // perform action on button click.
		frame.add(butonCancel);	
		
		butonPass = new JButton("Change Password");
		butonPass.setBackground(Color.BLACK);
		butonPass.setForeground(Color.WHITE);
		butonPass.setBounds(70,180,200,30);
		butonPass.setFont(new Font("serif",Font.BOLD,15));
		butonPass.addActionListener(this);  // perform action on button click.
		frame.add(butonPass);
		
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setVisible(true);
		frame.setSize(600,300);
				
	}	

	/**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == butonCancel) {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(ae.getSource() == butonLogin) {
			try {
				conn con = new conn();
				u = textUser.getText();
				String p = textPass.getText();
				
				String q = "select * from login where username='"+u+"' and password='"+p+"' ";
				ResultSet rs = con.st.executeQuery(q); // used to retrieve data from database using conn.s.executeQuery()
				
				if(rs.next()) {  //used to match username and password
					s=0; //admin
					new details_page(u); // open details page and make visible also. 
					frame.dispose();// close login page
				}else {
					q = "select * from pass where email='"+u+"' and pass='"+p+"' ";
					ResultSet rs2 = con.st.executeQuery(q);
					if(rs2.next()) {
						s=1; //user
						new details_page(u); // open details page and make visible also. 
						frame.dispose();// close login page
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid login"); // when not matched.
						frame.dispose();// close login page
						login_page newLogin =  new login_page(); // open another
					}
				}
			}catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
		}
		
		if(ae.getSource() == butonPass) {	
			try {
					conn con = new conn();
					u = textUser.getText();					
					String q = "select * from login where username='"+u+"' ";
					ResultSet rs = con.st.executeQuery(q); // used to retrieve data from database using conn.s.executeQuery()
					
					if(rs.next()) {  //used to match username and password
						s=0;
					}
					if(s==2) {
						q = "select * from pass where email='"+u+"' ";
						ResultSet rs2 = con.st.executeQuery(q);
						if(rs2.next()) {
							s=1;
						}
					}
				
				}catch(Exception e){
	                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
	                e.printStackTrace();
	            }
				if(textUser.getText().isBlank() || s==2){
					JOptionPane.showMessageDialog(null, "Enter a valid admin username / employee email before changing password!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JDialog dialog = new JDialog();
				    JPanel panel = new JPanel(new GridLayout(0, 1));
				    panel.add(new JLabel("Old Password:"));
				    JPasswordField oldPass = new JPasswordField();
			        panel.add(oldPass);
			        panel.add(new JLabel("New Password:"));
			        JTextField newPass = new JTextField();
			        panel.add(newPass);
			        panel.add(new JLabel("New Password Again:"));
			        JTextField newPass2 = new JTextField();
			        panel.add(newPass2);
	
			        JButton okButton = new JButton("OK");
			        okButton.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent ae) {
			        		if(newPass.getText().equals(newPass2.getText())){
				        		try {
									conn con = new conn();
									u = textUser.getText();	
									String q = "";
									if(s==0) {
										q = "select password from login where username='"+u+"' and password='"+oldPass.getText()+"'";
										ResultSet rs = con.st.executeQuery(q);
										if(!rs.next()) {
											JOptionPane.showMessageDialog(null, "Database error, invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
											dialog.dispose();
										}
										q = "update login set password='"+newPass.getText()+"' where username='"+u+"' and password='"+oldPass.getText()+"' ";
									
									}
									else if(s==1) {
										q = "select pass from pass where email='"+u+"' and pass='"+oldPass.getText()+"'";
										ResultSet rs = con.st.executeQuery(q);
										if(!rs.next()) {
											JOptionPane.showMessageDialog(null, "Database error, invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
											dialog.dispose();
										}
										q = "update pass set pass='"+newPass.getText()+"' where email='"+u+"' and pass='"+oldPass.getText()+"' ";
									}
									con.st.executeUpdate(q); // used to retrieve data from database using conn.s.executeQuery()
									
									
								}catch(Exception e){
					                JOptionPane.showMessageDialog(null, "Database error, invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
					                e.printStackTrace();
					            }
				        		dialog.setVisible(false);
				        		}
			        		else {
			        			JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE); 
			        		}
			        		
			        	}
			        	
			        });
			        JButton cancelButton = new JButton("Cancel");
			        cancelButton.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent ae) {
			        		dialog.setVisible(false);
			        	}
			        });
	
			        JPanel buttonPanel = new JPanel();
			        buttonPanel.add(okButton);
			        buttonPanel.add(cancelButton);
			        
			        panel.add(buttonPanel);
			        
			        dialog.setContentPane(panel);
			        if(s==0)
			        	dialog.setTitle("Admin");
			        else
			        	dialog.setTitle("User");
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		        
		    }
		}
		
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
		login_page newLogin =  new login_page();
	}
	
}
