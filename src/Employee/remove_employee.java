package Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Fereastra unde se poate concedia un angajat
 * @author Coruian Aurel-Ionut
 */
class remove_employee implements ActionListener{
    
	/**
     * Variabila JFrame pentru fereastra
     */
    JFrame frame;
    /**
     * Variabila JTextField pentru a stoca id-ul unui angajat
     */
    JTextField textID;
    /**
     * Variabila DefaultTableModel pentru a configura modelul tabelului
     */
    DefaultTableModel model;
    /**
     * Variabila JTable pentru a face un tabel cu datele angajatului
     */
    JTable list;
    /**
     * Variabila JScrollPane pentru a putea afisa cu usurinta toate detaliile tabelului
     */
    JScrollPane scroll;
    /**
     * Variabila JLabel pentru a afisa textul de id
     */
    JLabel labelID;
    /**
     * Variabila JLabel pentru a afisa textul pentru numele de familie
     */
    JLabel labelName;
    /**
     * Variabila JLabel pentru a afisa textul pentru numarul de telefon
     */
    JLabel labelPhone;
    /**
     * Variabila JLabel pentru a afisa textul pentru email
     */
    JLabel labelEmail;
    /**
     * Variabila JLabel pentru a seta imaginea ferestrei
     */
    JLabel labelPic;
    /**
     * Variabila JLabel pentru a organiza mai bine elementele de pe fereastra
     */
    JLabel Placeholder1,Placeholder2,Placeholder3;
    /**
     * Variabila JButton pentru a cauta angajatul in functie de id
     */
    JButton butonSearch;
    /**
     * Variabila JButton pentru a sterge angajatul in functie de id
     */
    JButton butonRemove;
    /**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
    JButton butonCancel, butonBack;

    /**
     * Constructor
     * @param emp_id Preia de la login username-ul adminului
     */
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
        
        String[] col = {"ID", "First", "Last"};
        List<Object[]> data = getDataFromDatabase();
        model = new DefaultTableModel(data.toArray(new Object[0][0]), col);
        list = new JTable(model);
        
        scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(30, 150, 300, 100);
        labelPic.add(scroll);
        
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
    
    /**
     * Functie ce ia din baza de date informatiile angajatului pentru a putea gasi angajatul respectiv
     * @return data Returneaza lista rezultata
     */
    private List<Object[]> getDataFromDatabase() {
        List<Object[]> data = new ArrayList<>();
        try {
            conn con = new conn();
            ResultSet rs = con.st.executeQuery("SELECT emp_id, name, fname FROM employee");
            while (rs.next()) {
                Object[] row = {rs.getInt(1), rs.getString(2), rs.getString(3)};
                data.add(row);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database error, could not get data from database", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return data;
    }
    
    /**
	* Functie pentru actiunea rezultata in urma apasarii unui buton
	* @param ae Variabila pentru reprezentarea obiectului asupra caruia se va aplica evenimentul
	*/
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==butonSearch){
            try{
            	scroll.setVisible(false);
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
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database error, could not get data from database", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        
        // Perform delete operation after confirming id matched
        if(ae.getSource()==butonRemove){
            try{
                conn con = new conn();
                String q = "delete from date where username=(select email from employee where emp_id='"+textID.getText()+"')";
                con.st.executeUpdate(q);
                q = "delete from pass where email=(select email from employee where emp_id='"+textID.getText()+"')";
                con.st.executeUpdate(q);
                q = "delete from timeoff where username=(select email from employee where emp_id='"+textID.getText()+"')";
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

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database error, could not delete from database", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
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
    	new remove_employee(login_page.u);
    }
    
}