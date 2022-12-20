import java.awt.Color;
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

		JLabel label = new JLabel("NEW ART WORK");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD, 36));
		label.setBounds(30, 57, 398, 42);
		contentPane.add(label);

		JLabel lblArtId = new JLabel("Art Name");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtId.setBounds(62, 142, 90, 27);
		contentPane.add(lblArtId);

		tbxName = new JTextField();
		tbxName.setColumns(10);
		tbxName.setBounds(162, 144, 302, 30);
		contentPane.add(tbxName);
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pType = connection.prepareStatement("SELECT * FROM artgallery.painting_type");
					pType.execute();
				    ArrayList<String> pTypeList = new ArrayList<String>();
				    var aaa = pType;
				    System.out.println(aaa);

				    

					
					
					
					PreparedStatement smt = connection.prepareStatement("insert " + "into  painting("
							+ "PAINTING_TITLE, " + "PAINTING_DATE, " + "PAINTING_TYPE_ID, " + "USER_ID, "
							+ "PAINTING_PURCHASE_FLG, " + "PAINTING_PRICE_EURO) " + "values (?,?,?,?,?,?)");
					smt.setString(1, tbxName.getText());
					smt.setString(2, tbxDate.getText());
					smt.setString(3, "1");
					smt.setString(4, "2");
					smt.setInt(5, 0);
					smt.setString(6, tbxPrice.getText());

					smt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Succesfully registered");
					smt.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				contentPane.setVisible(false);
				dispose();
				Manager M = new Manager(msg);
				M.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(364, 334, 100, 30);
		contentPane.add(button);

		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
		Image img = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();

		JLabel lblArtDate = new JLabel("Date");
		lblArtDate.setForeground(Color.WHITE);
		lblArtDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtDate.setBounds(62, 187, 90, 27);
		contentPane.add(lblArtDate);

		JLabel lblArtType = new JLabel("Type");
		lblArtType.setForeground(Color.WHITE);
		lblArtType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtType.setBounds(62, 225, 90, 27);
		contentPane.add(lblArtType);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(62, 263, 90, 27);
		contentPane.add(lblPrice);

		tbxDate = new JTextField();
		tbxDate.setText("2022-10-10");
		tbxDate.setColumns(10);
		tbxDate.setBounds(162, 185, 302, 30);
		contentPane.add(tbxDate);

		tbxPrice = new JTextField();
		tbxPrice.setText("100");
		tbxPrice.setColumns(10);
		tbxPrice.setBounds(162, 270, 302, 30);
		contentPane.add(tbxPrice);

		JComboBox cBoxType = new JComboBox();
		cBoxType.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		cBoxType.setBounds(162, 229, 302, 30);
		contentPane.add(cBoxType);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(img));
		label_2.setBounds(0, 0, 784, 561);
		contentPane.add(label_2);
	}
}
