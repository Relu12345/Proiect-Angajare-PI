package Employee;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class add_employee implements ActionListener {

	JFrame frame;
    JLabel format,title,labelNume,labelFamilie,labelVarsta,labelData,labelAdr,labelTel,labelEmail,labelEdu,labelJob,labelCNP,pholder1,pholder2;
    JTextField textNume,textFamilie,textVarsta,textData,textAdr,textTel,textEmail,textEdu,textCNP;
	JComboBox textJob;
    JButton butonSubmit,butonCancel;

    
    add_employee(String emp_id){
        frame = new JFrame("Add Employee");
        frame.setBackground(Color.white);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        format = new JLabel();
        format.setBounds(0,0,900,700);
        format.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/add.jpg"));
        format.setIcon(img);

        title = new JLabel("New Employee Details");
        title.setBounds(320,30,500,50);
        title.setFont(new Font("serif",Font.ITALIC,25));
        title.setForeground(Color.black);
        format.add(title);
        frame.add(format);

        labelNume = new JLabel("First Name");
        labelNume.setBounds(50,150,100,30);
        labelNume.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelNume);

        textNume=new JTextField();
        textNume.setFont(new Font("serif",Font.BOLD,15));
        textNume.setBounds(200,150,150,30);
        format.add(textNume);

        labelFamilie = new JLabel("Last Name");
        labelFamilie.setBounds(400,150,200,30);
        labelFamilie.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelFamilie);

        textFamilie=new JTextField();
        textFamilie.setFont(new Font("serif",Font.BOLD,15));
        textFamilie.setBounds(600,150,150,30);
        format.add(textFamilie);

        labelVarsta = new JLabel("Age");
        labelVarsta.setBounds(50,200,100,30);
        labelVarsta.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelVarsta);

        textVarsta=new JTextField();
        textVarsta.setFont(new Font("serif",Font.BOLD,15));
        textVarsta.setBounds(200,200,150,30);
        format.add(textVarsta);

        labelData = new JLabel("Date Of Birth");  
        labelData.setBounds(400,200,200,30);
        labelData.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelData);

        textData=new JTextField();
        textData.setFont(new Font("serif",Font.BOLD,15));
        textData.setBounds(600,200,150,30);
        format.add(textData);

        labelAdr = new JLabel("Address");
        labelAdr.setBounds(50,250,100,30);
        labelAdr.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelAdr);

        textAdr=new JTextField();
        textAdr.setFont(new Font("serif",Font.BOLD,15));
        textAdr.setBounds(200,250,150,30);
        format.add(textAdr);

        labelTel = new JLabel("Phone");
        labelTel.setBounds(400,250,100,30);
        labelTel.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelTel);

        textTel=new JTextField();
        textTel.setFont(new Font("serif",Font.BOLD,15));
        textTel.setBounds(600,250,150,30);
        format.add(textTel);

        labelEmail = new JLabel("Email");
        labelEmail.setBounds(50,300,100,30);
        labelEmail.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelEmail);

        textEmail=new JTextField();
        textEmail.setFont(new Font("serif",Font.BOLD,15));
        textEmail.setBounds(200,300,150,30);
        format.add(textEmail);

        labelEdu = new JLabel("Education");
        labelEdu.setBounds(400,300,100,30);
        labelEdu.setFont(new Font("serif",Font.BOLD,20));    
        format.add(labelEdu);

        textEdu=new JTextField();
        textEdu.setFont(new Font("serif",Font.BOLD,15));
        textEdu.setBounds(600,300,150,30);
        format.add(textEdu);

        labelJob = new JLabel("Job Position");
        labelJob.setBounds(50,350,150,30);
        labelJob.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelJob);

        String[] selectiiJob = {"Student Worker", "Junior Developer", "Developer", "Senior Developer"};
        textJob=new JComboBox(selectiiJob);
        textJob.setFont(new Font("serif",Font.BOLD,15));
        textJob.setBounds(200,350,150,30);
        format.add(textJob);

        labelCNP = new JLabel("CNP");
        labelCNP.setBounds(400,350,100,30);
        labelCNP.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelCNP);

        textCNP=new JTextField();
        textCNP.setFont(new Font("serif",Font.BOLD,15));
        textCNP.setBounds(600,350,150,30);
        format.add(textCNP);

        pholder1 = new JLabel();
        pholder1.setBounds(200,450,250,200);
        format.add(pholder1);

        pholder2 = new JLabel("");
        pholder2.setBounds(600,450,250,200);
        format.add(pholder2);

        butonSubmit = new JButton("Submit");
        butonSubmit.setBackground(Color.BLACK);
        butonSubmit.setForeground(Color.WHITE);
        butonSubmit.setBounds(250,550,150,40);
        format.add(butonSubmit);

        butonCancel=new JButton("Cancel");   
        butonCancel.setBackground(Color.BLACK);
        butonCancel.setForeground(Color.WHITE);
        butonCancel.setBounds(450,550,150,40);
        format.add(butonCancel);
        
        butonSubmit.addActionListener(this);
        butonCancel.addActionListener(this);
        
        frame.setVisible(true);
        frame.setSize(900,700);
        frame.setLocation(200,20);
    }
 
    @Override
	public void actionPerformed(ActionEvent ae) {
	
		String Nume  = textNume.getText();
        String Familie = textFamilie.getText();
        String Varsta = textVarsta.getText();
        String Data = textData.getText();
        String Adresa = textAdr.getText();
        String Telefon = textTel.getText();
        String Email = textEmail.getText();
        String Educatie = textEdu.getText();
        String Job = textJob.getSelectedItem().toString();
        String CNP = textCNP.getText();
        if(ae.getSource() == butonSubmit){
            try{
                conn con = new conn();
                int id=0;
                String q = "insert into employee values('"+id+"','"+Nume+"','"+Familie+"','"+Varsta+"','"+Data+"','"+Adresa+"','"+Telefon+"','"+Email+"','"+Educatie+"','"+Job+"','"+CNP+"')";
                con.st.executeUpdate(q);
                q = "insert into date values('"+Email+"', '"+0+"', '"+0+"')";
                con.st.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Details Successfully Inserted!");
                frame.dispose(); // close current frame.
                new details_page(login_page.u);
        		System.out.println(login_page.u);
            }catch(Exception e){
                System.out.println("The error is:"+e);
            }
        }else if(ae.getSource() == butonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
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
		new add_employee(login_page.u);
	}
	 
}
