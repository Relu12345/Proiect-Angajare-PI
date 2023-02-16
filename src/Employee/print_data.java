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

/**
 * Fereastra unde se pot printa datele unui angajat
 * @author Coruian Aurel-Ionut
 */
class print_data implements ActionListener{
    
	/**
     * Variabila JFrame pentru fereastra
     */
    JFrame frame;
    /**
     * Variabila JLabel pentru textul de bun venit
     */
    JLabel labelWelcome;
    /**
     * Variabila JLabel pentru a afisa textul de id
     */
    JLabel labelID;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru id
     */
    JLabel textID;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele de familie
     */
    JLabel labelName;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru numele de familie
     */
    JLabel textName;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele mic
     */
    JLabel labelLast;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru numele mic
     */
    JLabel textLast;
    /**
     * Variabila JLabel pentru a afisa textul pentru adresa
     */
    JLabel labelAddress;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru adresa
     */
    JLabel textAddress;
    /**
     * Variabila JLabel pentru a afisa textul pentru numarul de telefon
     */
    JLabel labelTel;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru numarul de telefon
     */
    JLabel textTel;
    /**
     * Variabila JLabel pentru a afisa textul pentru email
     */
    JLabel labelEmail;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru email
     */
    JLabel textEmail;
    /**
     * Variabila JLabel pentru a afisa textul pentru ultimele studii
     */
    JLabel labelEducation;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru ultimele studii
     */
    JLabel textEducation;
    /**
     * Variabila JLabel pentru a afisa textul pentru locul de munca
     */
    JLabel labelJob;
    /**
     * Variabila JLabel pentru a afisa valoarea pentru locul de munca
     */
    JLabel textJob;
    /**
     * Variabila JLabel pentru a seta imaginea ferestrei
     */
    JLabel labelPic;
    /**
     * Variabila String ce retine valori din baza de date pentru id
     */
    String emp_id;
    /**
     * Variabila String ce retine valori din baza de date pentru numele de familie
     */
    String name;
    /**
     * Variabila String ce retine valori din baza de date pentru numele mic
     */
    String last;
    /**
     * Variabila String ce retine valori din baza de date pentru adresa
     */
    String address;
    /**
     * Variabila String ce retine valori din baza de date pentru numarul de telefon
     */
    String phone;
    /**
     * Variabila String ce retine valori din baza de date pentru email
     */
    String email;
    /**
     * Variabila String ce retine valori din baza de date pentru ultimele studii
     */
    String education;
    /**
     * Variabila String ce retine valori din baza de date pentru locul de munca
     */
    String job;
    /**
     * Variabila String ce retine valori din baza de date pentru varsta
     */
    String age;
    /**
     * Variabila String ce retine valori din baza de date pentru data nasterii
     */
    String dob;
    /**
     * Variabila String ce retine valori din baza de date pentru cnp
     */
    String cnp;
    /**
     * Variabila JButton pentru a printa datele angajatului
     */
    JButton butonPrint;
    /**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
    JButton butonBack;

    /**
     * Constructor
     * @param e_id Preia de la login identificatorul (emailul pentru angajat si username-ul pentru admin) user-ului
     */
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
            JOptionPane.showMessageDialog(null, "Database error, could not get data from database", "Error", JOptionPane.ERROR_MESSAGE);
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

    /**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
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
    		      JOptionPane.showMessageDialog(null,"Printed successfully!");
    		    } catch (DocumentException | IOException ex) {
    		          ex.printStackTrace();
    		    }
    		    
    		}
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
    	new print_data("Print Data");
    }

}