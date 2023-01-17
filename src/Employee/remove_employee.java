package Employee;


import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class remove_employee implements ActionListener{
    JFrame frame;
    JTextField textID;
    JLabel labelID,labelName,labelPhone,labelEmail,labelPic,Placeholder1,Placeholder2,Placeholder3;
    JButton butonSearch,butonRemove,butonCancel,butonBack;

    remove_employee(String emp_id){
        frame=new JFrame("Remove Employee");
        frame.setBackground(Color.green);   
        frame.setLayout(null);

        labelPic=new JLabel();
        labelPic.setBounds(0,0,500,500);
        labelPic.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/remove.jpg"));
        labelPic.setIcon(img);
        frame.add(labelPic);
        
        labelID=new JLabel("Employee Id");
        labelID.setBounds(50,50,250,30);
        labelID.setForeground(Color.white);
        Font Font2 = new Font("serif",Font.BOLD,25); 
        labelID.setFont(Font2);
        labelPic.add(labelID);

        textID=new JTextField();
        textID.setBounds(250,50,150,30);
        labelPic.add(textID);

        butonSearch=new JButton("Search");
        butonSearch.setBounds(200,100,100,30);
        butonSearch.addActionListener(this);
        labelPic.add(butonSearch);

        butonBack=new JButton("Back");
        butonBack.setBounds(360,100,100,30);   
        butonBack.addActionListener(this);
        labelPic.add(butonBack);

        labelName=new JLabel("First Name:"); 
        labelName.setBounds(50,150,250,30);
        labelName.setForeground(Color.white);
        Font Font3 = new Font("serif",Font.BOLD,20); 
        labelName.setFont(Font3);
        labelPic.add(labelName);

        Placeholder1=new JLabel();    
        Placeholder1.setBounds(200,150,350,30);
        Placeholder1.setForeground(Color.white);
        Font Font6=new Font("serif",Font.BOLD,20); 
        Placeholder1.setFont(Font6);
        labelPic.add(Placeholder1);

        labelPhone=new JLabel("Phone:");
        labelPhone.setBounds(50,200,250,30);
        labelPhone.setForeground(Color.white);
        Font Font4 = new Font("serif",Font.BOLD,20); 
        labelPhone.setFont(Font4);
        labelPic.add(labelPhone);

        Placeholder2=new JLabel();
        Placeholder2.setBounds(200,200,350,30);
        Placeholder2.setForeground(Color.white);
        Font Font7=new Font("serif",Font.BOLD,20); 
        Placeholder2.setFont(Font7);
        labelPic.add(Placeholder2);

        labelEmail=new JLabel("Email:");
        labelEmail.setBounds(50,250,250,30);
        labelEmail.setForeground(Color.white);
        Font Font5=new Font("serif",Font.BOLD,20); 
        labelEmail.setFont(Font5);
        labelPic.add(labelEmail);

        Placeholder3=new JLabel();
        Placeholder3.setBounds(200,250,350,30);
        Placeholder3.setForeground(Color.white);
        Font Font8=new Font("serif",Font.BOLD,20); 
        Placeholder3.setFont(Font8);
        labelPic.add(Placeholder3);

        butonRemove=new JButton("Remove");
        butonRemove.setBounds(120,300,100,30);
        butonRemove.addActionListener(this);
        labelPic.add(butonRemove);

        butonCancel=new JButton("Cancel");
        butonCancel.setBounds(300,300,100,30);
        butonCancel.addActionListener(this);
        labelPic.add(butonCancel);
        labelName.setVisible(false);
        labelPhone.setVisible(false);
        labelEmail.setVisible(false);
        butonRemove.setVisible(false);
        butonCancel.setVisible(false);

        frame.setSize(500,500);
        frame.setLocation(500,250);  
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==butonSearch){
            try{
                conn con = new conn();
                String q = "select name,phone,email from employee where emp_id ='"+textID.getText()+"' ";
                ResultSet rs = con.st.executeQuery(q);

                int i=0;
                if(rs.next()){
                    String name  = rs.getString(1); // col no. 1
                    String mob   = rs.getString(2); // col no. 2
                    String email = rs.getString(3); // col no. 3

                    labelName.setVisible(true);
                    labelPhone.setVisible(true);
                    labelEmail.setVisible(true);
                    butonRemove.setVisible(true);
                    butonCancel.setVisible(true);    
                    i=1;
                    Placeholder1.setText(name);
                    Placeholder2.setText(mob);
                    Placeholder3.setText(email);
                }
                if(i==0)
                    JOptionPane.showMessageDialog(null,"Id not found!");
            }catch(Exception ex){}
        }
        
        // Perform delete operation after confirming id matched
        if(ae.getSource()==butonRemove){
            try{
                conn con = new conn();
                String q = "delete from date where username=(select email from employee where emp_id='"+textID.getText()+"')";
                con.st.executeUpdate(q);
                q = "delete from employee where emp_id='"+textID.getText()+"'";
                con.st.executeUpdate(q);
                String q1 = "SET  @num := 0";
                String q2 = "UPDATE employee SET emp_id = @num := (@num+1)";
                String q3 = "ALTER TABLE employee AUTO_INCREMENT = 1";
                con.st.executeUpdate(q1);
                con.st.executeUpdate(q2);
                con.st.executeUpdate(q3);
                JOptionPane.showMessageDialog(null,"Deleted successfully!");
                labelName.setVisible(false);
                labelPhone.setVisible(false);
                labelEmail.setVisible(false);
                Placeholder1.setVisible(false);
                Placeholder2.setVisible(false);
                Placeholder3.setVisible(false);
                butonRemove.setVisible(false);
                butonCancel.setVisible(false);
                frame.dispose();
                new details_page(login_page.u);
        		System.out.println(login_page.u);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Exception occured while deleting record "+ex);
            }
        }
        // When cancel button pressed
        if(ae.getSource()==butonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
        if(ae.getSource()==butonBack){
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
    	new remove_employee(login_page.u);
    }
    
}