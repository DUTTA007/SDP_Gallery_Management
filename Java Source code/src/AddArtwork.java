import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddArtwork extends JFrame {

	private JPanel contentPane;
	private JTextField tbxName;
	String paintingTypeName;
	int ptype_id;

	java.sql.Connection connection = Connection.Dbconnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddArtwork frame = new AddArtwork(msg);
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
	public static String msg = null;
	public static String GID = null;
	java.sql.Connection connection1 = Connection.Dbconnection();
	java.sql.Connection connection2 = Connection.Dbconnection();
	private JTextField tbxDate;
	private JTextField tbxPrice;

	public AddArtwork(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddNewArt = new JLabel("Add NEW ART WORK");
		lblAddNewArt.setForeground(Color.WHITE);
		lblAddNewArt.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddNewArt.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblAddNewArt.setBounds(136, 165, 398, 42);
		contentPane.add(lblAddNewArt);

		JLabel lblArtId = new JLabel("Art Name");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtId.setBounds(178, 224, 90, 27);
		contentPane.add(lblArtId);

		JComboBox<String> cBoxType = new JComboBox<String>();
		cBoxType.setBounds(278, 311, 302, 30);
		contentPane.add(cBoxType);
		try {

			PreparedStatement pType = connection
					.prepareStatement("SELECT PAINTING_TYPE_NAME FROM artgallery.painting_type");
			ResultSet rs4 = pType.executeQuery();
			while (rs4.next()) {
				paintingTypeName = rs4.getString("PAINTING_TYPE_NAME");
				cBoxType.addItem(paintingTypeName);
			}
		} catch (Exception a) {

		}

		tbxName = new JTextField();
		tbxName.setColumns(10);
		tbxName.setBounds(278, 226, 302, 30);
		contentPane.add(tbxName);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					PreparedStatement pNanme = connection
							.prepareStatement("SELECT PAINTING_TYPE_ID FROM painting_type where PAINTING_TYPE_NAME=?");
					pNanme.setString(1, cBoxType.getSelectedItem().toString());
					ResultSet rs5 = pNanme.executeQuery();
					while (rs5.next()) {
						ptype_id = rs5.getInt("PAINTING_TYPE_ID");
					}

					PreparedStatement smt = connection.prepareStatement("insert " + "into  painting("
							+ "PAINTING_TITLE, " + "PAINTING_DATE, " + "PAINTING_TYPE_ID, " + "USER_ID, "
							+ "PAINTING_PURCHASE_FLG, " + "PAINTING_PRICE_EURO) " + "values (?,?,?,?,?,?)");
					smt.setString(1, tbxName.getText());
					smt.setString(2, tbxDate.getText());
					smt.setInt(3, ptype_id);
					smt.setString(4, msg);
					smt.setInt(5, 0);
					smt.setString(6, tbxPrice.getText());
					
					System.out.println(smt);

					smt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Succesfully Added");
					smt.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				contentPane.setVisible(false);
				dispose();
				Artist M = new Artist(msg);
				M.setVisible(true);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(480, 416, 100, 30);
		contentPane.add(btnAdd);

		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
		Image img = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();

		JLabel lblArtDate = new JLabel("Date");
		lblArtDate.setForeground(Color.WHITE);
		lblArtDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtDate.setBounds(178, 269, 90, 27);
		contentPane.add(lblArtDate);

		JLabel lblArtType = new JLabel("Type");
		lblArtType.setForeground(Color.WHITE);
		lblArtType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtType.setBounds(178, 307, 90, 27);
		contentPane.add(lblArtType);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(178, 345, 90, 27);
		contentPane.add(lblPrice);

		tbxDate = new JTextField();
		tbxDate.setText("2022-10-10");
		tbxDate.setColumns(10);
		tbxDate.setBounds(278, 267, 302, 30);
		contentPane.add(tbxDate);

		tbxPrice = new JTextField();
		tbxPrice.setColumns(10);
		tbxPrice.setBounds(278, 352, 302, 30);
		contentPane.add(tbxPrice);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(img));
		label_2.setBounds(0, 0, 784, 561);
		contentPane.add(label_2);
	}
}