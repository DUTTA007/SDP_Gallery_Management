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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Man extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String msg;
	
	public void refreshTable(String msg){
		try{
			PreparedStatement smt= connection.prepareStatement("select * from MANAGER where Gallery_ID=?");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Man frame = new Man(msg);
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
	public Man(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManagers = new JLabel("MANAGERS");
		lblManagers.setForeground(Color.WHITE);
		lblManagers.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagers.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblManagers.setBounds(10, 83, 759, 36);
		contentPane.add(lblManagers);
		
		JButton btnNewButton = new JButton("Add Manager");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				AddManager AM= new AddManager(msg);
				AM.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(30, 130, 155, 33);
		contentPane.add(btnNewButton);
		
		JButton btnDeleteManager = new JButton("Delete Manager");
		btnDeleteManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteManager DM= new DeleteManager(msg);
				DM.setVisible(true);
			}
		});
		btnDeleteManager.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeleteManager.setBounds(303, 130, 181, 33);
		contentPane.add(btnDeleteManager);
		
		JLabel lblManagersWorkingIn = new JLabel("Managers working in your Gallery are:");
		lblManagersWorkingIn.setForeground(Color.WHITE);
		lblManagersWorkingIn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblManagersWorkingIn.setBounds(26, 174, 588, 28);
		contentPane.add(lblManagersWorkingIn);
		
		table = new JTable();
		table.setBounds(26, 235, 732, 165);
		contentPane.add(table);
		try{
			PreparedStatement smt= connection.prepareStatement("select * from MANAGER where Gallery_ID=?");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			refreshTable(msg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Gallery G= new Gallery(msg);
				G.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnBack.setBounds(669, 130, 89, 33);
		contentPane.add(btnBack);
		
		JButton btnManagerid = new JButton("SSN");
		btnManagerid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnManagerid.setBounds(26, 213, 92, 23);
		contentPane.add(btnManagerid);
		
		JButton btnName = new JButton("Name");
		btnName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnName.setBounds(117, 213, 92, 23);
		contentPane.add(btnName);
		
		JButton btnPhno = new JButton("PhNo");
		btnPhno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPhno.setBounds(206, 213, 95, 23);
		contentPane.add(btnPhno);
		
		JButton btnStartdate = new JButton("StartDate");
		btnStartdate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnStartdate.setBounds(300, 213, 92, 23);
		contentPane.add(btnStartdate);
		
		JButton btnSalary = new JButton("Salary");
		btnSalary.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalary.setBounds(391, 213, 93, 23);
		contentPane.add(btnSalary);
		
		JButton btnGender = new JButton("Gender");
		btnGender.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGender.setBounds(483, 213, 92, 23);
		contentPane.add(btnGender);
		
		JButton btnCatid = new JButton("Cat_ID");
		btnCatid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCatid.setBounds(574, 213, 93, 23);
		contentPane.add(btnCatid);
		
		JButton btnGalleryid = new JButton("Gallery_ID");
		btnGalleryid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGalleryid.setBounds(666, 213, 92, 23);
		contentPane.add(btnGalleryid);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(-96, 0, 875, 411);
		contentPane.add(label);
	}
}
