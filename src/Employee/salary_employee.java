package Employee;


import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class salary_employee implements ActionListener{
    JFrame frame;
    JTextField textID;
    Font Font;
    JLabel labelID,labelName,labelLast,labelJob,labelSal, labelHours, labelPic,Placeholder1,Placeholder2,Placeholder3, Placeholder4, Placeholder5;
    JButton buttonSearch, buttonBack, buttonSalary;

    salary_employee(String emp_id){
        frame=new JFrame("Salary");
        frame.setBackground(Color.green);   
        frame.setLayout(null);
        
        Font = new Font("serif",Font.BOLD, 20);

        labelPic=new JLabel();
        labelPic.setBounds(0,0,500,500);
        labelPic.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/salary.jpg"));
        labelPic.setIcon(img);
        frame.add(labelPic);

        buttonBack=new JButton("Back");
        buttonBack.setBounds(200,400,100,30); 
        buttonBack.addActionListener(this);
        labelPic.add(buttonBack);

        labelName=new JLabel("First Name:"); 
       	labelName.setBounds(50,50,250,30);
        labelName.setForeground(Color.black);
        labelName.setFont(Font);
        labelPic.add(labelName);

        Placeholder1=new JLabel();    
        Placeholder1.setBounds(200,50,350,30);
        Placeholder1.setForeground(Color.black);
        Placeholder1.setFont(Font);
        labelPic.add(Placeholder1);

        labelLast=new JLabel("Last Name:");
       	labelLast.setBounds(50,100,250,30);
        labelLast.setForeground(Color.black);
        labelLast.setFont(Font);
        labelPic.add(labelLast);

        Placeholder2=new JLabel();
       	Placeholder2.setBounds(200,100,350,30);
        Placeholder2.setForeground(Color.black);
        Placeholder2.setFont(Font);
        labelPic.add(Placeholder2);

        labelJob=new JLabel("Job Position:");
        labelJob.setBounds(50,150,250,30);
        labelJob.setForeground(Color.black);
        labelJob.setFont(Font);
        labelPic.add(labelJob);

        Placeholder3=new JLabel();
        Placeholder3.setBounds(200,150,350,30);
        Placeholder3.setForeground(Color.black);
        Placeholder3.setFont(Font);
        labelPic.add(Placeholder3);
        
        labelSal=new JLabel("Salary:");
       	labelSal.setBounds(50,250,250,30);
        labelSal.setForeground(Color.black);
        labelSal.setFont(Font);
        labelPic.add(labelSal);

        Placeholder4=new JLabel();
       	Placeholder4.setBounds(200,250,350,30);
        Placeholder4.setForeground(Color.black);
        Placeholder4.setFont(Font);
        labelPic.add(Placeholder4);
        
        labelHours=new JLabel("Hours Worked:");
       	labelHours.setBounds(50,300,250,30);
        labelHours.setForeground(Color.black);
        labelHours.setFont(Font);
        labelPic.add(labelHours);
        
        Placeholder5=new JLabel();
        Placeholder5.setBounds(200,300,350,30);
        Placeholder5.setForeground(Color.black);
        Placeholder5.setFont(Font);
        labelPic.add(Placeholder5);
        
        buttonSalary=new JButton("Calculate Salary");
        buttonSalary.setBounds(150,200,200,30);
        buttonSalary.addActionListener(this);
        labelPic.add(buttonSalary);

        labelName.setVisible(false);
        labelLast.setVisible(false);
        labelJob.setVisible(false);
        labelSal.setVisible(false);
        labelHours.setVisible(false);
        buttonSalary.setVisible(false);
        
			String id;
			textID=new JTextField();
			textID.setVisible(false);
			try {
				conn con = new conn();
				String q = "select emp_id from employee where email='"+login_page.u+"'";
				ResultSet rs = con.st.executeQuery(q);
				if(rs.next()) {
					id=rs.getString(1);
					textID.setText(id);
				}
				q = "select name,fname,post from employee where emp_id='"+textID.getText()+"' ";
                ResultSet rs2 = con.st.executeQuery(q);
                if(rs2.next()){
                    String name  = rs2.getString(1); // col no. 1
                    String last   = rs2.getString(2); // col no. 2
                    String job = rs2.getString(3); // col no. 3

                    labelName.setVisible(true);
                    labelLast.setVisible(true);
                    labelJob.setVisible(true);
                    buttonSalary.setVisible(true);
                    Placeholder1.setText(name);
                    Placeholder2.setText(last);
                    Placeholder3.setText(job);
                }
			} catch(Exception ex) {
				ex.printStackTrace();
			}
        
        frame.setSize(500,500);
        frame.setLocation(500,250);  
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==buttonSearch){
            try{
                conn con = new conn();
                String q = "select name,fname,post from employee where emp_id='"+textID.getText()+"' ";
                ResultSet rs = con.st.executeQuery(q);

                int i=0;
                if(rs.next()){
                    String name  = rs.getString(1); // col no. 1
                    String last   = rs.getString(2); // col no. 2
                    String job = rs.getString(3); // col no. 3

                    labelName.setVisible(true);
                    labelLast.setVisible(true);
                    labelJob.setVisible(true);
                    buttonSalary.setVisible(true);
                    i=1;
                    Placeholder1.setText(name);
                    Placeholder2.setText(last);
                    Placeholder3.setText(job);
                }
                if(i==0)
                    JOptionPane.showMessageDialog(null,"Id not found!");
            }catch(Exception ex){}
        }
        
        if(ae.getSource()==buttonSalary){
            try {
	            conn con = new conn();
                String q = "select hours from date join employee on employee.email = date.username where emp_id='"+textID.getText()+"'";
	            ResultSet rs = con.st.executeQuery(q);
	            int val=0;
	            if(rs.next()) {
	            	val = rs.getInt(1);
	            }           
	        	q = "select sal from employee join salary on employee.post = salary.post where emp_id='"+textID.getText()+"'";
	            ResultSet rs2 = con.st.executeQuery(q);
	            
	            int i=0;
                if(rs2.next()){
                	Double sal = rs2.getDouble(1) * val;
                	i=1;
                	labelSal.setVisible(true);
                	labelHours.setVisible(true);
    	            Placeholder4.setText(String.valueOf(sal));
    	            Placeholder5.setText(String.valueOf(val));
    	            
                }
	            
	            if(i==0)
                    JOptionPane.showMessageDialog(null,"Id not found!");
            }catch(Exception ex){}
            
        }
        
        if(ae.getSource()==buttonBack){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
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
    	new salary_employee(login_page.u);
    }
    
}