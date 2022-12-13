import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	java.sql.Connection connection=Connection.Dbconnection();
	public static String msg=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gal frame = new Gal(msg);
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
	public Gal(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGallery = new JLabel("GALLERY");
		lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
		lblGallery.setForeground(Color.WHITE);
		lblGallery.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblGallery.setBounds(22, 11, 733, 42);
		contentPane.add(lblGallery);
		
		JLabel lblTheGalleriesYou = new JLabel("List of all galleries");
		lblTheGalleriesYou.setForeground(Color.WHITE);
		lblTheGalleriesYou.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTheGalleriesYou.setBounds(22, 64, 496, 24);
		contentPane.add(lblTheGalleriesYou);
		
		table = new JTable();
		table.setBounds(22, 129, 733, 237);
		contentPane.add(table);
		try{
			PreparedStatement smt4= connection.prepareStatement("select * from GALLERY");
			ResultSet rs4=smt4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs4));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JButton btnGallerid = new JButton("Galler_ID");
		btnGallerid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGallerid.setBounds(22, 107, 183, 23);
		contentPane.add(btnGallerid);
		
		JButton btnGalleryname = new JButton("Gallery_Name");
		btnGalleryname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGalleryname.setBounds(204, 107, 184, 23);
		contentPane.add(btnGalleryname);
		
		JButton btnGallerylocation = new JButton("Gallery_Location");
		btnGallerylocation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGallerylocation.setBounds(385, 107, 187, 23);
		contentPane.add(btnGallerylocation);
		
		JButton btnPhno = new JButton("PhNo");
		btnPhno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPhno.setBounds(570, 107, 185, 23);
		contentPane.add(btnPhno);
		
		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Customer C= new Customer(msg);
				C.setVisible(true);
			}
		});
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_4.setBounds(343, 377, 89, 23);
		contentPane.add(button_4);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}
}
