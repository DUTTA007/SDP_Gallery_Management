import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER REGISTRATION");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewLabel.setBounds(26, 24, 715, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(134, 106, 161, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(134, 148, 161, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(134, 191, 161, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("E-Mail ID");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(134, 237, 161, 22);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone Number");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(134, 281, 161, 22);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(341, 106, 302, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(341, 148, 302, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(341, 191, 302, 22);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(341, 237, 302, 22);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(341, 281, 302, 22);
		contentPane.add(textField_4);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
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
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSubmit.setBounds(314, 380, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, -1, 779, 412);
		contentPane.add(label);
	}
}
