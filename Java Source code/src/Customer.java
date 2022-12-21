import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String msg=null;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
//		try{
//			PreparedStatement smt= connection.prepareStatement("select * from artwork");
//			ResultSet rs=smt.executeQuery();
//			table.setModel(DbUtils.resultSetToTableModel(rs));
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}


	/**
	 * Create the frame.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public Customer(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 179, 286, 137);
		contentPane.add(scrollPane);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane_1.setViewportView(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);	
		
		try{
			PreparedStatement smt4= connection.prepareStatement("call MOST_SOLD_PAINTINGS");
			ResultSet rs4=smt4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs4));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
		// ==================== Delete
		JButton btnDelete = new JButton("Delete Account");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteCustomer DC= new DeleteCustomer(msg);
				DC.setVisible(true);
			}
		});
		
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(23, 513, 180, 37);
		contentPane.add(btnDelete);
		
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		//============================================== Purchase		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Purchase P= new Purchase(msg);
				P.setVisible(true);
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPurchase.setBounds(23, 64, 134, 37);
		contentPane.add(btnPurchase);
		
		//============================================== Exhibitions 
		JButton btnExhibitions = new JButton("Exhibitions");
		btnExhibitions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Exhibitions E= new Exhibitions(msg);
				E.setVisible(true);
			}
		});
		btnExhibitions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExhibitions.setBounds(156, 64, 153, 37);
		contentPane.add(btnExhibitions);
		//============================================== Art Works
		JButton btnArtWork = new JButton("Art Works");
		btnArtWork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				CustomerArtworkList E = null;
				try {
					E = new CustomerArtworkList(msg);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				E.setVisible(true);
			}
		});
		btnArtWork.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnArtWork.setBounds(308, 64, 153, 37);
		contentPane.add(btnArtWork);
		//============================================== Artists
				JButton btnArtists = new JButton("Artists");
				btnArtists.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						contentPane.setVisible(false);
						dispose();
						CustomerArtistList E= new CustomerArtistList(msg);
						E.setVisible(true);
						
					}
				});
				btnArtists.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnArtists.setBounds(462, 64, 139, 37);
				contentPane.add(btnArtists);
				
		//============================================== Log out
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
				refreshTable();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogOut.setBounds(600, 64, 116, 37);
		contentPane.add(btnLogOut);
						
						JLabel lblNewLabel = new JLabel("Most Sold Painting");
						lblNewLabel.setForeground(new Color(255, 255, 255));
						lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
						lblNewLabel.setBounds(23, 131, 220, 37);
						contentPane.add(lblNewLabel);
						
						
						//============================================== Galleries
						
						
								JLabel lblBack = new JLabel("");
								lblBack.setIcon(new ImageIcon(img));
								lblBack.setBounds(0, 0, 784, 561);
								contentPane.add(lblBack);
	}
}