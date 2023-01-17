package Employee;

import java.awt.*;
import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;

import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

class print_data implements ActionListener{
    JFrame frame;
    JLabel labelWelcome,labelID,textID,labelName,textName,labelLast,textLast,labelAddress,textAddress,labelTel,textTel,labelEmail,textEmail,labelEducation,textEducation,labelJob,textJob,labelPic;
    String emp_id,name,last,address,phone,email,education,job,age,dob,cnp;
    JButton butonPrint,butonBack;

    print_data(String e_id){
    	frame=new JFrame("Print Data");
        frame.setVisible(false);
        frame.setSize(600,640);
        frame.setLocation(450,200);
        frame.setBackground(Color.white);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            conn con = new conn();
            String q = "select * from employee where emp_id = '"+e_id+"'";
            ResultSet rs= con.st.executeQuery(q);
            int i=0;
            while(rs.next()){ 
            	i=1;
                name = rs.getString("name");
                last = rs.getString("fname");
                age = rs.getString("age");
                dob = rs.getString("dob");
                address = rs.getString("address");
                phone = rs.getString("phone");
                email = rs.getString("email");
                education = rs.getString("education");
                job = rs.getString("post");
                cnp = rs.getString("cnp");
                emp_id = rs.getString("emp_id");             
            }
            
            if(i==0) {
            	JOptionPane.showMessageDialog(null,"Id not found!");
                frame.dispose();
                new view_employee(login_page.u);
            }
            else
            {
            	frame.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        labelPic=new JLabel();
        labelPic.setBounds(0,0,595,642);
        labelPic.setLayout(null);
        ImageIcon imageVar=new ImageIcon(ClassLoader.getSystemResource("icon/print.jpg"));
        labelPic.setIcon(imageVar);

        labelWelcome = new JLabel("Employee Details");
        labelWelcome.setBounds(50,10,250,30);
        frame.add(labelWelcome);
        labelWelcome.setFont(new Font("serif",Font.BOLD,25));
        labelPic.add(labelWelcome);
        frame.add(labelPic);

        if(login_page.s == 0) {
	        labelID = new JLabel("Employee Id:");
	        labelID.setBounds(50,70,120,30);
	        labelID.setFont(new Font("serif",Font.BOLD,20));
	        labelPic.add(labelID);
	
	        textID = new JLabel(emp_id);
	        textID.setBounds(200,70,200,30);
	        textID.setFont(new Font("serif",Font.BOLD,20));
	        labelPic.add(textID);
        }

        labelName = new JLabel("First Name:");
        labelName.setBounds(50,120,200,30);
        labelName.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelName);

        textName = new JLabel(name);
        textName.setBounds(200,120,300,30);
        textName.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textName);

        labelLast = new JLabel("Last Name:"); 
        labelLast.setBounds(50,170,200,30);
        labelLast.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelLast);

        textLast = new JLabel(last);
        textLast.setBounds(200,170,300,30);
        textLast.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textLast);

        labelAddress= new JLabel("Address:");
        labelAddress.setBounds(50,220,100,30);
        labelAddress.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelAddress);

        textAddress= new JLabel(address);
        textAddress.setBounds(200,220,300,30);
        textAddress.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textAddress);

        labelTel= new JLabel("Phone:");  
        labelTel.setBounds(50,270,100,30);
        labelTel.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelTel);

        textTel= new JLabel(phone);
        textTel.setBounds(200,270,300,30); 
        textTel.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textTel);
        
        labelEmail= new JLabel("Email:");
        labelEmail.setBounds(50,320,100,30);
        labelEmail.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelEmail);

        textEmail= new JLabel(email);
        textEmail.setBounds(200,320,300,30);
        textEmail.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textEmail);
        
        labelEducation= new JLabel("Education:");
        labelEducation.setBounds(50,370,100,30);
        labelEducation.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelEducation);

        textEducation= new JLabel(education);
        textEducation.setBounds(200,370,300,30); 
        textEducation.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textEducation);

        labelJob= new JLabel("Job Position:");
        labelJob.setBounds(50,420,150,30);
        labelJob.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(labelJob);

        textJob= new JLabel(job);
        textJob.setBounds(200,420,300,30);
        textJob.setFont(new Font("serif",Font.BOLD,20));
        labelPic.add(textJob);

        butonPrint=new JButton("Print");
        butonPrint.setBackground(Color.BLACK);
        butonPrint.setForeground(Color.WHITE);
        butonPrint.setBounds(100,520,100,30);
        butonPrint.addActionListener(this);
        labelPic.add(butonPrint);

        butonBack=new JButton("Back");
        butonBack.setBackground(Color.BLACK);
        butonBack.setForeground(Color.WHITE);
        butonBack.setBounds(250,520,100,30);
        butonBack.addActionListener(this);
        labelPic.add(butonBack);
        
        
    }

    public void actionPerformed(ActionEvent ae){

    	if(ae.getSource()==butonPrint){
    		butonPrint.setVisible(false);
    		butonBack.setVisible(false);
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Save PDF");
    		fileChooser.setSelectedFile(new File("Fisa " + name + ".pdf"));
    		int userSelection = fileChooser.showSaveDialog(frame);

    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    		    File fileToSave = fileChooser.getSelectedFile();
    		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());

    		    // Create a PDF document
    		    Document document = new Document();

    		    try {
    		      // Create a PdfWriter for the document
    		      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

    		      // Open the document
    		      document.open();

    		      // Create a Graphics2D object for the page
    		      PdfContentByte contentByte = writer.getDirectContent();
    		      Graphics2D graphics2D = contentByte.createGraphics(600, 640);

    		      // Draw the JFrame onto the Graphics2D object
    		      frame.paint(graphics2D);

    		      // Dispose the Graphics2D object
    		      graphics2D.dispose();

    		      // Close the document
    		      document.close();
    		    } catch (DocumentException | IOException ex) {
    		          ex.printStackTrace();
    		    }
    		}
    		    JOptionPane.showMessageDialog(null,"Printed successfully!");
    		    frame.dispose();
    		    details_page newDetails = new details_page(login_page.u);
    			System.out.println(login_page.u);
    		}
   
        if(ae.getSource()==butonBack){
            frame.dispose();
            details_page newDetails = new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
    }
    
    public static void main(String[] args){
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
    	new print_data("Print Data");
    }

}