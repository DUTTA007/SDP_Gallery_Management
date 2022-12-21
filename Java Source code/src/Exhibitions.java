import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
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

public class Exhibitions extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JLabel lblUpcomingExhibitions;
	public static String msg=null;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	
	private JLabel label;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblExhibitions = new JLabel("EXHIBITIONS");
		lblExhibitions.setForeground(Color.WHITE);
		lblExhibitions.setHorizontalAlignment(SwingConstants.CENTER);
		lblExhibitions.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblExhibitions.setBounds(21, 11, 733, 42);
		contentPane.add(lblExhibitions);

		table = new JTable();
		table.setBounds(21, 233, 733, 247);
		contentPane.add(table);
  
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITION");
			ResultSet rs=smt.executeQuery();
		       System.out.println("exhibitions" + rs +smt);
			table.setModel(DbUtils.resultSetToTableModel(rs));		
		}  
		catch(Exception e){
			e.printStackTrace();
		}

			lblUpcomingExhibitions = new JLabel("Upcoming exhibitions:");
			lblUpcomingExhibitions.setForeground(Color.WHITE);
			lblUpcomingExhibitions.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblUpcomingExhibitions.setBounds(21, 64, 496, 24);
			contentPane.add(lblUpcomingExhibitions);
			Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
	
			button = new JButton("Exhi_ID");
			button.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button.setBounds(21, 211, 145, 23);
			contentPane.add(button);

			button_1 = new JButton("EXHI_TITLE");
			button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button_1.setBounds(163, 211, 145, 23);
			contentPane.add(button_1);

			button_2 = new JButton("ESTART_DATE");
			button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button_2.setBounds(280, 211, 169, 23);
			contentPane.add(button_2);

			button_3 = new JButton("EEND_DATE");
			button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button_3.setBounds(385, 211, 221, 23);
			contentPane.add(button_3);

			button_4 = new JButton("PAINTING_ID");
			button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button_4.setBounds(598, 211, 156, 23);
			contentPane.add(button_4);  
			              
			JLabel lblUser = new JLabel("User");
			lblUser.setForeground(UIManager.getColor("ToolTip.background"));
			lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblUser.setBounds(10, 11, 100, 35);
			contentPane.add(lblUser);
			
			JButton button_4 = new JButton("Back");
			button_4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contentPane.setVisible(false);
					dispose();
					Customer C = new Customer(msg);
					C.setVisible(true);
				}
			});
			button_4.setBounds(616, 63, 148, 42);
			contentPane.add(button_4);
			
		
						label = new JLabel("");
						label.setIcon(new ImageIcon(img));
						label.setBounds(0, 0, 784, 561);
						contentPane.add(label);
	}
}