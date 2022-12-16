import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;

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
public class Artist extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					String msg=null;
					Artist frame = new Artist(msg);
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
	public Artist(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(20, 254, 733, 214);
		contentPane.add(table);
		try{
			CallableStatement smt= connection.prepareCall("Call refreshArtwork(?)");
			smt.setString(1,msg);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("ARTIST");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 515, 129, 37);
		contentPane.add(lblNewLabel);

		JButton btnAddArtwork = new JButton("Add Artwork");
		btnAddArtwork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Artwork AW= new Artwork(msg);
				AW.setVisible(true);
			}
		});
		btnAddArtwork.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddArtwork.setBounds(50, 139, 153, 37);
		contentPane.add(btnAddArtwork);

		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteArtist DA= new DeleteArtist(msg);
				DA.setVisible(true);
			}
		});
		btnDeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteAccount.setBounds(528, 139, 167, 37);
		contentPane.add(btnDeleteAccount);

		JButton btnDeleteArtwork = new JButton("Delete Artwork");
		btnDeleteArtwork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteArtwork DA= new DeleteArtwork(msg);
				DA.setVisible(true);
			}
		});
		btnDeleteArtwork.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteArtwork.setBounds(203, 139, 167, 37);
		contentPane.add(btnDeleteArtwork);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBounds(662, 521, 112, 29);
		contentPane.add(btnLogout);

		JLabel lblNewLabel_1 = new JLabel("Art work created by you:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_1.setBounds(20, 180, 551, 29);
		contentPane.add(lblNewLabel_1);

		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				AddCategory DA= new AddCategory(msg);
				DA.setVisible(true);
			}
		});
		btnAddCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddCategory.setBounds(370, 139, 157, 37);
		contentPane.add(btnAddCategory);

		JButton btnNewButton = new JButton("Art_ID");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton.setBounds(20, 220, 92, 23);
		contentPane.add(btnNewButton);

		JButton btnArtistid = new JButton("Artist_ID");
		btnArtistid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnArtistid.setBounds(111, 220, 93, 23);
		contentPane.add(btnArtistid);

		JButton btnArtworkname = new JButton("Name");
		btnArtworkname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnArtworkname.setBounds(200, 220, 96, 23);
		contentPane.add(btnArtworkname);

		JButton btnYear = new JButton("Year");
		btnYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnYear.setBounds(295, 220, 92, 23);
		contentPane.add(btnYear);

		JButton btnExhibitionid = new JButton("Exhib_ID");
		btnExhibitionid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExhibitionid.setBounds(662, 220, 91, 23);
		contentPane.add(btnExhibitionid);

		JButton btnGalleryid = new JButton("Gallery_ID");
		btnGalleryid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGalleryid.setBounds(569, 220, 93, 23);
		contentPane.add(btnGalleryid);

		JButton btnPrice = new JButton("Price");
		btnPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPrice.setBounds(478, 220, 92, 23);
		contentPane.add(btnPrice);

		JButton btnCategoryid = new JButton("Cat_ID");
		btnCategoryid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCategoryid.setBounds(385, 220, 94, 23);
		contentPane.add(btnCategoryid);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);

	}
}
