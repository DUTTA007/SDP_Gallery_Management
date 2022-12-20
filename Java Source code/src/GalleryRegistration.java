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
import java.awt.Toolkit;

public class GalleryRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GalleryRegistration frame = new GalleryRegistration();
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
	public GalleryRegistration() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGalleryRegistration = new JLabel("GALLERY REGISTRATION");
		lblGalleryRegistration.setForeground(Color.WHITE);
		lblGalleryRegistration.setHorizontalAlignment(SwingConstants.LEFT);
		lblGalleryRegistration.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblGalleryRegistration.setBounds(109, 189, 587, 42);
		contentPane.add(lblGalleryRegistration);

		JLabel lblGalleryId = new JLabel("Gallery ID");
		lblGalleryId.setForeground(Color.WHITE);
		lblGalleryId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGalleryId.setBounds(109, 269, 161, 22);
		contentPane.add(lblGalleryId);

		JLabel label_2 = new JLabel("Name");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(109, 302, 161, 22);
		contentPane.add(label_2);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setForeground(Color.WHITE);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocation.setBounds(109, 335, 161, 22);
		contentPane.add(lblLocation);

		JLabel label_4 = new JLabel("Phone Number");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(108, 371, 161, 22);
		contentPane.add(label_4);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(316, 269, 302, 22);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(316, 302, 302, 22);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(316, 335, 302, 22);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(315, 371, 302, 22);
		contentPane.add(textField_3);

		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("insert into GALLERY values (?,?,?,?)");
					smt.setString(1,textField.getText());
					smt.setString(2,textField_1.getText());
					smt.setString(3,textField_2.getText());
					smt.setString(4,textField_3.getText());
						smt.execute();
						JOptionPane.showMessageDialog(null,"Gallery registered");
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(501, 429, 117, 33);
		contentPane.add(button);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}

}
