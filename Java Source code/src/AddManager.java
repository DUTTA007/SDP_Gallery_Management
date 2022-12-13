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
import java.awt.event.ActionEvent;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class AddManager extends JFrame {

	private JPanel contentPane;
	private JTextField ManagerID;
	private JTextField ManagerName;
	private JTextField ManagerPhNo;
	private JTextField Salary;
	private JTextField GalleryID;
	private JTextField Gender;
	private JTextField CategoryID;
	public static String msg;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	java.sql.Connection connection=Connection.Dbconnection();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddManager frame = new AddManager(msg);
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
	public AddManager(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewManager = new JLabel("NEW MANAGER");
		lblNewManager.setForeground(Color.WHITE);
		lblNewManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewManager.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewManager.setBounds(35, 11, 715, 42);
		contentPane.add(lblNewManager);
		
		JLabel lblManagerId = new JLabel("Manager ID");
		lblManagerId.setForeground(Color.WHITE);
		lblManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblManagerId.setBounds(140, 64, 161, 29);
		contentPane.add(lblManagerId);
		
		JLabel lblManagerName = new JLabel("Manager Name");
		lblManagerName.setForeground(Color.WHITE);
		lblManagerName.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblManagerName.setBounds(140, 104, 161, 29);
		contentPane.add(lblManagerName);
		
		JLabel lblManagerPhno = new JLabel("Manager PhNo");
		lblManagerPhno.setForeground(Color.WHITE);
		lblManagerPhno.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblManagerPhno.setBounds(140, 144, 161, 29);
		contentPane.add(lblManagerPhno);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblStartDate.setBounds(140, 184, 161, 29);
		contentPane.add(lblStartDate);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setForeground(Color.WHITE);
		lblSalary.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSalary.setBounds(140, 224, 161, 29);
		contentPane.add(lblSalary);
		
		JLabel lblConfirmGalleryId = new JLabel("Confirm Gallery ID");
		lblConfirmGalleryId.setForeground(Color.WHITE);
		lblConfirmGalleryId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConfirmGalleryId.setBounds(140, 337, 212, 29);
		contentPane.add(lblConfirmGalleryId);
		
		ManagerID = new JTextField();
		ManagerID.setColumns(10);
		ManagerID.setBounds(347, 64, 302, 22);
		contentPane.add(ManagerID);
		
		ManagerName = new JTextField();
		ManagerName.setColumns(10);
		ManagerName.setBounds(347, 104, 302, 22);
		contentPane.add(ManagerName);
		
		ManagerPhNo = new JTextField();
		ManagerPhNo.setColumns(10);
		ManagerPhNo.setBounds(347, 144, 302, 22);
		contentPane.add(ManagerPhNo);
		
		Salary = new JTextField();
		Salary.setColumns(10);
		Salary.setBounds(347, 224, 302, 22);
		contentPane.add(Salary);
		
		GalleryID = new JTextField();
		GalleryID.setColumns(10);
		GalleryID.setBounds(347, 337, 302, 22);
		contentPane.add(GalleryID);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("insert into MANAGER values (?,?,?,?,?,?,?,?)");
					smt.setString(1,ManagerID.getText());
					smt.setString(2,ManagerName.getText());
					smt.setInt(3,Integer.parseInt(ManagerPhNo.getText()));
					smt.setString(4,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
					smt.setInt(5,Integer.parseInt(Salary.getText()));
					smt.setString(6,Gender.getText());
					smt.setString(7,CategoryID.getText());
					String msg1=GalleryID.getText();
					smt.setString(8,msg1);
					if(msg.equals(msg1)){
						smt.execute();
						JOptionPane.showMessageDialog(null,"Manager saved");
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
				Man G= new Man(msg);
				G.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(458, 377, 89, 23);
		contentPane.add(button);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblGender.setBounds(140, 264, 161, 29);
		contentPane.add(lblGender);
		
		Gender = new JTextField();
		Gender.setColumns(10);
		Gender.setBounds(347, 264, 302, 22);
		contentPane.add(Gender);
		
		JLabel lblCategoryId = new JLabel("Category ID");
		lblCategoryId.setForeground(Color.WHITE);
		lblCategoryId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblCategoryId.setBounds(140, 304, 161, 29);
		contentPane.add(lblCategoryId);
		
		CategoryID = new JTextField();
		CategoryID.setColumns(10);
		CategoryID.setBounds(347, 304, 302, 22);
		contentPane.add(CategoryID);
		
		JButton button_1 = new JButton("View Categories");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				Category AW= new Category(msg,0);
				AW.setVisible(true);
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button_1.setBounds(140, 377, 183, 23);
		contentPane.add(button_1);
		
		 dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(347, 184, 302, 20);
		contentPane.add(dateChooser);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
		
	}
}
