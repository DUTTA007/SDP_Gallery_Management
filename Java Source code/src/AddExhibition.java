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

public class AddExhibition extends JFrame {

	private JPanel contentPane;
	JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static String msg=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExhibition frame = new AddExhibition(msg);
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
	private JTextField loc;
	private JTextField time;
	private JTextField day;
	private JTextField rf;
	private JTextField aid;
	public AddExhibition(String msg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewExhibition = new JLabel("NEW EXHIBITION");
		lblNewExhibition.setForeground(Color.WHITE);
		lblNewExhibition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewExhibition.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblNewExhibition.setBounds(33, 11, 715, 42);
		contentPane.add(lblNewExhibition);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblDate.setBounds(138, 132, 161, 22);
		contentPane.add(lblDate);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setForeground(Color.WHITE);
		lblLocation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblLocation.setBounds(138, 174, 161, 22);
		contentPane.add(lblLocation);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTime.setBounds(138, 220, 161, 22);
		contentPane.add(lblTime);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setForeground(Color.WHITE);
		lblDay.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblDay.setBounds(138, 267, 161, 29);
		contentPane.add(lblDay);
		
		JLabel lblRegistrationFee = new JLabel("Registration fee");
		lblRegistrationFee.setForeground(Color.WHITE);
		lblRegistrationFee.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblRegistrationFee.setBounds(138, 307, 161, 32);
		contentPane.add(lblRegistrationFee);
		
		JLabel lblArtId = new JLabel("Exhibition ID");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblArtId.setBounds(138, 88, 212, 22);
		contentPane.add(lblArtId);
		
		loc = new JTextField();
		loc.setColumns(10);
		loc.setBounds(345, 174, 302, 22);
		contentPane.add(loc);
		
		time = new JTextField();
		time.setColumns(10);
		time.setBounds(345, 220, 302, 22);
		contentPane.add(time);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(345, 266, 302, 22);
		contentPane.add(day);
		
		rf = new JTextField();
		rf.setColumns(10);
		rf.setBounds(345, 317, 302, 22);
		contentPane.add(rf);
		
		aid = new JTextField();
		aid.setColumns(10);
		aid.setBounds(345, 88, 302, 22);
		contentPane.add(aid);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("insert into EXHIBITIONS(Exhibition_ID,Date,Location,Time,Day,RegistrationFee) values (?,?,?,?,?,?)");
					smt.setString(1,aid.getText());
					smt.setString(2,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
					smt.setString(3,loc.getText());
					smt.setString(4,time.getText());
					smt.setString(5,day.getText());
					smt.setInt(6,Integer.parseInt(rf.getText()));
					
						smt.execute();
						JOptionPane.showMessageDialog(null,"Exhibition saved");
					smt.close();
				}
				 catch(Exception ex){
					   JOptionPane.showMessageDialog(null,ex);
				   }
				contentPane.setVisible(false);
				dispose();
				Ex G= new Ex(msg);
				G.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(438, 361, 89, 23);
		contentPane.add(button);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(345, 132, 302, 22);
		contentPane.add(dateChooser);
		
		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("C.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 779, 411);
		contentPane.add(label);
	}
}
