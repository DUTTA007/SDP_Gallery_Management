import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Gallery extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String msg;

	/**
	 * Launch the application.
	 */
	public void refreshTable(String msg){
		try{
			PreparedStatement smt= connection.prepareStatement("select * from ARTWORK where Gallery_ID=?");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gallery frame = new Gallery(msg);
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
	java.sql.Connection connection=Connection.Dbconnection();
	public Gallery(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGallery = new JLabel("GALLERY");
		lblGallery.setForeground(Color.WHITE);
		lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
		lblGallery.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblGallery.setBounds(10, 11, 759, 42);
		contentPane.add(lblGallery);
		
		JButton btnAddManager = new JButton("Managers");
		btnAddManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			contentPane.setVisible(false);
			dispose();
			Man M= new Man(msg);
			M.setVisible(true);
			}
		}
		);
		btnAddManager.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddManager.setBounds(20, 64, 133, 37);
		contentPane.add(btnAddManager);
		
		table = new JTable();
		table.setBounds(21, 174, 733, 213);
		contentPane.add(table);
		
		
		try{
			PreparedStatement smt= connection.prepareStatement("select * from ARTWORK where Gallery_ID=?");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			refreshTable(msg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JButton button = new JButton("Delete Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteGallery DG= new DeleteGallery(msg);
				DG.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(538, 64, 189, 37);
		contentPane.add(button);
		
		JButton btnDeleteExhibition = new JButton("Exhibitions");
		btnDeleteExhibition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Ex DE= new Ex(msg);
				DE.setVisible(true);
			}
		});
		btnDeleteExhibition.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeleteExhibition.setBounds(191, 64, 176, 37);
		contentPane.add(btnDeleteExhibition);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_1.setBounds(396, 64, 108, 37);
		contentPane.add(button_1);
		
		JLabel lblArtWorkAvailable = new JLabel("Art work available in your gallery:");
		lblArtWorkAvailable.setForeground(Color.WHITE);
		lblArtWorkAvailable.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblArtWorkAvailable.setBounds(20, 112, 551, 29);
		contentPane.add(lblArtWorkAvailable);
		
		JButton button_2 = new JButton("Art_ID");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(21, 152, 92, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Artist_ID");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(112, 152, 93, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Name");
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(201, 152, 96, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Year");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(296, 152, 92, 23);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Cat_ID");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(386, 152, 94, 23);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("Price");
		button_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_7.setBounds(479, 152, 92, 23);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("Gallery_ID");
		button_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_8.setBounds(570, 152, 93, 23);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("Exhib_ID");
		button_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_9.setBounds(662, 152, 91, 23);
		contentPane.add(button_9);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}
}
