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

public class DeleteCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField CustID;
	public static String msg=null;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCustomer frame = new DeleteCustomer(msg);
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
	public DeleteCustomer(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteCustomer = new JLabel("DELETE CUSTOMER");
		lblDeleteCustomer.setForeground(Color.WHITE);
		lblDeleteCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCustomer.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteCustomer.setBounds(34, 121, 715, 42);
		contentPane.add(lblDeleteCustomer);
		
		JLabel lblConfirmCustomerId = new JLabel("Confirm Customer ID");
		lblConfirmCustomerId.setForeground(Color.WHITE);
		lblConfirmCustomerId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConfirmCustomerId.setBounds(143, 198, 312, 22);
		contentPane.add(lblConfirmCustomerId);
		
		CustID = new JTextField();
		CustID.setColumns(10);
		CustID.setBounds(388, 198, 302, 22);
		contentPane.add(CustID);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String msg1;
					PreparedStatement smt= connection.prepareStatement("Delete from CUSTOMER where Customer_ID=?");
					msg1=CustID.getText();
					smt.setString(1,msg1);
					if(msg.equals(msg1)){
					smt.execute();
					JOptionPane.showMessageDialog(null,"Customer Deleted");}
					else 
						JOptionPane.showMessageDialog(null,"Incorrect Customer ID");
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
		button.setBounds(339, 276, 89, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}

}
