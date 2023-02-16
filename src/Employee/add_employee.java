package Employee;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


/**
 * Fereastra unde se poate adauga un angajat
 * @author Coruian Aurel-Ionut
 */
public class add_employee implements ActionListener {

	/**
	 * Variabila JFrame pentru fereastra
	 */
	JFrame frame;
    /**
     * Variabila JLabel pentru a formata fereastra
     */
    JLabel format;
    /**
     * Variabila JLabel pentru a afisa textul de bun venit
     */
    JLabel title;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele de familie
     */
    JLabel labelNume;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele mic
     */
    JLabel labelLast;
    /**
     * Variabila JLabel pentru a afisa textul pentru varsta
     */
    JLabel labelVarsta;
    /**
     * Variabila JLabel pentru a afisa textul pentru data nasterii
     */
    JLabel labelData;
    /**
     * Variabila JLabel pentru a afisa textul pentru adresa
     */
    JLabel labelAdr;
    /**
     * Variabila JLabel pentru a afisa textul pentru numarul de telefon
     */
    JLabel labelTel;
    /**
     * Variabila JLabel pentru a afisa textul pentru email
     */
    JLabel labelEmail;
    /**
     * Variabila JLabel pentru a afisa textul pentru ultimele studii
     */
    JLabel labelEdu;
    /**
     * Variabila JLabel pentru a afisa textul pentru locul de munca
     */
    JLabel labelJob;
    /**
     * Variabila JLabel pentru a afisa textul pentru CNP
     */
    JLabel labelCNP;
    /**
     * Variabila JLabel pentru a organiza mai bine elementele de pe fereastra
     */
    JLabel pholder1,pholder2;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru numele de familie
     */
    JTextField textNume;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru numele mic
     */
    JTextField textLast;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru varsta
     */
    JTextField textVarsta;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru data nasterii
     */
    JTextField textData;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru adresa
     */
    JTextField textAdr;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru numarul de telefon
     */
    JTextField textTel;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru email
     */
    JTextField textEmail;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru ultimele studii
     */
    JTextField textEdu;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru CNP
     */
    JTextField textCNP;
	/**
	 * Variabila JComboBox pentru a putea selecta ce loc de munca o sa aiba angajatul
	 */
	JComboBox textJob;
    /**
     * Variabila JButton pentru a adauga noul angajat
     */
    JButton butonSubmit;
    /**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
    JButton butonCancel;

    
    /**
     * Constructor
     * @param emp_id Preia de la login username-ul adminului
     */
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

        labelLast = new JLabel("Last Name");
        labelLast.setBounds(400,150,200,30);
        labelLast.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelLast);

        textLast=new JTextField();
        textLast.setFont(new Font("serif",Font.BOLD,15));
        textLast.setBounds(600,150,150,30);
        format.add(textLast);

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
 
    /**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
    @Override
	public void actionPerformed(ActionEvent ae) {
	
		String Nume  = textNume.getText();
        String Familie = textLast.getText();
        String Varsta = textVarsta.getText();
        String Data = textData.getText();
        String Adresa = textAdr.getText();
        String Telefon = textTel.getText();
        String Email = textEmail.getText();
        String Educatie = textEdu.getText();
        String Job = textJob.getSelectedItem().toString();
        String CNP = textCNP.getText();
        if(ae.getSource() == butonSubmit){
        	if(Nume.isBlank() || Familie.isBlank() || Varsta.isBlank() || Data.isBlank() || Adresa.isBlank() || Telefon.isBlank() || Email.isBlank() || Educatie.isBlank() || Job.isBlank() || CNP.isBlank()){
        		JOptionPane.showMessageDialog(null, "You have to complete all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	else{
        		try{
	                conn con = new conn();
	                String q1 = "SET  @num := 0";
	                String q2 = "UPDATE employee SET emp_id = @num := (@num+1)";
	                String q3 = "ALTER TABLE employee AUTO_INCREMENT = 1";
	                con.st.executeUpdate(q1);
	                con.st.executeUpdate(q2);
	                con.st.executeUpdate(q3);
	                String q = "insert into employee values('"+0+"', '"+Nume+"','"+Familie+"','"+Varsta+"','"+Data+"','"+Adresa+"','"+Telefon+"','"+Email+"','"+Educatie+"','"+Job+"','"+CNP+"')";
	                con.st.executeUpdate(q);
	                q = "insert into date values('"+Email+"', '"+0+"', '"+0+"', '"+0+"')";
	                con.st.executeUpdate(q);
	                q = "insert into pass values('"+Email+"', 'temp"+Nume+"')";
	                con.st.executeUpdate(q);
	                JOptionPane.showMessageDialog(null,"Account Successfully Created!\n Temporary password: temp"+Nume+"");
	                frame.dispose(); // close current frame.
	                new details_page(login_page.u);
	        		System.out.println(login_page.u);
	            }catch(Exception e){
	                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
	                e.printStackTrace();
	            }
        	}
        }else if(ae.getSource() == butonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
	}
	
    /**
	 * Functia de main
	 * @param args Argumentele pentru main
	 */
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
