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
import java.awt.event.ActionEvent;
import java.awt.Color;
import com.toedter.calendar.JYearChooser;

public class Artwork extends JFrame {

	private JPanel contentPane;
	private JTextField ArtID;
	private JTextField ArtName;
	private JTextField CategoryID;
	private JTextField Price;
	private JYearChooser yearChooser ;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JTextField ArtistID;
	public static String msg;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Artwork frame = new Artwork(msg);
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
	public Artwork(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewArtWork = new JLabel("NEW ART WORK");
		lblNewArtWork.setForeground(Color.WHITE);
		lblNewArtWork.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewArtWork.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewArtWork.setBounds(30, 11, 715, 42);
		contentPane.add(lblNewArtWork);
		
		JLabel lblArtId = new JLabel("Art ID");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtId.setBounds(135, 84, 161, 22);
		contentPane.add(lblArtId);
		
		JLabel lblArtName = new JLabel("Art Name");
		lblArtName.setForeground(Color.WHITE);
		lblArtName.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtName.setBounds(135, 126, 161, 22);
		contentPane.add(lblArtName);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setForeground(Color.WHITE);
		lblYear.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblYear.setBounds(135, 172, 161, 22);
		contentPane.add(lblYear);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblCategory.setBounds(135, 219, 161, 29);
		contentPane.add(lblCategory);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPrice.setBounds(135, 269, 161, 22);
		contentPane.add(lblPrice);
		
		ArtID = new JTextField();
		ArtID.setColumns(10);
		ArtID.setBounds(342, 84, 302, 22);
		contentPane.add(ArtID);
		
		ArtName = new JTextField();
		ArtName.setColumns(10);
		ArtName.setBounds(342, 126, 302, 22);
		contentPane.add(ArtName);
		
		CategoryID = new JTextField();
		CategoryID.setColumns(10);
		CategoryID.setBounds(342, 218, 302, 22);
		contentPane.add(CategoryID);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(342, 269, 302, 22);
		contentPane.add(Price);
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String msg1;
					PreparedStatement smt= connection.prepareStatement("insert into ARTWORK(Art_ID,Artist_ID,Artwork_Name,Year,Category_ID,Price) values (?,?,?,?,?,?)");
					smt.setString(1,ArtID.getText());
					msg1=ArtistID.getText();
					smt.setString(2,msg1);
					smt.setString(3,ArtName.getText());
					int Year=yearChooser.getYear();
					String year=String.valueOf(Year);
					smt.setString(4,year);
					smt.setString(5,CategoryID.getText());
					smt.setInt(6,Integer.parseInt(Price.getText()));
					if(msg1.equals(msg)){
						smt.execute();
						JOptionPane.showMessageDialog(null,"Art work saved");
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect Artist ID");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Artist A= new Artist(msg);
				A.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(478, 365, 89, 23);
		contentPane.add(button);
		
		JLabel Artist = new JLabel("Confirm Artist ID");
		Artist.setForeground(Color.WHITE);
		Artist.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		Artist.setBounds(135, 317, 212, 22);
		contentPane.add(Artist);
		
		ArtistID = new JTextField();
		ArtistID.setColumns(10);
		ArtistID.setBounds(342, 317, 302, 22);
		contentPane.add(ArtistID);
		
		JButton btnNewButton = new JButton("View Categories");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Category AW= new Category(msg,1);
				AW.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(135, 365, 183, 23);
		contentPane.add(btnNewButton);
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(342, 174, 302, 20);
		contentPane.add(yearChooser);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}
}
