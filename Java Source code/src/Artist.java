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
import javax.swing.JScrollPane;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 254, 733, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try{
			CallableStatement smt= connection.prepareCall("Call ARTIST_DETAILS(?)");
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
				AddArtwork AW= new AddArtwork(msg);
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
		btnDeleteAccount.setBounds(573, 139, 167, 37);
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

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);

	}
}
