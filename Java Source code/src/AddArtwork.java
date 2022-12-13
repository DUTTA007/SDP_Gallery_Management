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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddArtwork extends JFrame {

	private JPanel contentPane;
	private JTextField ArtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("NEW ART WORK");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 36));
		label.setBounds(54, 103, 715, 42);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Art ID");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_1.setBounds(205, 171, 151, 27);
		contentPane.add(label_1);
		
		ArtID = new JTextField();
		ArtID.setColumns(10);
		ArtID.setBounds(366, 176, 302, 22);
		contentPane.add(ArtID);
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
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
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(301, 240, 89, 23);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label_2.setIcon(new ImageIcon(img));
		label_2.setBounds(0, 0, 779, 411);
		contentPane.add(label_2);
	}

}
