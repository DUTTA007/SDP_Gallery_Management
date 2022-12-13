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

public class DeleteManager extends JFrame {

	private JPanel contentPane;
	private JTextField MID;
	private JTextField GID;

	/**
	 * Launch the application.
	 */
	public static String msg;
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteManager frame = new DeleteManager(msg);
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
	public DeleteManager(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteManager = new JLabel("DELETE MANAGER");
		lblDeleteManager.setForeground(Color.WHITE);
		lblDeleteManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteManager.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteManager.setBounds(36, 88, 715, 42);
		contentPane.add(lblDeleteManager);
		
		JLabel lblManagerId = new JLabel("Manager ID");
		lblManagerId.setForeground(Color.WHITE);
		lblManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblManagerId.setBounds(141, 161, 161, 29);
		contentPane.add(lblManagerId);
		
		MID = new JTextField();
		MID.setColumns(10);
		MID.setBounds(348, 161, 302, 22);
		contentPane.add(MID);
		
		JLabel lblConfirmGalleryId = new JLabel("Confirm Gallery ID");
		lblConfirmGalleryId.setForeground(Color.WHITE);
		lblConfirmGalleryId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConfirmGalleryId.setBounds(141, 224, 212, 29);
		contentPane.add(lblConfirmGalleryId);
		
		GID = new JTextField();
		GID.setColumns(10);
		GID.setBounds(348, 224, 302, 22);
		contentPane.add(GID);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("Delete from MANAGER where Manager_ID=? and Gallery_ID=?");
					smt.setString(1,MID.getText());
					String msg1=GID.getText();
					smt.setString(2,msg1);
					if(msg.equals(msg1)){
					smt.execute();
					JOptionPane.showMessageDialog(null,"Manager Deleted");
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect Gallery ID");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Man G= new Man(msg);
				G.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(303, 288, 89, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 456);
		contentPane.add(label);
	}

}
