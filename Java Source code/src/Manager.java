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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class Manager extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static String msg=null;
	public static String MID=null;
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Manager frame = new Manager(msg);
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
	public Manager(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setForeground(Color.WHITE);
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblManager.setBounds(24, 11, 733, 42);
		contentPane.add(lblManager);
		JButton btnAddArtworkTo = new JButton("Add Artwork to Gallery");
		btnAddArtworkTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				MID=msg;
				AddArtwork AW= new AddArtwork(MID);
				AW.setVisible(true);
			}
		});
		btnAddArtworkTo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtworkTo.setBounds(24, 64, 251, 37);
		contentPane.add(btnAddArtworkTo);

		table = new JTable();
		table.setBounds(25, 162, 733, 227);
		contentPane.add(table);
		String CID=null;
		try{
			PreparedStatement smt= connection1.prepareStatement("select Category_ID from MANAGER where Manager_ID=?");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			if(rs.next())
				CID=rs.getString("Category_ID");
			PreparedStatement smt1= connection2.prepareStatement("select * from ARTWORK where Category_ID=? and (Gallery_ID is null or Exhibition_ID is null)");
			smt1.setString(1,CID);
			ResultSet rs1=smt1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_1.setBounds(641, 64, 116, 37);
		contentPane.add(button_1);

		JLabel lblArtWorkAvailable = new JLabel("Art work available in your category:");
		lblArtWorkAvailable.setForeground(Color.WHITE);
		lblArtWorkAvailable.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblArtWorkAvailable.setBounds(24, 112, 551, 29);
		contentPane.add(lblArtWorkAvailable);

		JButton btnAddArtworkTo_1 = new JButton("Add Artwork to Exhibition");
		btnAddArtworkTo_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				MID=msg;
				Exhi AW= new Exhi(MID);
				AW.setVisible(true);
			}
		});
		btnAddArtworkTo_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtworkTo_1.setBounds(330, 64, 257, 37);
		contentPane.add(btnAddArtworkTo_1);

		JButton button = new JButton("Art_ID");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button.setBounds(25, 140, 92, 23);
		contentPane.add(button);

		JButton button_2 = new JButton("Artist_ID");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(116, 140, 93, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Name");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(205, 140, 96, 23);
		contentPane.add(button_3);

		JButton button_4 = new JButton("Year");
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(300, 140, 92, 23);
		contentPane.add(button_4);

		JButton button_5 = new JButton("Cat_ID");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(390, 140, 94, 23);
		contentPane.add(button_5);

		JButton button_6 = new JButton("Price");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(483, 140, 92, 23);
		contentPane.add(button_6);

		JButton button_7 = new JButton("Gallery_ID");
		button_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_7.setBounds(574, 140, 93, 23);
		contentPane.add(button_7);

		JButton button_8 = new JButton("Exhib_ID");
		button_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_8.setBounds(666, 140, 91, 23);
		contentPane.add(button_8);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(UIManager.getColor("ToolTip.background"));
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setBounds(10, 11, 100, 35);
		contentPane.add(lblAdmin);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}
}
