import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UpdateArtwork extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UpdateArtwork frame = new UpdateArtwork(msg);
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
	public static String msg=null;
	public static String GID=null;
	java.sql.Connection connection1=Connection.Dbconnection();
	private JTextField textField;
	@SuppressWarnings("unchecked")
	public UpdateArtwork(String msg) throws SQLException {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"PAINTING_PRICE_EURO", "PAINTING_TITLE"}));
		comboBox_2.setBounds(183, 154, 305, 27);
		contentPane.add(comboBox_2);


		JLabel lblUpdateArtwork = new JLabel("UPDATE ARTWORK");
		lblUpdateArtwork.setForeground(Color.WHITE);
		lblUpdateArtwork.setHorizontalAlignment(SwingConstants.LEFT);
		lblUpdateArtwork.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblUpdateArtwork.setBounds(229, 11, 363, 42);
		contentPane.add(lblUpdateArtwork);
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setForeground(UIManager.getColor("ToolTip.background"));
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtist.setBounds(10, 11, 100, 35);
		contentPane.add(lblArtist);
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
						
						JLabel lblUpdateField = new JLabel("UPDATE FIELD");
						lblUpdateField.setForeground(Color.WHITE);
						lblUpdateField.setFont(new Font("Tahoma", Font.PLAIN, 20));
						lblUpdateField.setBounds(37, 154, 141, 27);
						contentPane.add(lblUpdateField);
								
								JLabel lblNewLabel = new JLabel("UPDATE VALUE");
								lblNewLabel.setForeground(new Color(255, 255, 255));
								lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
								lblNewLabel.setBounds(37, 209, 141, 27);
								contentPane.add(lblNewLabel);
										
										textField = new JTextField();
										textField.setColumns(10);
										textField.setBounds(183, 209, 305, 27);
										contentPane.add(textField);
												
												@SuppressWarnings("rawtypes")
												JComboBox<String> comboBox2 = new JComboBox<String>();
												comboBox2.setBounds(183, 90, 305, 27);
												contentPane.add(comboBox2);
												PreparedStatement smt= connection1.prepareStatement("Select PAINTING_ID from PAINTING");
												ResultSet rs=smt.executeQuery();
												while(rs.next()) {
													String ids= rs.getString("PAINTING_ID");
													comboBox2.addItem(ids);
												}

		JLabel lblArtId = new JLabel("PAINTING ID");
		lblArtId.setForeground(Color.WHITE);
		lblArtId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtId.setBounds(37, 90, 141, 27);
		contentPane.add(lblArtId);
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(592, 281, 141, 30);
		contentPane.add(btnUpdate);
		
				JLabel label_2 = new JLabel("");
				label_2.setIcon(new ImageIcon(img));
				label_2.setBounds(0, 4, 784, 561);
				contentPane.add(label_2);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String updtField=comboBox_2.getSelectedItem().toString();
					String updtValue=textField.getText();
					String paintingId=comboBox2.getSelectedItem().toString();
					
					if(updtField=="PAINTING_PRICE_EURO") {
					PreparedStatement smt= connection1.prepareStatement("UPDATE PAINTING SET PAINTING_PRICE_EURO = ? WHERE PAINTING_ID=?");			
					smt.setString(1, updtValue);
					smt.setString(2, paintingId);
					System.out.println(smt);
					smt.execute();
					JOptionPane.showMessageDialog(null,"Art work updated");
					smt.close();
					}
					else {
						PreparedStatement smt= connection1.prepareStatement("UPDATE PAINTING SET PAINTING_TITLE = ? WHERE PAINTING_ID=?");			
						smt.setString(1, updtValue);
						smt.setString(2, paintingId);
						System.out.println(smt);
						smt.execute();
						JOptionPane.showMessageDialog(null,"Art work updated");
						smt.close();
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
	}
}
