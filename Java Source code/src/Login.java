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
	private JTextField textField;
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

		JButton btnRegister = new JButton("Create an Account");
		btnRegister.setForeground(UIManager.getColor("Label.foreground"));
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				CustomerRegisteration CR= new CustomerRegisteration();
				CR.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRegister.setBounds(107, 412, 222, 23);
		frame.getContentPane().add(btnRegister);

		JButton btnArtist = new JButton("Artist");
		btnArtist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ArtistRegistration AR= new ArtistRegistration();
				AR.setVisible(true);
			}
		});
		btnArtist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnArtist.setBounds(578, 465, 174, 23);
		frame.getContentPane().add(btnArtist);

		JButton btnNewButton = new JButton("Gallery");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				GalleryRegistration GR= new GalleryRegistration();
				GR.setVisible(true);
			}
		});
		btnNewButton.setBounds(582, 499, 174, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(107, 274, 225, 25);
		frame.getContentPane().add(textField);
		
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

	}
}
