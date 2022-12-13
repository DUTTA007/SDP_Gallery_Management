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

public class DeleteArtist extends JFrame {

	private JPanel contentPane;
	private JTextField ArtistID;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static String msg;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteArtist frame = new DeleteArtist(msg);
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
	public DeleteArtist(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteArtist = new JLabel("DELETE ARTIST");
		lblDeleteArtist.setForeground(Color.WHITE);
		lblDeleteArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteArtist.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteArtist.setBounds(21, 133, 715, 42);
		contentPane.add(lblDeleteArtist);
		
		JLabel lblConfirmArtistId = new JLabel("Confirm Artist ID");
		lblConfirmArtistId.setForeground(Color.WHITE);
		lblConfirmArtistId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConfirmArtistId.setBounds(146, 206, 207, 22);
		contentPane.add(lblConfirmArtistId);
		
		ArtistID = new JTextField();
		ArtistID.setColumns(10);
		ArtistID.setBounds(395, 206, 302, 22);
		contentPane.add(ArtistID);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String msg1;
					PreparedStatement smt= connection.prepareStatement("Delete from ARTIST where Artist_ID=?");
					msg1=ArtistID.getText();
					smt.setString(1,msg1);
					if(msg1.equals(msg)){
					smt.execute();
					JOptionPane.showMessageDialog(null,"Artist Deleted");
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect Artist ID");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(335, 285, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 511);
		contentPane.add(label);
	}

}
