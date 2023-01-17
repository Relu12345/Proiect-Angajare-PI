package Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

class view_employee implements ActionListener{

    JFrame frame;
    JTextField textID;
    JLabel labelPic,labelID;
    JButton buttonSearch,buttonCancel;

    view_employee(String emp_id){
        frame=new JFrame("View");
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

        buttonSearch=new JButton("Search");
        buttonSearch.setBounds(240,150,100,30);
        buttonSearch.addActionListener(this);
        labelPic.add(buttonSearch);

        buttonCancel=new JButton("Cancel");
        buttonCancel.setBounds(360,150,100,30);
        buttonCancel.addActionListener(this);
        labelPic.add(buttonCancel);

        frame.setSize(500,270);
        frame.setLocation(450,250);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==buttonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
        if(ae.getSource()==buttonSearch){
        	frame.dispose();
            print_data newPrint=new print_data(textID.getText());
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
    	view_employee v=new view_employee(login_page.u);
    }
}