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
	private JTextField eid;
	public static String msg=null;
	public static String GID=null;
	java.sql.Connection connection=Connection.Dbconnection();
	java.sql.Connection connection1=Connection.Dbconnection();
	java.sql.Connection connection2=Connection.Dbconnection();
	private JTable table;
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

		JLabel label = new JLabel("NEW ART WORK");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 36));
		label.setBounds(29, 31, 715, 42);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Art ID");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(22, 222, 151, 27);
		contentPane.add(label_1);

		aid = new JTextField();
		aid.setColumns(10);
		aid.setBounds(183, 222, 302, 22);
		contentPane.add(aid);

		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
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
					if(rs1){
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button.setBounds(183, 293, 100, 30);
		contentPane.add(button);

		JLabel lblExhibitionId = new JLabel("Exhibition ID");
		lblExhibitionId.setForeground(Color.WHITE);
		lblExhibitionId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblExhibitionId.setBounds(22, 253, 151, 27);
		contentPane.add(lblExhibitionId);

		eid = new JTextField();
		eid.setColumns(10);
		eid.setBounds(183, 260, 302, 22);
		contentPane.add(eid);

		table = new JTable();
		table.setBounds(22, 377, 746, 112);
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
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(22, 354, 107, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Date");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(127, 354, 109, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Location");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(233, 354, 109, 23);
		contentPane.add(button_3);

		JButton button_4 = new JButton("Time");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(339, 354, 109, 23);
		contentPane.add(button_4);

		JButton button_5 = new JButton("Day");
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(445, 354, 109, 23);
		contentPane.add(button_5);

		JButton button_6 = new JButton("Reg_Fee");
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_6.setBounds(553, 354, 109, 23);
		contentPane.add(button_6);

		JButton button_7 = new JButton("NoOfArt");
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_7.setBounds(659, 354, 109, 23);
		contentPane.add(button_7);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
		
				JLabel label_2 = new JLabel("");
				label_2.setIcon(new ImageIcon(img));
				label_2.setBounds(0, 0, 784, 561);
				contentPane.add(label_2);

	}
}
