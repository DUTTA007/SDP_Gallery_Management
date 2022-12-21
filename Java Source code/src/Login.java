import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
public class Login {

	private JFrame frame;
	private JTextField textLogin;

	
	 // Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 // Create the application.
	 
	java.sql.Connection connection=null;
	private JPasswordField passwordField;
	public Login() {
		initialize();
		connection=Connection.Dbconnection();
	}

	
	 // Initialize the contents of the frame.
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblArtGalleryManagement = new JLabel("ART GALLERY");
		lblArtGalleryManagement.setForeground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		lblArtGalleryManagement.setHorizontalAlignment(SwingConstants.LEFT);
		lblArtGalleryManagement.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblArtGalleryManagement.setBounds(107, 105, 615, 41);
		frame.getContentPane().add(lblArtGalleryManagement);

		textLogin = new JTextField();
		textLogin.setBounds(107, 223, 225, 25);
		frame.getContentPane().add(textLogin);
		textLogin.setColumns(10);

		JLabel lblEMail = new JLabel("E-Mail");
		lblEMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEMail.setForeground(UIManager.getColor("scrollbar"));
		lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEMail.setBounds(107, 197, 60, 29);
		frame.getContentPane().add(lblEMail);

		JLabel lblNewMember = new JLabel("New Member ?");
		lblNewMember.setForeground(Color.WHITE);
		lblNewMember.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewMember.setBounds(107, 378, 192, 35);
		frame.getContentPane().add(lblNewMember);

		JButton btnArtist = new JButton("Create account");
		btnArtist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SignUp AR= new SignUp();
				AR.setVisible(true);
			}
		});
		btnArtist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnArtist.setBounds(95, 410, 174, 23);
		frame.getContentPane().add(btnArtist);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String msg=textLogin.getText();
					String inputpass=String.valueOf(passwordField.getPassword());
					SecurePasswordStorage passManager = new SecurePasswordStorage();
					PreparedStatement smt1= connection.prepareStatement("SELECT USER_ID,USER_ENTITY FROM USERS WHERE REG_EMAIL_ID=?");
					smt1.setString(1,msg);
					ResultSet rs1=smt1.executeQuery();
					String userid = null; 
					String entity = null; 
					

					
					while (rs1.next()) {
						userid = rs1.getString("USER_ID");
						entity = rs1.getString("USER_ENTITY");
					}
					System.out.println(userid);
					System.out.println(entity);
					
					PreparedStatement smt2= connection.prepareStatement("SELECT PASSWORD_HASH,PASSWORD_SALT FROM LOGIN WHERE LOGIN_ID=?");
					smt2.setString(1, userid);
					ResultSet rs2=smt2.executeQuery();
					
					String dbpass = null; 
					String salt = null;
					
					while (rs2.next()) {
						dbpass = rs2.getString("PASSWORD_HASH");
						salt = rs2.getString("PASSWORD_SALT");
					}
					System.out.println(dbpass);
					System.out.println(salt);
					
					
					if (passManager.authenticateUser(inputpass,salt,dbpass))
					{
					if(entity.equals("ARTIST")){
						JOptionPane.showMessageDialog(null,"Logging in as an Artist");
						frame.dispose();
						Artist A= new Artist(userid);
						A.setVisible(true);
						}
					else if(entity.equals("CUSTOMER")) {
						JOptionPane.showMessageDialog(null,"Logging in as a Customer");
						frame.dispose();
						Customer C= new Customer(userid);
						C.setVisible(true);
					}
					else if(entity.equals("STAFF")) {
						JOptionPane.showMessageDialog(null,"Logging in as a Manager");
						frame.dispose();
						Manager M= new Manager(userid);
						M.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"Invalid User ID or Password");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"Invalid User ID or Password");
					}
				}
				
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }

			}
		});
		
		
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(106, 320, 118, 25);
		frame.getContentPane().add(btnLogin);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setForeground(UIManager.getColor("scrollbar"));
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPass.setBounds(106, 247, 80, 29);
		frame.getContentPane().add(lblPass);
		
		JLabel lblSeperator2 = new JLabel("________________________________________________________");
		lblSeperator2.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperator2.setForeground(UIManager.getColor("Label.disabledShadow"));
		lblSeperator2.setBounds(107, 361, 428, 14);
		frame.getContentPane().add(lblSeperator2);
		
		JLabel lblSignIn = new JLabel("Sign in");
		lblSignIn.setForeground(UIManager.getColor("window"));
		lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSignIn.setBounds(107, 157, 101, 29);
		frame.getContentPane().add(lblSignIn);
		
		JLabel lblSeperator1 = new JLabel("________________________________________________________________________________");
		lblSeperator1.setBounds(107, 182, 521, 14);
		frame.getContentPane().add(lblSeperator1);
		
		JLabel lblMemberName = new JLabel("User");
		lblMemberName.setForeground(UIManager.getColor("ToolTip.background"));
		lblMemberName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMemberName.setBounds(10, 11, 100, 35);
		frame.getContentPane().add(lblMemberName);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(img));
		lblBackground.setBounds(0, 0, 784, 561);
		frame.getContentPane().add(lblBackground);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 277, 225, 19);
		frame.getContentPane().add(passwordField);

	}
}
