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
import javax.swing.JScrollPane;

public class Manager extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static String msg=null;
	public static String MID=null;
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Manager frame = new Manager(msg);
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
	public Manager(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setForeground(Color.WHITE);
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblManager.setBounds(0, 0, 733, 42);
		contentPane.add(lblManager);
		JButton btnAddArtworkTo = new JButton("UPDATE ARTWORK");
		btnAddArtworkTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				contentPane.setVisible(false);
				dispose();
				MID=msg;
				UpdateArtwork AW= new UpdateArtwork(MID);
				AW.setVisible(true);
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAddArtworkTo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtworkTo.setBounds(24, 64, 233, 37);
		contentPane.add(btnAddArtworkTo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 162, 733, 227);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		try{
			PreparedStatement smt1= connection2.prepareStatement("SELECT PAINTING_ID, PAINTING_TITLE, PAINTING_DATE, PAINTING_TYPE_ID, USER_ID, PAINTING_PRICE_EURO FROM PAINTING WHERE PAINTING_PURCHASE_FLG=0");
			ResultSet rs1=smt1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Login.main(null);
			}
		});
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogout.setBounds(668, 0, 116, 29);
		contentPane.add(btnLogout);

		JLabel lblArtWorkAvailable = new JLabel("ARTWORKS AVAILABLE IN THE GALLERY:");
		lblArtWorkAvailable.setForeground(Color.WHITE);
		lblArtWorkAvailable.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblArtWorkAvailable.setBounds(24, 112, 551, 29);
		contentPane.add(lblArtWorkAvailable);

		JButton btnAddArtworkTo_1 = new JButton("ADD EXHIBITION");
		btnAddArtworkTo_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				MID=msg;
				Exhi AW= new Exhi(MID);
				AW.setVisible(true);
			}
		});
		btnAddArtworkTo_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtworkTo_1.setBounds(267, 64, 220, 37);
		contentPane.add(btnAddArtworkTo_1);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(UIManager.getColor("ToolTip.background"));
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setBounds(10, 11, 100, 35);
		contentPane.add(lblAdmin);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JButton btnAddArtworkTo_1_1 = new JButton("DELETE EXHIBITION");
		btnAddArtworkTo_1_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			contentPane.setVisible(false);
			dispose();
			MID=msg;
			DeleteExhibition AW= new DeleteExhibition(MID);
			AW.setVisible(true);
		}
	});
		btnAddArtworkTo_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddArtworkTo_1_1.setBounds(485, 64, 236, 37);
		contentPane.add(btnAddArtworkTo_1_1);

		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(img));
				label.setBounds(10, 10, 784, 561);
				contentPane.add(label);
	}
}
