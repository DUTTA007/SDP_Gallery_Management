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

public class DeleteGallery extends JFrame {

	private JPanel contentPane;
	private JTextField GalleryID;
	static String msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteGallery frame = new DeleteGallery(msg);
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
	java.sql.Connection connection=Connection.Dbconnection();
	public DeleteGallery(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteGallery = new JLabel("DELETE GALLERY");
		lblDeleteGallery.setForeground(Color.WHITE);
		lblDeleteGallery.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteGallery.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteGallery.setBounds(24, 109, 715, 42);
		contentPane.add(lblDeleteGallery);
		
		JLabel lblConfirmGalleryId = new JLabel("Confirm Gallery ID");
		lblConfirmGalleryId.setForeground(Color.WHITE);
		lblConfirmGalleryId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConfirmGalleryId.setBounds(145, 187, 312, 22);
		contentPane.add(lblConfirmGalleryId);
		
		GalleryID = new JTextField();
		GalleryID.setColumns(10);
		GalleryID.setBounds(375, 187, 302, 22);
		contentPane.add(GalleryID);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("Delete from CUSTOMER where Customer_ID=?");
					String msg1=GalleryID.getText();
					smt.setString(1,msg1);
					if (msg.equals(msg1)){
					smt.execute();
					JOptionPane.showMessageDialog(null,"Gallery Deleted");
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
				Login.main(null);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(319, 255, 89, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 476);
		contentPane.add(label);
	}

}
