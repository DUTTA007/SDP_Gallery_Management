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

public class AddCategory extends JFrame {

	private JPanel contentPane;
	private JTextField CA;
	private JTextField CN;
	private JTextField CID;
	public static String msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddCategory frame = new AddCategory(msg);
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
	public AddCategory(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewCategory = new JLabel("NEW CATEGORY");
		lblNewCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCategory.setForeground(Color.WHITE);
		lblNewCategory.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewCategory.setBounds(38, 82, 715, 42);
		contentPane.add(lblNewCategory);

		JLabel lblCategoryId = new JLabel("Category ID");
		lblCategoryId.setForeground(Color.WHITE);
		lblCategoryId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblCategoryId.setBounds(143, 146, 161, 31);
		contentPane.add(lblCategoryId);

		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setForeground(Color.WHITE);
		lblCategoryName.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblCategoryName.setBounds(143, 188, 161, 41);
		contentPane.add(lblCategoryName);

		JLabel label_3 = new JLabel("Confirm Artist ID");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_3.setBounds(143, 240, 212, 22);
		contentPane.add(label_3);

		CA = new JTextField();
		CA.setColumns(10);
		CA.setBounds(350, 240, 302, 22);
		contentPane.add(CA);

		CN = new JTextField();
		CN.setColumns(10);
		CN.setBounds(350, 197, 302, 22);
		contentPane.add(CN);

		CID = new JTextField();
		CID.setColumns(10);
		CID.setBounds(350, 155, 302, 22);
		contentPane.add(CID);

		JButton button = new JButton("View Categories");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Category AW= new Category(msg,0);
				AW.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(143, 287, 183, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("Submit");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String msg1;
					PreparedStatement smt= connection.prepareStatement("insert into CATEGORY values (?,?)");
					smt.setString(1,CID.getText());
					smt.setString(2,CN.getText());
					msg1=CA.getText();
					if(msg1.equals(msg)){
						smt.execute();
						JOptionPane.showMessageDialog(null,"Category saved");
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
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_1.setBounds(454, 287, 89, 23);
		contentPane.add(button_1);
		
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
