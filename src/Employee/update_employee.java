package Employee;

import java.awt.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.awt.event.*;

/**
 * Fereastra unde se pot actualiza datele unui angajat
 * @author Coruian Aurel-Ionut
 */
class update_employee  implements ActionListener{

    /**
     * Variabila JFrame pentru fereastra
     */
    JFrame frame;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele de familie
     */
    JLabel labelName;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele mic
     */
    JLabel labelLast;
    /**
     * Variabila JLabel pentru a afisa textul pentru adresa
     */
    JLabel labelAddress;
    /**
     * Variabila JLabel pentru a afisa textul pentru numar de telefon
     */
    JLabel labelPhone;
    /**
     * Variabila JLabel pentru a afisa textul pentru email
     */
    JLabel labelEmail;
    /**
     * Variabila JLabel pentru a afisa textul pentru ultimele studii
     */
    JLabel labelEducation;
    /**
     * Variabila JLabel pentru a afisa textul pentru locul de munca
     */
    JLabel labelJob;
    /**
     * Variabila JLabel pentru a afisa textul de bun venit
     */
    JLabel labelWelcome;
    /**
     * Variabila JLabel pentru a afisa textul pentru CNP
     */
    JLabel labelCNP;
    /**
     * Variabila JLabel pentru a seta imaginea ferestrei
     */
    JLabel labelPic;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru numele de familie
     */
    JTextField textName;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru numele mic
     */
    JTextField textLast;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru adresa
     */
    JTextField textAddress;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru numarul de telefon
     */
    JTextField textPhone;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru email
     */
    JTextField textEmail;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru ultimele studii
     */
    JTextField textEducation;
    /**
     * Variabila JTextField pentru stocarea datelor introduse de user pentru CNP
     */
    JTextField textCNP;
	/**
	 * Variabila JComboBox pentru selectarea locului de munca
	 */
	JComboBox textJob;
	/**
     * Variabila JButton pentru a actualiza datele angajatului
     */
	JButton buttonUpdate;
	/**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
	JButton buttonCancel; 
    /**
     * Variabila globala String pentru a putea accesa de oriunde email-ul angajatului
     */
    String id_emp;

    /**
     * Constructor
     * @param e_id Preia de la login username-ul adminului
     */
    update_employee(String e_id){
        id_emp=e_id;
        
        frame=new JFrame("Update Employee details");
        frame.setVisible(false);
        frame.setSize(900,500);
        frame.setLocation(450,250);
        frame.setBackground(Color.white);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
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

        /**
         * Variabila String[] pentru a stoca alegerile pentru combo box-ul cu tipurile de locuri de munca
         */
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

    /**
     * Variabila int folosita pentru a verifica daca s-au luat sau nu date din baza de date pentru angajat
     */
    int i=0;
    /**
     * Variabila String folosita pentru a tine minte varsta
     */
    String age;
    /**
     * Variabila String folosita pentru a tine minte data nasterii
     */
    String dat;
    
    String EmailBack;

    /**
     * Functie pentru afisarea datelor din baza de date pentru un angajat in functie de id
     * @param id Id-ul angajatului ale caror date vor fi afisate
     */
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
                EmailBack = rs.getString(8);
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
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database error, could not get data from database", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==buttonUpdate && i==1){
            try{
                conn con = new conn();
                String q = "update employee set name='"+textName.getText()+"',fname='"+textLast.getText()+"',age='"+age+"',dob='"+dat+"',address='"+textAddress.getText()+"',phone='"+textPhone.getText()+"',email='"+textEmail.getText()+"',education='"+textEducation.getText()+"',post='"+textJob.getSelectedItem().toString()+"',cnp='"+textCNP.getText()+"' where emp_id='"+id_emp+"'";
                con.st.executeUpdate(q);
                q = "update date set username='"+textEmail.getText()+"' where username='"+EmailBack+"'";
                con.st.executeUpdate(q);
                q = "update pass set email='"+textEmail.getText()+"' where email='"+EmailBack+"'";
                con.st.executeUpdate(q);
                q = "update timeoff set username='"+textEmail.getText()+"' where username='"+EmailBack+"'";
                con.st.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Successfully updated!");
                frame.dispose();
                new details_page(login_page.u);
        		System.out.println(login_page.u);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        if(ae.getSource()==buttonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
    }
    
    /**
	 * Functia de main
	 * @param args Argumentele pentru main
	 */
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
    	new update_employee("");
    }
    
}