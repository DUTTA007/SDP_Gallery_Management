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

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String msg=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer(msg);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void refreshTable(){
		try{
			PreparedStatement smt= connection.prepareStatement("select * from ARTWORK");
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public Customer(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomer = new JLabel("CUSTOMER");
		lblCustomer.setForeground(Color.WHITE);
		lblCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomer.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblCustomer.setBounds(23, 11, 733, 42);
		contentPane.add(lblCustomer);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Purchase P= new Purchase(msg);
				P.setVisible(true);
			}
		});
		btnPurchase.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnPurchase.setBounds(23, 64, 134, 37);
		contentPane.add(btnPurchase);
		
		JButton btnExhibitions = new JButton("Exhibitions");
		btnExhibitions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Exhibitions E= new Exhibitions(msg);
				E.setVisible(true);
			}
		});
		btnExhibitions.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExhibitions.setBounds(156, 64, 153, 37);
		contentPane.add(btnExhibitions);
		
		table = new JTable();
		table.setBounds(23, 163, 733, 225);
		contentPane.add(table);
		try{
			PreparedStatement smt4= connection.prepareStatement("select * from ITEMSPURCHASED where CustomerID=?");
			smt4.setString(1,msg);
			ResultSet rs4=smt4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs4));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JButton button = new JButton("Delete Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteCustomer DC= new DeleteCustomer(msg);
				DC.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(576, 64, 180, 37);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
				refreshTable();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_1.setBounds(461, 64, 116, 37);
		contentPane.add(button_1);
		
		JLabel lblItemsPurchasedBy = new JLabel("Items purchased by you:");
		lblItemsPurchasedBy.setForeground(Color.WHITE);
		lblItemsPurchasedBy.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblItemsPurchasedBy.setBounds(23, 112, 496, 24);
		contentPane.add(lblItemsPurchasedBy);
		
		JButton button_2 = new JButton("Art_ID");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(21, 140, 106, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Artist_ID");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(126, 140, 107, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Name");
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(231, 140, 107, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Year");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(337, 140, 116, 23);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Cat_ID");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(442, 140, 105, 23);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("Price");
		button_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_7.setBounds(546, 140,105, 23);
		contentPane.add(button_7);
		
		JButton btnCustomerid = new JButton("Customer_ID");
		btnCustomerid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCustomerid.setBounds(650, 140, 108, 23);
		contentPane.add(btnCustomerid);
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		
		JButton btnGalleries = new JButton("Galleries");
		btnGalleries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Gal E= new Gal(msg);
				E.setVisible(true);
			}
		});
		btnGalleries.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnGalleries.setBounds(308, 64, 153, 37);
		contentPane.add(btnGalleries);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}
}
