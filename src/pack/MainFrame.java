package pack;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.Clock;

import javax.swing.*;

public class MainFrame{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w=(int) (screenSize.getWidth());
	int h=(int) (screenSize.getHeight());
	boolean cv=false, rez=false;
	
	JFrame frame = new JFrame();
	JPanel panelcv = new JPanel(new BorderLayout());
	JPanel panelrez = new JPanel(new BorderLayout());
	JPanel panelang = new JPanel(new BorderLayout());
	JLabel lblnull = new JLabel();
	JLabel lblcv = new JLabel("Incarcare CV");
	JLabel lblrez = new JLabel("Rezultat:");
	JLabel lblang = new JLabel("Esti angajat!");
	JLabel rezfin = new JLabel("In asteptare...");
	JButton btncv = new JButton("Incarca");
	JButton btnrez = new JButton("Continua");
	
	public MainFrame(int i){
	
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Connected! (setare cv)");
		    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from stare");
			while(rs.next()) {
				if(rs.getInt(1)==0) {
					cv=false;
				}
				else {
					cv=true;
				}
				if(rs.getInt(2)==0) {
					rez=false;
				}
				else {
					rez=true;
				}
			}
			System.out.println("Disconnected! (setare cv)");
			con.close();
		} catch (SQLException ex) {
			throw new Error("Error ", ex);
		} catch(ClassNotFoundException ex) {
		  	throw new Error("Error ", ex);
		}
		
		lblcv.setBounds(0,0,200,50);
		lblcv.setFont(new Font(null, Font.PLAIN, 25));
		
		btncv.setBounds(15,50,100,50);
		btncv.setFocusable(false);
		btncv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    System.out.println("Connected! (in cv)");
				    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
					String sql="update stare set cv=1 where cv=0";
				    PreparedStatement stmt = con.prepareStatement(sql);
					stmt.executeUpdate();
					System.out.println("Disconnected! (in cv)");
					con.close();
				} catch (SQLException ex) {
					throw new Error("Error ", ex);
				} catch(ClassNotFoundException ex) {
				  	throw new Error("Error ", ex);
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Connected! (dupa cv)");
				    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
				    Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from login where idLOGIN='"+i+"'");
					String usr="", pass="";
					while(rs.next()) {
					    usr=rs.getString(2);
						pass=rs.getString(3);
						break;
					}
				    String sql = "update stare join login set LOGINcv=cv where LOGINuser='"+usr+"' and LOGINpass='"+pass+"'";
				    PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set LOGINrez=rez where LOGINuser='"+usr+"' and LOGINpass='"+pass+"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set cv=0";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set rez=0";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					System.out.println("Disconnected! (dupa cv)");
					con.close();
				} catch (SQLException ex) {
					throw new Error("Error ", ex);
				} catch(ClassNotFoundException ex) {
				  	throw new Error("Error ", ex);
				}
				
				frame.dispose();
				JOptionPane.showMessageDialog(null, "CV incarcat cu succes!", "Success!", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		panelcv.setSize(w, h);
		panelcv.add(lblcv);
		panelcv.add(btncv);
		panelcv.add(lblnull);	
			
		lblrez.setBounds(0,0,100,50);
		lblrez.setFont(new Font(null, Font.PLAIN, 25));		
		
		lblcv.setBounds(0,0,200,50);
		lblcv.setFont(new Font(null, Font.PLAIN, 25));
		rezfin.setBounds(100,0,200,50);
		rezfin.setFont(new Font(null, Font.PLAIN, 25));
		rezfin.setForeground(Color.yellow);
		Timer timer = new Timer(3000, new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent arg0) {
	        	rezfin.setText("Gata!");
	        	rezfin.setForeground(Color.green);
	        	btnrez.setVisible(true);
			}
		});
		timer.setRepeats(false);
		timer.start();
		
		btnrez.setBounds(15,50,100,50);
		btnrez.setFocusable(false);
		btnrez.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    System.out.println("Connected! (in cv rez)");
				    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
				    String sql="update stare set rez=1 where rez=0";
				    PreparedStatement stmt = con.prepareStatement(sql);
					stmt.executeUpdate();	
				    JOptionPane.showMessageDialog(null, "Bine ai venit!", "Success!", JOptionPane.PLAIN_MESSAGE);
					panelrez.setVisible(false);
				    frame.setVisible(false);
				    Timer timer = new Timer(3000, new ActionListener() {
				        @Override
						public void actionPerformed(ActionEvent arg0) {
				        	panelang.setVisible(true);
							frame.setVisible(true);
						}
					});
					timer.setRepeats(false);
					timer.start();
					System.out.println("Disconnected! (in cv rez)");
					con.close();
				} catch (SQLException ex) {
					throw new Error("Error ", ex);
				} catch(ClassNotFoundException ex) {
				  	throw new Error("Error ", ex);
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Connected! (dupa cv rez)");
				    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema", "root", "superman12");
				    Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from login where idLOGIN='"+i+"'");
					String usr="", pass="";
					while(rs.next()) {
					    usr=rs.getString(2);
						pass=rs.getString(3);
						break;
					}
				    String sql = "update stare join login set LOGINcv=cv where LOGINuser='"+usr+"' and LOGINpass='"+pass+"'";
				    PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set LOGINrez=rez where LOGINuser='"+usr+"' and LOGINpass='"+pass+"'";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set cv=0";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "update stare join login set rez=0";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					System.out.println("Disconnected! (dupa cv rez)");
					con.close();
				} catch (SQLException ex) {
					throw new Error("Error ", ex);
				} catch(ClassNotFoundException ex) {
				  	throw new Error("Error ", ex);
				}
			}
		});
		btnrez.setVisible(false);
			
		panelrez.setSize(w, h);
		panelrez.add(lblrez);
		panelrez.add(rezfin);
		panelrez.add(btnrez);
		panelrez.add(lblnull);
		
		lblang.setBounds(0,0,250,50);
		lblang.setFont(new Font(null, Font.PLAIN, 25));
		lblang.setForeground(Color.blue);
		
		panelang.setSize(w, h);
		panelang.add(lblang);
		panelang.add(lblnull);
		
		frame.add(panelcv);
		frame.add(panelrez);
		frame.add(panelang);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w, h);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		if(!cv && !rez) {
			panelcv.setVisible(true);			
		}
		else if(cv && !rez) {
			panelcv.setVisible(false);
			panelrez.setVisible(true);
		}
		else if(cv && rez) {
			panelcv.setVisible(false);
			panelrez.setVisible(false);
			panelang.setVisible(true);
		}
		
		frame.setVisible(true);
	
	}
}
