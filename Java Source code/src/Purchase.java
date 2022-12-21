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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.UIManager;

public class Purchase extends JFrame {

	private JPanel contentPane;
	public static String msg=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Purchase frame = new Purchase(msg);
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
//	private JTextField ArtID;
	private JTable table;
	public Purchase(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPurchaseArtWork = new JLabel("PURCHASE ART WORK");
		lblPurchaseArtWork.setForeground(Color.WHITE);
		lblPurchaseArtWork.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurchaseArtWork.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblPurchaseArtWork.setBounds(24, 11, 733, 42);
		contentPane.add(lblPurchaseArtWork);

		JLabel lblArtWorkId = new JLabel("PURCHASE HISTORY:");
		lblArtWorkId.setForeground(Color.WHITE);
		lblArtWorkId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtWorkId.setBounds(149, 83, 268, 29);
		contentPane.add(lblArtWorkId);



		table = new JTable();
		table.setBounds(24, 171, 733, 200);
		contentPane.add(table);

		try{
			PreparedStatement smt3= connection.prepareStatement("select * from PURCHASE");
			ResultSet rs3=smt3.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs3));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton button_1 = new JButton("Art_ID");
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_1.setBounds(24, 150, 143, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("PURCHASE_ID");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(115, 150, 200, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("PAINTING_TYPE_ID");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(204, 150, 96, 23);
		contentPane.add(button_3);

		JButton button_4 = new JButton("USER_ID");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(299, 150, 164, 23);
		contentPane.add(button_4);

		JButton button_5 = new JButton("EXHIBITION_ID");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(462, 150, 143, 23);
		contentPane.add(button_5);

		JButton button_6 = new JButton("PAINTING_ID");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(605, 150, 152, 23);
		contentPane.add(button_6);

		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(UIManager.getColor("ToolTip.background"));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(0, 19, 100, 35);
		contentPane.add(lblUser);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JButton button_41 = new JButton("Back");
		button_41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Customer C = new Customer(msg);
				C.setVisible(true);
			}
		});
		contentPane.add(button_41);
		button_41.setBounds(630, 419, 100, 35);

		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(img));
				label.setBounds(0, 0, 784, 561);
				contentPane.add(label);

	}
}