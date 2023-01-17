package Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;


class login_page implements ActionListener {

	JFrame frame;
	JLabel labelUser, labelPass;
	JTextField textUser;
	JPasswordField textPass;
	JButton butonLogin, butonCancel;
	
	public static String u;
	public static int s;
	
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
		
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setVisible(true);
		frame.setSize(600,300);
				
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == butonCancel) {
			frame.dispose();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if(e.getSource() == butonLogin) {
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
					q = "select email from employee where email='"+u+"'";
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
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
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
