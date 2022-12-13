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

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Exhibitions extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JButton btnBack;
	private JLabel lblUpcomingExhibitions;
	public static String msg=null;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JLabel label;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exhibitions frame = new Exhibitions(msg);
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
	public Exhibitions(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExhibitions = new JLabel("EXHIBITIONS");
		lblExhibitions.setForeground(Color.WHITE);
		lblExhibitions.setHorizontalAlignment(SwingConstants.CENTER);
		lblExhibitions.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblExhibitions.setBounds(21, 11, 733, 42);
		contentPane.add(lblExhibitions);
		
		table = new JTable();
		table.setBounds(21, 114, 733, 247);
		contentPane.add(table);
		
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITIONS");
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.setVisible(false);
					dispose();
					Customer C= new Customer(msg);
					C.setVisible(true);
				}
			});
			btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnBack.setBounds(348, 372, 89, 23);
			contentPane.add(btnBack);
			
			lblUpcomingExhibitions = new JLabel("Upcoming exhibitions:");
			lblUpcomingExhibitions.setForeground(Color.WHITE);
			lblUpcomingExhibitions.setFont(new Font("Times New Roman", Font.BOLD, 24));
			lblUpcomingExhibitions.setBounds(21, 64, 496, 24);
			contentPane.add(lblUpcomingExhibitions);
			Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
			
			button = new JButton("Exhi_ID");
			button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button.setBounds(21, 92, 105, 23);
			contentPane.add(button);
			
			button_1 = new JButton("Date");
			button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_1.setBounds(124, 92, 107, 23);
			contentPane.add(button_1);
			
			button_2 = new JButton("Location");
			button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_2.setBounds(230, 92, 106, 23);
			contentPane.add(button_2);
			
			button_3 = new JButton("Time");
			button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_3.setBounds(335, 92, 106, 23);
			contentPane.add(button_3);
			
			button_4 = new JButton("Day");
			button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_4.setBounds(440, 92, 105, 23);
			contentPane.add(button_4);
			
			button_5 = new JButton("Reg_Fee");
			button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_5.setBounds(544, 92, 106, 23);
			contentPane.add(button_5);
			
			button_6 = new JButton("NoOfArt");
			button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_6.setBounds(648, 92, 106, 23);
			contentPane.add(button_6);
			
			label = new JLabel("");
			label.setIcon(new ImageIcon(img));
			label.setBounds(0, 0, 779, 411);
			contentPane.add(label);
	}

}
