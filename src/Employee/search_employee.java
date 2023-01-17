package Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

// THIS CODE IS SIMILAR TO VIEW_EMPLOYEE CODE, THEY NEEDS TO BE SEPARATE TO DIFFERENTIATE BETWEEN CLASSES! 

class search_employee implements ActionListener{

    JFrame frame;
    JTextField textID;
    JLabel labelPic,labelID;
    JButton butonSearch,butonCancel;

    search_employee(String emp_id){
        frame=new JFrame("Search");
        frame.setBackground(Color.green);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelPic=new JLabel();
        labelPic.setBounds(0,0,500,270);
        labelPic.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/view.jpg"));
        labelPic.setIcon(img);

        labelID=new JLabel("Employee Id");
        labelID.setVisible(true);
        labelID.setBounds(40,60,250,30);
        labelID.setForeground(Color.white);
        Font Font1 = new Font("serif",Font.BOLD,30);
        labelID.setFont(Font1); 
        labelPic.add(labelID);
        frame.add(labelPic);

        textID=new JTextField();
        textID.setFont(new Font("serif",Font.BOLD,17));
        textID.setBounds(240,60,220,30);
        labelPic.add(textID);

        butonSearch=new JButton("Search");
        butonSearch.setBounds(240,150,100,30);
        butonSearch.addActionListener(this);
        labelPic.add(butonSearch);

        butonCancel=new JButton("Cancel");
        butonCancel.setBounds(360,150,100,30);
        butonCancel.addActionListener(this);
        labelPic.add(butonCancel);

        frame.setSize(500,270);
        frame.setLocation(450,250);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==butonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
        if(ae.getSource()==butonSearch){
        	frame.dispose();
            String emp_id = textID.getText();
            update_employee newUpdate=new update_employee(emp_id);
        }

    }

    public static void main(String[]ar){
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
    	search_employee newSearch=new search_employee(login_page.u);
    }
}