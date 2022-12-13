import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
public class Artist extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String msg=null;
					Artist frame = new Artist(msg);
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
	public Artist(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		table = new JTable();
		table.setBounds(20, 175, 733, 214);
		contentPane.add(table);
		try{
			CallableStatement smt= connection.prepareCall("Call refreshArtwork(?)");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("ARTIST");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(20, 11, 733, 42);
		contentPane.add(lblNewLabel);
		
		JButton btnAddArtwork = new JButton("Add Artwork");
		btnAddArtwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Artwork AW= new Artwork(msg);
				AW.setVisible(true);
			}
		});
		btnAddArtwork.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtwork.setBounds(10, 64, 153, 37);
		contentPane.add(btnAddArtwork);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteArtist DA= new DeleteArtist(msg);
				DA.setVisible(true);
			}
		});
		btnDeleteAccount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeleteAccount.setBounds(488, 64, 167, 37);
		contentPane.add(btnDeleteAccount);
		
		JButton btnDeleteArtwork = new JButton("Delete Artwork");
		btnDeleteArtwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteArtwork DA= new DeleteArtwork(msg);
				DA.setVisible(true);
			}
		});
		btnDeleteArtwork.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeleteArtwork.setBounds(163, 64, 167, 37);
		contentPane.add(btnDeleteArtwork);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogout.setBounds(655, 64, 98, 37);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Art work created by you:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_1.setBounds(20, 112, 551, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				AddCategory DA= new AddCategory(msg);
				DA.setVisible(true);
			}
		});
		btnAddCategory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddCategory.setBounds(330, 64, 157, 37);
		contentPane.add(btnAddCategory);
		
		JButton btnNewButton = new JButton("Art_ID");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton.setBounds(20, 152, 92, 23);
		contentPane.add(btnNewButton);
		
		JButton btnArtistid = new JButton("Artist_ID");
		btnArtistid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnArtistid.setBounds(111, 152, 93, 23);
		contentPane.add(btnArtistid);
		
		JButton btnArtworkname = new JButton("Name");
		btnArtworkname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnArtworkname.setBounds(200, 152, 96, 23);
		contentPane.add(btnArtworkname);
		
		JButton btnYear = new JButton("Year");
		btnYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnYear.setBounds(295, 152, 92, 23);
		contentPane.add(btnYear);
		
		JButton btnExhibitionid = new JButton("Exhib_ID");
		btnExhibitionid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExhibitionid.setBounds(662, 152, 91, 23);
		contentPane.add(btnExhibitionid);
		
		JButton btnGalleryid = new JButton("Gallery_ID");
		btnGalleryid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGalleryid.setBounds(569, 152, 93, 23);
		contentPane.add(btnGalleryid);
		
		JButton btnPrice = new JButton("Price");
		btnPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPrice.setBounds(478, 152, 92, 23);
		contentPane.add(btnPrice);
		
		JButton btnCategoryid = new JButton("Cat_ID");
		btnCategoryid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCategoryid.setBounds(385, 152, 94, 23);
		contentPane.add(btnCategoryid);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
		
	}
}
