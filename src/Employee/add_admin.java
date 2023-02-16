package Employee;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


/**
 * Fereastra unde se poate adauga un admin
 * @author Coruian Aurel-Ionut
 */
public class add_admin implements ActionListener {

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
     * Variabila JLabel pentru a afisa textul pentru username
     */
    JLabel labelUser;
    /**
     * Variabila JLabel pentru a afisa textul pentru parola
     */
    JLabel labelPass;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru username
     */
    JTextField textUser;
    /**
     * Variabila JTextField pentru a stoca datele introduse pentru parola
     */
    JTextField textPass;
    /**
     * Variabila JButton pentru a adauga un nou admin
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
    add_admin(String emp_id){
        frame = new JFrame("Admin");
        frame.setBackground(Color.white);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        format = new JLabel();
        format.setBounds(0,0,900,700);
        format.setLayout(null);

        title = new JLabel("New Admin Details");
        title.setBounds(100,30,500,50);
        title.setFont(new Font("serif",Font.ITALIC,25));
        title.setForeground(Color.black);
        format.add(title);
        frame.add(format);

        labelUser = new JLabel("User");
        labelUser.setBounds(50,150,100,30);
        labelUser.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelUser);

        textUser=new JTextField();
        textUser.setFont(new Font("serif",Font.BOLD,15));
        textUser.setBounds(150,150,150,30);
        format.add(textUser);

        labelPass = new JLabel("Password");
        labelPass.setBounds(50,220,200,30);
        labelPass.setFont(new Font("serif",Font.BOLD,20));
        format.add(labelPass);

        textPass=new JPasswordField();
        textPass.setFont(new Font("serif",Font.BOLD,15));
        textPass.setBounds(150,220,150,30);
        format.add(textPass);

        butonSubmit = new JButton("Submit");
        butonSubmit.setBackground(Color.BLACK);
        butonSubmit.setForeground(Color.WHITE);
        butonSubmit.setBounds(50,400,100,40);
        format.add(butonSubmit);

        butonCancel=new JButton("Cancel");   
        butonCancel.setBackground(Color.BLACK);
        butonCancel.setForeground(Color.WHITE);
        butonCancel.setBounds(230,400,100,40);
        format.add(butonCancel);
        
        butonSubmit.addActionListener(this);
        butonCancel.addActionListener(this);
        
        frame.setVisible(true);
        frame.setSize(400,600);
        frame.setLocation(200,20);
    }
 
    /**
	 * Functie pentru actiunea rezultata in urma apasarii unui buton
	 * @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	 */
    @Override
	public void actionPerformed(ActionEvent ae) {
	
		String User  = textUser.getText();
        String Pass = textPass.getText();
        
        if(ae.getSource() == butonSubmit){
            try{
                conn con = new conn();
                int id=0;
                String q = "insert into login values('"+User+"','"+Pass+"')";
                con.st.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Details Successfully Inserted!");
                frame.dispose(); // close current frame.
                new details_page(login_page.u);
        		System.out.println(login_page.u);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database error, check that all fields are correct", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
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
		new add_admin(login_page.u);
	}
	 
}
