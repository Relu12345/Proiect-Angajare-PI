package pack;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

/**
 * @author corui
 *
 */
public class LoginFrame{
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(new BorderLayout());
	JButton btnlogin = new JButton("Login");
	JButton btnregister = new JButton("Register");
	JLabel lbllogin = new JLabel("Bun venit!");
	JTextField txtusr = new JTextField();
	JLabel lblusr = new JLabel("User");
	JPasswordField txtpass = new JPasswordField();
	JLabel lblpass = new JLabel("Password");
	JLabel lblnull = new JLabel();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w=(int) (screenSize.getWidth());
	int h=(int) (screenSize.getHeight());
	boolean ok=false;
	
	
	LoginFrame(){
		btnlogin.setBounds(15,150,100,20);
		btnlogin.setFocusable(false);
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ok=false;
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    System.out.println("Connected! (la apasare login)");
				    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from login");
					while(rs.next()) {
						int i=rs.getInt(1);
						String usr=rs.getString(2);
						String pass=rs.getString(3);
						String usr1=txtusr.getText();
						String pass1 = txtpass.getText();
						if(!usr1.equals("") && !pass1.equals("")) {
							System.out.println("user db: " + usr);
							System.out.println("user aici: " + usr1);
							System.out.println("parola db: " + pass);
							System.out.println("parola aici: " + pass1);
							if(usr.equals(usr1) && pass.equals(pass1)) {
								String sql = "update stare join login set cv=LOGINcv where LOGINuser='"+txtusr.getText()+"' and LOGINpass='"+txtpass.getText()+"'";
								PreparedStatement pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();
								sql = "update stare join login set rez=LOGINrez where LOGINuser='"+txtusr.getText()+"' and LOGINpass='"+txtpass.getText()+"'";
								pstmt = con.prepareStatement(sql);
								pstmt.executeUpdate();
								ok=true;
								frame.dispose();
								System.out.println("Disconnected! (la apasare login)");
								con.close();
								MainFrame Nframe = new MainFrame(i);
								break;
							}
						}
						else {
							break;
						}
						
					}
					if(!ok) {
						System.out.println("Disconnected! (la apasare login gresit)");
						con.close();
						JOptionPane.showMessageDialog(null, "User sau parola gresita!", "Error", JOptionPane.ERROR_MESSAGE);
						frame.dispose();
						LoginFrame Nframe = new LoginFrame();
					}
				} catch (SQLException ex) {
					throw new Error("Error ", ex);
				} catch(ClassNotFoundException ex) {
				  	throw new Error("Error ", ex);
				}
				
			}
		});
		
		btnregister.setBounds(145,150,100,20);
		btnregister.setFocusable(false);
		btnregister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtusr.getText().equals("") && !txtpass.getText().equals("")) {
					try {
					    Class.forName("com.mysql.cj.jdbc.Driver");
					    System.out.println("Connected!");
					    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
					    String search = "SELECT MAX(idLOGIN) FROM LOGIN";
					    Statement stmt = con.createStatement();
					    ResultSet rs = stmt.executeQuery(search);
					    int i=0;
					    while(rs.next()) {
					    	i=rs.getInt(1);
					    }
					    System.out.println(i);
					    String sql = "INSERT INTO LOGIN " + "VALUES ('"+(i+1)+"', '"+txtusr.getText()+"', '"+txtpass.getText()+"', 0, 0)";
					    stmt.executeUpdate(sql);
					    con.close();
						JOptionPane.showMessageDialog(null, "User-ul si parola au fost inregistrate!", "Success", JOptionPane.PLAIN_MESSAGE);
						frame.dispose();
						LoginFrame Nframe = new LoginFrame();
					} catch (SQLException ex) {
						throw new Error("Error ", ex);
					} catch(ClassNotFoundException ex) {
					  	throw new Error("Error ", ex);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "User sau parola nu sunt introduse corect!", "Error", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
					LoginFrame Nframe = new LoginFrame();
				}
			}
		});
		
		
		lbllogin.setBounds(80,0,200,50);
		lbllogin.setFont(new Font(null, Font.PLAIN, 25));
		
		txtusr.setBounds(60,57,150,20);
		
		lblusr.setBounds(0,40,100,50);
		lblusr.setFont(new Font(null, Font.PLAIN, 12));
		
		txtpass.setBounds(60,87,150,20);
		
		lblpass.setBounds(0,70,100,50);
		lblpass.setFont(new Font(null, Font.PLAIN, 12));
		
		panel.add(btnlogin);
		panel.add(btnregister);
		panel.add(lbllogin);
		panel.add(lblusr);
		panel.add(txtusr);
		panel.add(lblpass);
		panel.add(txtpass);
		panel.add(lblnull);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(280,250);
		int x = (int) ((w - frame.getWidth()) / 2);
	    int y = (int) ((h - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
		panel.setSize(280, 250);
		panel.setBackground(new Color(0, 100, 200));
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}	
}
