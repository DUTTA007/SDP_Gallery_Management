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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class Category extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int count;
	private static String msg;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	private JButton btnBack;
	private JButton button;
	private JButton button_1;
	private JLabel label;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Category frame = new Category(msg,count);
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
	public Category(String msg,int count) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCategories = new JLabel("CATEGORIES");
		lblCategories.setForeground(Color.WHITE);
		lblCategories.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategories.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblCategories.setBounds(10, 45, 759, 42);
		contentPane.add(lblCategories);

		table = new JTable();
		table.setBounds(236, 114, 292, 230);
		contentPane.add(table);
		try{
			PreparedStatement smt= connection.prepareStatement("select * from CATEGORY");
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			   JOptionPane.showMessageDialog(null,ex);
		   }


			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						String artist="Artist";
					String Gallery="Gallery";
					if(msg.startsWith(artist.substring(0,6)) && count==1){
						contentPane.setVisible(false);
						dispose();
						Artwork AW= new Artwork(msg);
						AW.setVisible(true);
					}
					else if(msg.startsWith(artist.substring(0,6)) && count==0){
						contentPane.setVisible(false);
						dispose();
						AddCategory DA= new AddCategory(msg);
						DA.setVisible(true);
					}
					else if(msg.startsWith(Gallery.substring(0,7)) && count==0){
						contentPane.setVisible(false);
						dispose();
						AddManager AM= new AddManager(msg);
						AM.setVisible(true);
					}
				}catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
			btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btnBack.setBounds(335, 359, 89, 27);
			contentPane.add(btnBack);

			button = new JButton("Art_ID");
			button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button.setBounds(236, 91, 146, 23);
			contentPane.add(button);

			button_1 = new JButton("Artist_ID");
			button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			button_1.setBounds(380, 91, 148, 23);
			contentPane.add(button_1);

			label = new JLabel("");
			Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
			label.setIcon(new ImageIcon(img));
			label.setBounds(0, 0, 784, 561);
			contentPane.add(label);
	}

}
