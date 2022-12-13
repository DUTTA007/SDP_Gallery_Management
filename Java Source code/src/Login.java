import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
public class Login {

	private JFrame frame;
	private JTextField textLogin;
	private JTextField textLoginP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the application.
	 */
	java.sql.Connection connection=null;
	public Login() {
		initialize();
		connection=Connection.Dbconnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArtGalleryManagement = new JLabel("ART GALLERY MANAGEMENT");
		lblArtGalleryManagement.setForeground(Color.WHITE);
		lblArtGalleryManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtGalleryManagement.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblArtGalleryManagement.setBounds(31, 88, 726, 41);
		frame.getContentPane().add(lblArtGalleryManagement);
		
		textLogin = new JTextField();
		textLogin.setBounds(334, 153, 174, 29);
		frame.getContentPane().add(textLogin);
		textLogin.setColumns(10);
		
		textLoginP = new JTextField();
		textLoginP.setBounds(334, 200, 174, 29);
		frame.getContentPane().add(textLoginP);
		textLoginP.setColumns(10);
		
		JLabel lblEnterTheUser = new JLabel("Enter the Email ID:");
		lblEnterTheUser.setForeground(Color.WHITE);
		lblEnterTheUser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblEnterTheUser.setBounds(108, 153, 233, 29);
		frame.getContentPane().add(lblEnterTheUser);
		
		JLabel lblEnterThePass = new JLabel("Enter the Password:");
		lblEnterThePass.setForeground(Color.WHITE);
		lblEnterThePass.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblEnterThePass.setBounds(108, 200, 233, 29);
		frame.getContentPane().add(lblEnterThePass);
		
		JLabel lblNewLabel = new JLabel("No User ID? Register as");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(84, 244, 398, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				CustomerRegisteration CR= new CustomerRegisteration();
				CR.setVisible(true);
			}
		});
		btnCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCustomer.setBounds(84, 306, 174, 23);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnArtist = new JButton("Artist");
		btnArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ArtistRegistration AR= new ArtistRegistration();
				AR.setVisible(true);
			}
		});
		btnArtist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnArtist.setBounds(84, 340, 174, 23);
		frame.getContentPane().add(btnArtist);
		
		JButton btnNewButton = new JButton("Gallery");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				GalleryRegistration GR= new GalleryRegistration();
				GR.setVisible(true);
			}
		});
		btnNewButton.setBounds(84, 374, 174, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt1= connection.prepareStatement("select * from ARTIST where Artist_ID=?");
					String msg=textLogin.getText();
					smt1.setString(1,msg);
					ResultSet rs1=smt1.executeQuery();
					int count=0;
					while(rs1.next()){
						count=count+1;
					}
					if(count==1){
						JOptionPane.showMessageDialog(null,"Logging in as an Artist");
						frame.dispose();
						Artist A= new Artist(msg);
						A.setVisible(true);
					}
					else if(count==0){
						PreparedStatement smt2= connection.prepareStatement("select * from CUSTOMER where Customer_ID=?");
						smt2.setString(1,textLogin.getText());
						ResultSet rs2=smt2.executeQuery();
						while(rs2.next()){
							count=count+1;
						}
						if(count==1){
							JOptionPane.showMessageDialog(null,"Logging in as a Customer");
							frame.dispose();
							Customer C= new Customer(msg);
							C.setVisible(true);
						}
						else if(count==0){
							PreparedStatement smt3= connection.prepareStatement("select * from GALLERY where Gallery_ID=?");
							smt3.setString(1,textLogin.getText());
							ResultSet rs3=smt3.executeQuery();
							while(rs3.next()){
								count=count+1;
							}
							if(count==1){
								JOptionPane.showMessageDialog(null,"Logging in as a Gallery");
								frame.dispose();
								Gallery G= new Gallery(msg);
								G.setVisible(true);
							}
							else if(count==0){
								PreparedStatement smt4= connection.prepareStatement("select * from MANAGER where Manager_ID=?");
								smt4.setString(1,textLogin.getText());
								ResultSet rs4=smt4.executeQuery();
								while(rs4.next()){
									count=count+1;
								}
								if(count==1){
									JOptionPane.showMessageDialog(null,"Logging in as a Manager");
									frame.dispose();
									Manager M= new Manager(msg);
									M.setVisible(true);
								}
								else
									JOptionPane.showMessageDialog(null,"Invalid User ID");
							}
						}
						
					}
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_1.setBounds(547, 200, 155, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 779, 411);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
