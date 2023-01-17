package Employee;

import java.awt.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.awt.event.*;

class update_employee  implements ActionListener{

    JFrame frame;
    JLabel id,labelName,labelLast,labelAddress,labelPhone,labelEmail,labelEducation,labelJob,labelWelcome,labelCNP,labelPic;
    JTextField textName,textLast,textAddress,textPhone,textEmail,textEducation,textCNP;
	JComboBox textJob;
    JButton buttonUpdate,buttonCancel; 
    String id_emp;

    update_employee(String e_id){
        
        frame=new JFrame("Update Employee details");
        frame.setVisible(false);
        frame.setSize(900,500);
        frame.setLocation(450,250);
        frame.setBackground(Color.white);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        id_emp=e_id;    
        labelPic=new JLabel();
        labelPic.setBounds(0,0,900,500);
        labelPic.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        labelPic.setIcon(img);

        labelWelcome = new JLabel("Update Employee Detail:");
        labelWelcome.setBounds(50,10,500,50);
        labelWelcome.setFont(new Font("serif",Font.ITALIC,40));
        labelWelcome.setForeground(Color.black);
        labelPic.add(labelWelcome);
        frame.add(labelPic);

        labelName = new JLabel("First Name:");  
        labelName.setBounds(50,100,200,30);
        labelName.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelName);

        textName=new JTextField();
        textName.setBounds(200,100,150,30);
        labelPic.add(textName);

        labelLast = new JLabel("Last Name:");
        labelLast.setBounds(400,100,200,30);
        labelLast.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelLast);

        textLast=new JTextField();
        textLast.setBounds(600,100,150,30);
        labelPic.add(textLast);

        labelAddress= new JLabel("Address:");
        labelAddress.setBounds(50,150,100,30);
        labelAddress.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelAddress);

        textAddress=new JTextField();
        textAddress.setBounds(200,150,150,30);
        labelPic.add(textAddress);

        labelPhone= new JLabel("Phone:");
        labelPhone.setBounds(400,150,100,30);
        labelPhone.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelPhone);

        textPhone=new JTextField();
        textPhone.setBounds(600,150,150,30);   
        labelPic.add(textPhone);

        labelEmail= new JLabel("Email:");
        labelEmail.setBounds(50,200,100,30);
        labelEmail.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelEmail);

        textEmail=new JTextField();
        textEmail.setBounds(200,200,150,30);
        labelPic.add(textEmail);

        labelEducation= new JLabel("Education:");
        labelEducation.setBounds(400,200,100,30);
        labelEducation.setFont(new Font("serif",Font.BOLD,20));    
        labelPic.add(labelEducation);

        textEducation=new JTextField();
        textEducation.setBounds(600,200,150,30);
        labelPic.add(textEducation);

        labelJob= new JLabel("Job Position:");
        labelJob.setBounds(50,250,150,30);
        labelJob.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelJob);

        String[] selectiiJob = {"Student Worker", "Junior Developer", "Developer", "Senior Developer"};
        textJob=new JComboBox(selectiiJob);
        textJob.setBounds(200,250,150,30);
        labelPic.add(textJob);

        labelCNP= new JLabel("CNP:");
        labelCNP.setBounds(400,250,100,30);  
        labelCNP.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelCNP);

        textCNP=new JTextField();
        textCNP.setBounds(600,250,150,30);
        labelPic.add(textCNP);

        buttonUpdate=new JButton("Update");
        buttonUpdate.setBounds(250,400,100,30);
        buttonUpdate.addActionListener(this);
        labelPic.add(buttonUpdate);

        buttonCancel=new JButton("Cancel");
        buttonCancel.setBounds(450,400,100,30);
        buttonCancel.addActionListener(this);
        labelPic.add(buttonCancel);

        showData(e_id);
    }

    int i=0;
    String age,dat;

    void showData(String id){
        try{
            conn con = new conn();
            String q = "select * from employee where emp_id = '"+id+"'";
            ResultSet rs = con.st.executeQuery(q); // whole row stored in rs

            if(rs.next()){
                frame.setVisible(true);
                i=1;

                // put data from database to text field.
                textName.setText(rs.getString(2));
                textLast.setText(rs.getString(3));
                textAddress.setText(rs.getString(6));
                textPhone.setText(rs.getString(7));
                textEmail.setText(rs.getString(8));
                textEducation.setText(rs.getString(9));
                textJob.setSelectedItem(rs.getString(10));
                textCNP.setText(rs.getString(11));

                age=rs.getString(4);
                dat=rs.getString(5);
            }
            if(i==0) {
                JOptionPane.showMessageDialog(null,"Id not found!");
                frame.dispose();
                new search_employee(login_page.u);
            }
            else
            {
            	frame.setVisible(true);
            }
        }catch(Exception ex){}
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==buttonUpdate && i==1){
            try{
                conn con = new conn();
                String q = "update employee set name='"+textName.getText()+"',fname='"+textLast.getText()+"',age='"+age+"',dob='"+dat+"',address='"+textAddress.getText()+"',phone='"+textPhone.getText()+"',email='"+textEmail.getText()+"',education='"+textEducation.getText()+"',post='"+textJob.getSelectedItem().toString()+"',cnp='"+textCNP.getText()+"' where emp_id='"+id_emp+"'";
                con.st.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Successfully updated!");
                frame.dispose();
                new details_page(login_page.u);
        		System.out.println(login_page.u);
            }catch(Exception e){
                System.out.println("The error is:"+e);
            }
        }
        if(ae.getSource()==buttonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
    }
    
    public static void main(String[] arg){
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
    	new update_employee("");
    }
    
}