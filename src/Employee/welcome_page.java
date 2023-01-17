package Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

class welcome_page implements ActionListener {
	
	JFrame frame;
	JButton buton;
	
	
	welcome_page(){
	
		frame = new JFrame("EMS");
		frame.setBackground(Color.red);
		frame.setLayout(null); // absolute layout used
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		ImageIcon imageLoad = new ImageIcon(ClassLoader.getSystemResource("icon/welcome.jpg"));
		Image imageGet = imageLoad.getImage().getScaledInstance(1050, 650, Image.SCALE_DEFAULT); // resize our image size.
		ImageIcon imageVar = new ImageIcon(imageGet);  // convert image into imageicon
		
		JLabel labelPic =  new JLabel(imageVar); // set imageicon on label
		labelPic.setBounds(30,140,1165,430);
		
		JButton buton = new JButton("Click Here To Continue");
		buton.setBackground(Color.DARK_GRAY);
		buton.setForeground(Color.WHITE);
		buton.setBounds(525,530,180,40);
		buton.addActionListener(this);  // perform action on button click.
			
		JLabel labelFormat = new JLabel();
		labelFormat.setBounds(0,0,1165,650); // keep length as image length.
		labelFormat.setLayout(null);	
		
		JLabel labelWelcome = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
		labelWelcome.setBounds(80,30,1140,100);
		labelWelcome.setFont(new Font("Serif",Font.PLAIN,63));
		labelFormat.add(labelWelcome);
		
		labelFormat.add(buton);
		frame.add(labelFormat);
		frame.add(labelPic); // finally, add label on welcome frame.
		
		frame.getContentPane().setBackground(Color.WHITE); // set frame background color
		
		frame.setVisible(true);
		frame.setSize(1260,650);
		frame.setLocation(50,50); // setting on window location 	
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		frame.dispose();
		new login_page();  // open login page on button click	
	}
	
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
		welcome_page newWelcome = new welcome_page();
	}
	
}
