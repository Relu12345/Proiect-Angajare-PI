package Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

// THIS CODE IS SIMILAR TO VIEW_EMPLOYEE CODE, THEY NEEDS TO BE SEPARATE TO DIFFERENTIATE BETWEEN CLASSES! 

/**
 * Fereastra unde se cauta id-ul angajatului ce urmeaza sa aiba datele actualizate
 * @author Coruian Aurel-Ionut
 */
class search_employee implements ActionListener{

	/**
     * Variabila JFrame pentru fereastra
     */
    JFrame frame;
    /**
     * Variabila JTextField pentru stocarea id-ului ce urmeaza sa aiba datele actualizate
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
     * Variabila JLabel pentru a seta imaginea ferestrei
     */
    JLabel labelPic;
    /**
     * Variabila JLabel pentru a afisa textul de id
     */
    JLabel labelID;
    /**
     * Variabila JButton pentru a continua la fereastra de actualizare pentru id-ul precizat
     */
    JButton butonSearch;
    /**
     * Variabila JButton pentru a reveni la pagina de detalii
     */
    JButton butonCancel;

    /**
     * Constructor
     * @param emp_id Preia de la login username-ul adminului
     */
    search_employee(String emp_id){
        frame=new JFrame("Search");
        frame.setBackground(Color.green);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelPic=new JLabel();
        labelPic.setBounds(0,0,500,270);
        labelPic.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("icon/view.jpg"));
        labelPic.setIcon(img);

        String[] col = {"ID", "First", "Last"};
        List<Object[]> data = getDataFromDatabase();
        model = new DefaultTableModel(data.toArray(new Object[0][0]), col);
        list = new JTable(model);
        
        scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(30, 50, 300, 100);
        labelPic.add(scroll);
        
        labelID=new JLabel("Employee Id");
        labelID.setVisible(true);
        labelID.setBounds(40,10,250,30);
        labelID.setForeground(Color.white);
        Font Font1 = new Font("serif",Font.BOLD,30);
        labelID.setFont(Font1); 
        labelPic.add(labelID);
        frame.add(labelPic);

        textID=new JTextField();
        textID.setFont(new Font("serif",Font.BOLD,17));
        textID.setBounds(240,10,220,30);
        labelPic.add(textID);

        butonSearch=new JButton("Search");
        butonSearch.setBounds(240,180,100,30);
        butonSearch.addActionListener(this);
        labelPic.add(butonSearch);

        butonCancel=new JButton("Cancel");
        butonCancel.setBounds(360,180,100,30);
        butonCancel.addActionListener(this);
        labelPic.add(butonCancel);

        frame.setSize(500,270);
        frame.setLocation(450,250);
        frame.setVisible(true);
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

        if(ae.getSource()==butonCancel){
        	frame.dispose();
        	new details_page(login_page.u);
    		System.out.println(login_page.u);
        }
        if(ae.getSource()==butonSearch){
        	frame.dispose();
            String emp_id = textID.getText();
            update_employee newUpdate=new update_employee(emp_id);
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
    	search_employee newSearch=new search_employee(login_page.u);
    }
}