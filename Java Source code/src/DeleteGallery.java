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

public class DeleteGallery extends JFrame {

	private JPanel contentPane;
	private JTextField GalleryID;
	static String msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
			@Override
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
		button.setBounds(375, 248, 89, 23);
		contentPane.add(button);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(UIManager.getColor("ToolTip.background"));
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setBounds(10, 11, 100, 35);
		contentPane.add(lblAdmin);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}

}
