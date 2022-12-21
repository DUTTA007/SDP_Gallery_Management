
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;


public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField FNAME;
    private JTextField LNAME;
	private JTextField REG_EMAIL_ID;
	private JTextField REG_PHNO;
	private JTextField CURRENT_ADDRESS;
	private JTextField DOB;


	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JTextField PERMANENT_ADDRESS;
	private JPasswordField passwordField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArtistRegistration = new JLabel("SIGN UP");
		lblArtistRegistration.setForeground(Color.WHITE);
		lblArtistRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistRegistration.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblArtistRegistration.setBounds(177, -3, 502, 42);
		contentPane.add(lblArtistRegistration);
		
		JLabel label_1 = new JLabel("First Name");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_1.setBounds(130, 97, 161, 22);
		contentPane.add(label_1);
		
		FNAME = new JTextField();
		FNAME.setColumns(10);
		FNAME.setBounds(377, 102, 302, 22);
		contentPane.add(FNAME);
		
		JLabel label_2 = new JLabel("Last Name");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_2.setBounds(130, 137, 161, 22);
		contentPane.add(label_2);
		
		LNAME = new JTextField();
		LNAME.setColumns(10);
		LNAME.setBounds(377, 142, 302, 22);
		contentPane.add(LNAME);

        JLabel label_3 = new JLabel("Nationality");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_3.setBounds(130, 176, 161, 22);
		contentPane.add(label_3);

        @SuppressWarnings("rawtypes")
		JComboBox<String> NATIONALITY_ID = new JComboBox<String>();
        NATIONALITY_ID.setBounds(377,180,305,22);
        contentPane.add(NATIONALITY_ID);
        try {
			PreparedStatement smt= connection.prepareStatement("SELECT NATIONALITY FROM NATIONALITY");
			ResultSet rsn = smt.executeQuery();
			while(rsn.next()) {
				String nid = rsn.getString("NATIONALITY");
				NATIONALITY_ID.addItem(nid);
			}
        }
        catch(Exception e) {
			   JOptionPane.showMessageDialog(null,e);
        }


        JLabel label_5 = new JLabel("Phone Number");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_5.setBounds(130, 249, 161, 22);
		contentPane.add(label_5);
		
		REG_PHNO = new JTextField();
		REG_PHNO.setColumns(10);
		REG_PHNO.setBounds(377, 254, 302, 22);
		contentPane.add(REG_PHNO);

 		JLabel label_6 = new JLabel("Email ID");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_6.setBounds(130, 293, 161, 22);
		contentPane.add(label_6);
		
		REG_EMAIL_ID = new JTextField();
		REG_EMAIL_ID.setColumns(10);
		REG_EMAIL_ID.setBounds(377, 298, 302, 22);
		contentPane.add(REG_EMAIL_ID);
		EmailValidator emailValidator = new EmailValidator();

 		JLabel label_7 = new JLabel("Gender");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_7.setBounds(130, 332, 161, 22);
		contentPane.add(label_7);
		
		JComboBox GENDER = new JComboBox();
		GENDER.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female", "Others"}));
       	GENDER.setBounds(377,336,305,22);
        contentPane.add(GENDER);
        
		JLabel label_8 = new JLabel("Current Address");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_8.setBounds(130, 371, 215, 22);
		contentPane.add(label_8);
		
		CURRENT_ADDRESS = new JTextField();
		CURRENT_ADDRESS.setColumns(10);
		CURRENT_ADDRESS.setBounds(377, 376, 302, 22);
		contentPane.add(CURRENT_ADDRESS);
		
		JLabel label_9 = new JLabel("Permanent Address");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_9.setBounds(130, 410, 215, 22);
		contentPane.add(label_9);

		contentPane.add(CURRENT_ADDRESS);



		JLabel label_10 = new JLabel("User Type");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_10.setBounds(130, 446, 161, 22);
		contentPane.add(label_10);

		
        JComboBox USER_ENTITY = new JComboBox();
        USER_ENTITY.setModel(new DefaultComboBoxModel(new String[] {"SELECT USER TYPE", "ARTIST", "CUSTOMER"}));
        USER_ENTITY.setBounds(377,450,305,22);
        contentPane.add(USER_ENTITY);

        JLabel label_11 = new JLabel("Password");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_11.setBounds(130, 485, 161, 22);
		contentPane.add(label_11);
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(377, 212, 302, 19);
		contentPane.add(dateChooser);
        
        
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(!emailValidator.validate(REG_EMAIL_ID.getText().trim())) {
						JOptionPane.showMessageDialog(null,"Invalid email ID");
				   }
					else {
						String nid = null;
						PreparedStatement smtn= connection.prepareStatement("SELECT NATIONALITY_ID FROM NATIONALITY where nationality=?");
						smtn.setString(1,NATIONALITY_ID.getSelectedItem().toString());
						ResultSet rsn = smtn.executeQuery();
						while(rsn.next()) {
							nid = rsn.getString("NATIONALITY_id");
						}
					PreparedStatement smt= connection.prepareStatement("insert into  USERS(FNAME, LNAME, NATIONALITY_ID, DOB, REG_PHNO, REG_EMAIL_ID, GENDER, CURRENT_ADDRESS, PERMANENT_ADDRESS, USER_ENTITY) values (?,?,?,?,?,?,?,?,?,?)");
					smt.setString(1,FNAME.getText());
					smt.setString(2,LNAME.getText());
					smt.setString(3,nid);
					java.sql.Date sqldate = new java.sql.Date(dateChooser.getDate().getTime());
					smt.setString(4,sqldate.toString());
					smt.setString(5,REG_PHNO.getText());
					smt.setString(6,REG_EMAIL_ID.getText());
					smt.setString(7,(String) GENDER.getSelectedItem().toString().subSequence(0, 1));
					smt.setString(8,CURRENT_ADDRESS.getText());
					smt.setString(9,PERMANENT_ADDRESS.getText());
					smt.setString(10,USER_ENTITY.getSelectedItem().toString());
					
					System.out.println(smt);
					PreparedStatement smt1 = connection.prepareStatement("SELECT USER_ID FROM USERS WHERE REG_EMAIL_ID=?");
					smt1.setString(1,REG_EMAIL_ID.getText());

						smt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Succesfully registered");
					smt.close();
					System.out.println(smt1);

					ResultSet rsu = smt1.executeQuery();
					int user_id = 0;
					while(rsu.next()) {
					user_id = rsu.getInt("USER_ID");
					}
					
					SecurePasswordStorage passManager = new SecurePasswordStorage();
					String salt = passManager.getNewSalt();
					System.out.println(passwordField.getPassword().toString());
					String hashpassword = passManager.signUp(String.valueOf(passwordField.getPassword()),salt);
					

					PreparedStatement smt11 = connection.prepareStatement("INSERT INTO LOGIN(LOGIN_ID,PASSWORD_HASH,PASSWORD_SALT) values (?,?,?)");
					smt11.setInt(1,user_id);
					smt11.setString(2,hashpassword);
					smt11.setString(3,salt);
					System.out.println(smt11);

					smt11.executeUpdate();
					}
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(346, 577, 122, 23);
		contentPane.add(button);
		/*
		BP = new JTextField();
		BP.setColumns(10);
		BP.setBounds(347, 172, 302, 22);
		contentPane.add(BP);
		*/
		JLabel lblDob = new JLabel("DOB");
		lblDob.setForeground(Color.WHITE);
		lblDob.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblDob.setBounds(130, 217, 161, 22);
		contentPane.add(lblDob);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		PERMANENT_ADDRESS = new JTextField();
		PERMANENT_ADDRESS.setBounds(377, 416, 302, 19);
		contentPane.add(PERMANENT_ADDRESS);
		PERMANENT_ADDRESS.setColumns(10);
		
		

		
		passwordField = new JPasswordField();
		passwordField.setBounds(377, 491, 302, 19);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, -3, 779, 610);
		contentPane.add(label);
	}
}
