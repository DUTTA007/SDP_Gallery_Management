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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.UIManager;

public class Exhi extends JFrame {

	private JPanel contentPane;
	private JTextField aid;
	public static String msg=null;
	public static String GID=null;
	java.sql.Connection connection=Connection.Dbconnection();
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Exhi frame = new Exhi(msg);
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
	public Exhi(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("NEW EXHIBITION WORK");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 36));
		label.setBounds(29, 31, 715, 42);
		contentPane.add(label);

		JLabel lblArtId = new JLabel("PAINTING ID");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblArtId.setBounds(22, 119, 151, 27);
		contentPane.add(lblArtId);

		aid = new JTextField();
		aid.setColumns(10);
		aid.setBounds(183, 125, 302, 22);
		contentPane.add(aid);
		
		table = new JTable();
		table.setBounds(24, 309, 744, 180);
		contentPane.add(table);
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITION");
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt1= connection2.prepareStatement("INSERT INTO EXHIBITION(EXHIBITION_TITLE,ESTART_DATE,EEND_DATE,PAINTING_ID)VALUES (?,?,?,?)"
							+ "");
					smt1.setString(1,textField.getText());
					smt1.setString(2,textField_1.getText());
					smt1.setString(3,textField_2.getText());
					smt1.setString(4,aid.getText());
					boolean rs1=smt1.execute();
					if(rs1){
						JOptionPane.showMessageDialog(null,"EXHIBITION ADDED!");
					}
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button.setBounds(272, 269, 100, 30);
		contentPane.add(button);


		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
				
				JLabel label_1_2 = new JLabel("EXHIBITION TITLE");
				label_1_2.setForeground(Color.WHITE);
				label_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				label_1_2.setBounds(22, 156, 151, 27);
				contentPane.add(label_1_2);
						
						textField = new JTextField();
						textField.setColumns(10);
						textField.setBounds(183, 162, 302, 22);
						contentPane.add(textField);
						
						JLabel label_1_2_1 = new JLabel("START DATE");
						label_1_2_1.setForeground(Color.WHITE);
						label_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
						label_1_2_1.setBounds(22, 193, 151, 27);
						contentPane.add(label_1_2_1);
						
						textField_1 = new JTextField();
						textField_1.setColumns(10);
						textField_1.setBounds(183, 199, 302, 22);
						contentPane.add(textField_1);
								
								JLabel lblEndDate = new JLabel("END DATE");
								lblEndDate.setForeground(Color.WHITE);
								lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
								lblEndDate.setBounds(22, 226, 151, 27);
								contentPane.add(lblEndDate);
								
								textField_2 = new JTextField();
								textField_2.setColumns(10);
								textField_2.setBounds(183, 231, 302, 22);
								contentPane.add(textField_2);
								
										JLabel label_2 = new JLabel("");
										label_2.setIcon(new ImageIcon(img));
										label_2.setBounds(0, 0, 784, 561);
										contentPane.add(label_2);

	}
}
