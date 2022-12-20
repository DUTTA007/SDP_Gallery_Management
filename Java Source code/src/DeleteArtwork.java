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

public class DeleteArtwork extends JFrame {

	private JPanel contentPane;
	private JTextField ArtID;
	private JTextField ArtistID;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static String msg;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DeleteArtwork frame = new DeleteArtwork(msg);
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
	public DeleteArtwork(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeleteArtWork = new JLabel("DELETE ART WORK");
		lblDeleteArtWork.setForeground(Color.WHITE);
		lblDeleteArtWork.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteArtWork.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteArtWork.setBounds(34, 104, 715, 42);
		contentPane.add(lblDeleteArtWork);

		JLabel label_1 = new JLabel("Art ID");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_1.setBounds(139, 177, 161, 22);
		contentPane.add(label_1);

		ArtID = new JTextField();
		ArtID.setColumns(10);
		ArtID.setBounds(346, 177, 302, 22);
		contentPane.add(ArtID);

		JLabel label_2 = new JLabel("Confirm Artist ID");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_2.setBounds(139, 240, 212, 22);
		contentPane.add(label_2);

		ArtistID = new JTextField();
		ArtistID.setColumns(10);
		ArtistID.setBounds(346, 240, 302, 22);
		contentPane.add(ArtistID);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String msg1;
					PreparedStatement smt= connection.prepareStatement("Delete from ARTWORK where Art_ID=? and Artist_ID=?");
					smt.setString(1,ArtID.getText());
					msg1=ArtistID.getText();
					smt.setString(2,ArtistID.getText());
					if(msg1.equals(msg)){
					smt.execute();
					JOptionPane.showMessageDialog(null,"Art work Deleted");
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect Artist ID");

					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Artist A= new Artist(msg);
				A.setVisible(true);
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(310, 303, 89, 23);
		contentPane.add(btnDelete);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}
}
