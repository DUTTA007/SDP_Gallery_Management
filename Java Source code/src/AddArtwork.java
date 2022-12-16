import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class AddArtwork extends JFrame {

	private JPanel contentPane;
	private JTextField ArtID;

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
	public static String msg=null;
	public static String GID=null;
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
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
		label.setBounds(70, 103, 699, 42);
		contentPane.add(label);

		JLabel lblArtId = new JLabel("Art ID");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtId.setBounds(70, 296, 90, 27);
		contentPane.add(lblArtId);

		ArtID = new JTextField();
		ArtID.setColumns(10);
		ArtID.setBounds(173, 302, 302, 22);
		contentPane.add(ArtID);
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection1.prepareStatement("Select Gallery_ID from MANAGER where Manager_ID=?");
					smt.setString(1,msg);
					ResultSet rs=smt.executeQuery();
					if(rs.next())
						GID=rs.getString("Gallery_ID");
					PreparedStatement smt1= connection2.prepareStatement("update ARTWORK set Gallery_ID=? where Art_ID=?");
					smt1.setString(1,GID);
					smt1.setString(2,ArtID.getText());
					smt1.execute();
					JOptionPane.showMessageDialog(null,"Art work updated");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Manager M= new Manager(msg);
				M.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(375, 402, 100, 30);
		contentPane.add(button);
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
						
						JButton btnUploadArt = new JButton("Upload ART");
						btnUploadArt.setFont(new Font("Tahoma", Font.PLAIN, 25));
						btnUploadArt.setBounds(173, 335, 302, 42);
						contentPane.add(btnUploadArt);
				
						JLabel label_2 = new JLabel("");
						label_2.setIcon(new ImageIcon(img));
						label_2.setBounds(0, 0, 784, 561);
						contentPane.add(label_2);
	}
}
