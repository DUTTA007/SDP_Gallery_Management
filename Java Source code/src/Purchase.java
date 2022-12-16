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
	public void refreshTable(){
		try{
			PreparedStatement smt3= connection.prepareStatement("select * from ARTWORK");
			ResultSet rs3=smt3.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs3));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JTextField ArtID;
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

		JLabel lblArtWorkId = new JLabel("Art work ID");
		lblArtWorkId.setForeground(Color.WHITE);
		lblArtWorkId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtWorkId.setBounds(79, 84, 164, 29);
		contentPane.add(lblArtWorkId);

		ArtID = new JTextField();
		ArtID.setColumns(10);
		ArtID.setBounds(253, 84, 286, 29);
		contentPane.add(ArtID);

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String Art_ID = null,Artist_ID = null,ArtName = null,Category_ID = null;
					int year = 0,price = 0;
					PreparedStatement smt= connection.prepareStatement("select * from ARTWORK where Art_ID=?");
					smt.setString(1,ArtID.getText());
					ResultSet rs=smt.executeQuery();
					while(rs.next()){
					Art_ID=rs.getString("Art_ID");
					Artist_ID=rs.getString("Artist_ID");
					ArtName=rs.getString("Artwork_Name");
					year=rs.getInt("Year");
					Category_ID=rs.getString("Category_ID");
					price=rs.getInt("Price");
					}
					PreparedStatement smt1= connection.prepareStatement("insert into ITEMSPURCHASED values (?,?,?,?,?,?,?)");
					smt1.setString(1,Art_ID);
					smt1.setString(2,Artist_ID);
					smt1.setString(3,ArtName);
					smt1.setInt(4,year);
					smt1.setString(5,Category_ID);
					smt1.setInt(6,price);
					smt1.setString(7,msg);
					smt1.execute();
					PreparedStatement smt2= connection.prepareStatement("Delete from ARTWORK where Art_ID=?");
					smt2.setString(1,ArtID.getText());
					smt2.execute();
					JOptionPane.showMessageDialog(null,"Item Purchased");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,ex);
				}
				refreshTable();
			}
		});
		btnPurchase.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnPurchase.setBounds(583, 85, 131, 28);
		contentPane.add(btnPurchase);

		table = new JTable();
		table.setBounds(24, 171, 733, 200);
		contentPane.add(table);

		try{
			PreparedStatement smt3= connection.prepareStatement("select * from ARTWORK");
			ResultSet rs3=smt3.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs3));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Customer C= new Customer(msg);
				C.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(352, 382, 89, 23);
		contentPane.add(button);

		JLabel lblArtWorksAvailable = new JLabel("Art works available:");
		lblArtWorksAvailable.setForeground(Color.WHITE);
		lblArtWorksAvailable.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblArtWorksAvailable.setBounds(24, 120, 496, 24);
		contentPane.add(lblArtWorksAvailable);

		JButton button_1 = new JButton("Art_ID");
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_1.setBounds(24, 150, 92, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Artist_ID");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(115, 150, 93, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Name");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(204, 150, 96, 23);
		contentPane.add(button_3);

		JButton button_4 = new JButton("Year");
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(299, 150, 92, 23);
		contentPane.add(button_4);

		JButton button_5 = new JButton("Cat_ID");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(389, 150, 94, 23);
		contentPane.add(button_5);

		JButton button_6 = new JButton("Price");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(482, 150, 92, 23);
		contentPane.add(button_6);

		JButton button_7 = new JButton("Gallery_ID");
		button_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_7.setBounds(573, 150, 93, 23);
		contentPane.add(button_7);

		JButton button_8 = new JButton("Exhib_ID");
		button_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_8.setBounds(665, 150, 91, 23);
		contentPane.add(button_8);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(UIManager.getColor("ToolTip.background"));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(10, 11, 100, 35);
		contentPane.add(lblUser);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}

}
