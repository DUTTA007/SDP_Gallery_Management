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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class Gal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	java.sql.Connection connection=Connection.Dbconnection();
	public static String msg=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Gal frame = new Gal(msg);
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
	public Gal(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGallery = new JLabel("GALLERY");
		lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
		lblGallery.setForeground(Color.WHITE);
		lblGallery.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblGallery.setBounds(22, 11, 733, 42);
		contentPane.add(lblGallery);

		JLabel lblTheGalleriesYou = new JLabel("List of all galleries");
		lblTheGalleriesYou.setForeground(Color.WHITE);
		lblTheGalleriesYou.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTheGalleriesYou.setBounds(24, 119, 496, 33);
		contentPane.add(lblTheGalleriesYou);

		table = new JTable();
		table.setBounds(22, 218, 733, 237);
		contentPane.add(table);
		try{
			PreparedStatement smt4= connection.prepareStatement("select * from GALLERY");
			ResultSet rs4=smt4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs4));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton btnGallerid = new JButton("Galler_ID");
		btnGallerid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGallerid.setBounds(22, 163, 183, 23);
		contentPane.add(btnGallerid);

		JButton btnGalleryname = new JButton("Gallery_Name");
		btnGalleryname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGalleryname.setBounds(204, 163, 184, 23);
		contentPane.add(btnGalleryname);

		JButton btnGallerylocation = new JButton("Gallery_Location");
		btnGallerylocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGallerylocation.setBounds(385, 163, 187, 23);
		contentPane.add(btnGallerylocation);

		JButton btnPhno = new JButton("PhNo");
		btnPhno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPhno.setBounds(570, 163, 185, 23);
		contentPane.add(btnPhno);

		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Customer C= new Customer(msg);
				C.setVisible(true);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_4.setBounds(20, 520, 100, 30);
		contentPane.add(button_4);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(UIManager.getColor("ToolTip.background"));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(10, 11, 100, 35);
		contentPane.add(lblUser);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}
}
