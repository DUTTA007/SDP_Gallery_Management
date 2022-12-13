import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Exhi extends JFrame {

	private JPanel contentPane;
	private JTextField aid;
	private JTextField eid;
	public static String msg=null;
	public static String GID=null;
	java.sql.Connection connection=Connection.Dbconnection();
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("NEW ART WORK");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 36));
		label.setBounds(29, 31, 715, 42);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Art ID");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_1.setBounds(181, 84, 151, 27);
		contentPane.add(label_1);
		
		aid = new JTextField();
		aid.setColumns(10);
		aid.setBounds(342, 84, 302, 22);
		contentPane.add(aid);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection1.prepareStatement("Select Gallery_ID from MANAGER where Manager_ID=?");
					smt.setString(1,msg);
					ResultSet rs=smt.executeQuery();
					if(rs.next())
						GID=rs.getString("Gallery_ID");
					PreparedStatement smt1= connection2.prepareStatement("update ARTWORK set Exhibition_ID=? where Art_ID=? and Gallery_ID =?");
					smt1.setString(1,eid.getText());
					smt1.setString(2,aid.getText());
					smt1.setString(3,GID);
					boolean rs1=smt1.execute();
					if(rs1==true){
						JOptionPane.showMessageDialog(null,"Art work updated");
					}
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
		button.setBounds(433, 171, 89, 23);
		contentPane.add(button);
		
		JLabel lblExhibitionId = new JLabel("Exhibition ID");
		lblExhibitionId.setForeground(Color.WHITE);
		lblExhibitionId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblExhibitionId.setBounds(181, 122, 151, 27);
		contentPane.add(lblExhibitionId);
		
		eid = new JTextField();
		eid.setColumns(10);
		eid.setBounds(342, 129, 302, 22);
		contentPane.add(eid);
		
		table = new JTable();
		table.setBounds(10, 288, 746, 112);
		contentPane.add(table);
		try{
			PreparedStatement smt= connection.prepareStatement("select * from EXHIBITIONS");
			ResultSet rs=smt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JButton button_1 = new JButton("Exhi_ID");
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_1.setBounds(10, 265, 107, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Date");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(115, 265, 109, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Location");
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(221, 265, 109, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Time");
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(327, 265, 109, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Day");
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(433, 265, 109, 23);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Reg_Fee");
		button_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_6.setBounds(541, 265, 109, 23);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("NoOfArt");
		button_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_7.setBounds(647, 265, 109, 23);
		contentPane.add(button_7);
		
		JLabel label_2 = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label_2.setIcon(new ImageIcon(img));
		label_2.setBounds(0, 0, 779, 411);
		contentPane.add(label_2);
		
	}
}
