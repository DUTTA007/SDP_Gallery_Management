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
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;

public class CustomerRegisteration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JTextField textFieldDateOfBirth;
	private JTextField textFieldPlaceOfBirth;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerRegisteration frame = new CustomerRegisteration();
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
	public CustomerRegisteration() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Member Type");
		lblNewLabel_1.setForeground(UIManager.getColor("scrollbar"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(114, 208, 132, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(UIManager.getColor("scrollbar"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(114, 241, 132, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setForeground(UIManager.getColor("scrollbar"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(115, 413, 132, 22);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("E-Mail");
		lblNewLabel_4.setForeground(UIManager.getColor("scrollbar"));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(114, 274, 132, 22);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Phone Number");
		lblNewLabel_5.setForeground(UIManager.getColor("scrollbar"));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(114, 307, 132, 22);
		contentPane.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(633, 211, 59, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(322, 241, 302, 22);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(321, 413, 302, 22);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(321, 274, 302, 22);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(322, 307, 302, 22);
		contentPane.add(textField_4);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("insert into CUSTOMER values (?,?,?,?,?)");
					smt.setString(1,textField.getText());
					smt.setString(2,textField_1.getText());
					smt.setString(3,textField_2.getText());
					smt.setString(4,textField_3.getText());
					smt.setInt(5,Integer.parseInt(textField_4.getText()));
						smt.execute();
						JOptionPane.showMessageDialog(null,"Customer registered");
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
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSubmit.setBounds(505, 454, 119, 29);
		contentPane.add(btnSubmit);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JLabel lblNewLabel_4_1 = new JLabel("Registration");
		lblNewLabel_4_1.setForeground(UIManager.getColor("window"));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4_1.setBounds(114, 144, 165, 29);
		contentPane.add(lblNewLabel_4_1);
				
				JLabel lblNewLabel_6 = new JLabel("_____________________________________________________________________________________");
				lblNewLabel_6.setBounds(114, 173, 553, 14);
				contentPane.add(lblNewLabel_6);
								
								JComboBox comboBoxMemberType = new JComboBox();
								comboBoxMemberType.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Artist"}));
								comboBoxMemberType.setBounds(322, 208, 301, 22);
								contentPane.add(comboBoxMemberType);
										
										JLabel lblNewLabel_1_1 = new JLabel("Date of birth");
										lblNewLabel_1_1.setForeground(SystemColor.scrollbar);
										lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
										lblNewLabel_1_1.setBounds(114, 342, 132, 22);
										contentPane.add(lblNewLabel_1_1);
												
												JLabel lblNewLabel_1_1_1 = new JLabel("Place of birth");
												lblNewLabel_1_1_1.setForeground(SystemColor.scrollbar);
												lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
												lblNewLabel_1_1_1.setBounds(114, 377, 132, 22);
												contentPane.add(lblNewLabel_1_1_1);
														
														textFieldDateOfBirth = new JTextField();
														textFieldDateOfBirth.setColumns(10);
														textFieldDateOfBirth.setBounds(321, 342, 302, 22);
														contentPane.add(textFieldDateOfBirth);
														
														textFieldPlaceOfBirth = new JTextField();
														textFieldPlaceOfBirth.setColumns(10);
														textFieldPlaceOfBirth.setBounds(321, 377, 302, 22);
														contentPane.add(textFieldPlaceOfBirth);
														
																JLabel label = new JLabel("");
																label.setIcon(new ImageIcon(img));
																label.setBounds(0, -1, 784, 561);
																contentPane.add(label);
	}
}
