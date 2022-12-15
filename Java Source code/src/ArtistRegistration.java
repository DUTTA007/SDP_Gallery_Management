import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

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

public class ArtistRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField ArtistID;
	private JTextField Name;
	private JTextField PN;
	private JTextField BP;
	private JTextField DOB;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ArtistRegistration frame = new ArtistRegistration();
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
	public ArtistRegistration() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblArtistRegistration = new JLabel("ARTIST REGISTRATION");
		lblArtistRegistration.setForeground(Color.WHITE);
		lblArtistRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistRegistration.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblArtistRegistration.setBounds(35, 11, 715, 42);
		contentPane.add(lblArtistRegistration);

		JLabel lblArtistId = new JLabel("Artist ID");
		lblArtistId.setForeground(Color.WHITE);
		lblArtistId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtistId.setBounds(140, 84, 161, 22);
		contentPane.add(lblArtistId);

		ArtistID = new JTextField();
		ArtistID.setColumns(10);
		ArtistID.setBounds(347, 84, 302, 22);
		contentPane.add(ArtistID);

		JLabel label_1 = new JLabel("Name");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_1.setBounds(140, 126, 161, 22);
		contentPane.add(label_1);

		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(347, 126, 302, 22);
		contentPane.add(Name);

		JLabel label_2 = new JLabel("Phone Number");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_2.setBounds(140, 259, 161, 22);
		contentPane.add(label_2);

		PN = new JTextField();
		PN.setColumns(10);
		PN.setBounds(347, 259, 302, 22);
		contentPane.add(PN);

		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					PreparedStatement smt= connection.prepareStatement("insert into ARTIST values (?,?,?,?,?)");
					smt.setString(1,ArtistID.getText());
					smt.setString(2,Name.getText());
					smt.setString(3,BP.getText());
					smt.setString(4,DOB.getText());
					smt.setInt(5,Integer.parseInt(PN.getText()));
						smt.execute();
						JOptionPane.showMessageDialog(null,"Artist registered");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(347, 339, 89, 23);
		contentPane.add(button);

		JLabel lblBirthPlace = new JLabel("Birth Place");
		lblBirthPlace.setForeground(Color.WHITE);
		lblBirthPlace.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblBirthPlace.setBounds(140, 172, 161, 22);
		contentPane.add(lblBirthPlace);

		BP = new JTextField();
		BP.setColumns(10);
		BP.setBounds(347, 172, 302, 22);
		contentPane.add(BP);

		JLabel lblDob = new JLabel("DOB");
		lblDob.setForeground(Color.WHITE);
		lblDob.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblDob.setBounds(140, 218, 161, 22);
		contentPane.add(lblDob);

		DOB = new JTextField();
		DOB.setColumns(10);
		DOB.setBounds(347, 218, 302, 22);
		contentPane.add(DOB);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}
}
