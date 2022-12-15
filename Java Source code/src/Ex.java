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

public class Ex extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String msg,loc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Ex frame = new Ex(msg);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	java.sql.Connection connection=Connection.Dbconnection();
	/**
	 * Create the frame.
	 */
	public Ex(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblExhibitions = new JLabel("EXHIBITIONS");
		lblExhibitions.setHorizontalAlignment(SwingConstants.CENTER);
		lblExhibitions.setForeground(Color.WHITE);
		lblExhibitions.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblExhibitions.setBounds(10, 83, 759, 36);
		contentPane.add(lblExhibitions);

		JButton btnAddExhibitions = new JButton("Add Exhibition");
		btnAddExhibitions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				AddExhibition AE= new AddExhibition(msg);
				AE.setVisible(true);
			}
		});
		btnAddExhibitions.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAddExhibitions.setBounds(58, 130, 155, 33);
		contentPane.add(btnAddExhibitions);

		JButton btnDeleteExhibition = new JButton("Delete Exhibition");
		btnDeleteExhibition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				DeleteExhibition DE= new DeleteExhibition(msg);
				DE.setVisible(true);
			}
		});
		btnDeleteExhibition.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeleteExhibition.setBounds(303, 130, 181, 33);
		contentPane.add(btnDeleteExhibition);

		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Gallery G= new Gallery(msg);
				G.setVisible(true);
			}
		});
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_2.setBounds(617, 130, 89, 33);
		contentPane.add(button_2);

		JLabel lblExhibitionsConductedYour = new JLabel("Exhibitions conducted in your region");
		lblExhibitionsConductedYour.setForeground(Color.WHITE);
		lblExhibitionsConductedYour.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblExhibitionsConductedYour.setBounds(58, 174, 588, 28);
		contentPane.add(lblExhibitionsConductedYour);
		try{
			PreparedStatement smt1= connection.prepareStatement("select Gallery_Location from GALLERY where Gallery_ID=?");
			smt1.setString(1,msg);
			ResultSet rs1=smt1.executeQuery();
			while(rs1.next()){
				loc=rs1.getString("Gallery_Location");
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		table = new JTable();
		table.setBounds(58, 224, 642, 176);
		contentPane.add(table);
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITIONS where Location=?");
			smt.setString(1,loc);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			refreshTable(loc);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton btnExhiid = new JButton("Exhi_ID");
		btnExhiid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExhiid.setBounds(58, 201, 92, 23);
		contentPane.add(btnExhiid);

		JButton btnDate = new JButton("Date");
		btnDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDate.setBounds(149, 201, 93, 23);
		contentPane.add(btnDate);

		JButton btnLocation = new JButton("Location");
		btnLocation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLocation.setBounds(238, 201, 96, 23);
		contentPane.add(btnLocation);

		JButton btnTime = new JButton("Time");
		btnTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnTime.setBounds(333, 201, 93, 23);
		contentPane.add(btnTime);

		JButton btnDay = new JButton("Day");
		btnDay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDay.setBounds(423, 201, 94, 23);
		contentPane.add(btnDay);

		JButton btnRegistration = new JButton("Reg_Fee");
		btnRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRegistration.setBounds(516, 201, 93, 23);
		contentPane.add(btnRegistration);

		JButton btnNoofart = new JButton("NoOfArt");
		btnNoofart.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNoofart.setBounds(607, 201, 93, 23);
		contentPane.add(btnNoofart);
		
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
		refreshTable(loc);
	}
	public void refreshTable(String loc){
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITIONS where Location=?");
			smt.setString(1,loc);
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
