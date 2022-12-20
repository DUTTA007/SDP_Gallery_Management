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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;

public class CustomerArtistList extends JFrame {

	private JPanel contentPane;
	java.sql.Connection connection = Connection.Dbconnection();
	public static String msg = null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerArtistList frame = new CustomerArtistList(msg);
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

	public CustomerArtistList(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGallery = new JLabel("List of all Artists");
		lblGallery.setHorizontalAlignment(SwingConstants.LEFT);
		lblGallery.setForeground(Color.WHITE);
		lblGallery.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblGallery.setBounds(35, 70, 496, 42);
		contentPane.add(lblGallery);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 125, 733, 291);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		try {
			PreparedStatement smt4 = connection.prepareStatement(String.format("""
					SELECT 
					FNAME as 'First name',
					LNAME as 'Last name',
					NATIONALITY as 'From',
						Case	 GENDER
							When	'm'	Then	N'Mail'
							Else				N'Femail'
						End	As 'Gender'
                    
					FROM artgallery.users 
					JOIN nationality USING (NATIONALITY_ID)
					where USER_ENTITY = 'ARTIST' 
						"""));
//			smt4.setString(1,msg);
			ResultSet rs4 = smt4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs4));
		} catch (Exception e) {
			e.printStackTrace();
		}

//		try{
//			PreparedStatement smt4= connection.prepareStatement("SELECT * FROM artgallery.users where USER_ENTITY = 'ARTIST'");
//			ResultSet rs4 = smt4.executeQuery();
//			table.setModel(DbUtils.resultSetToTableModel(rs4));
//			
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}

		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Customer C = new Customer(msg);
				C.setVisible(true);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_4.setBounds(20, 520, 100, 30);
		contentPane.add(button_4);
		Image img = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);

	}
}
