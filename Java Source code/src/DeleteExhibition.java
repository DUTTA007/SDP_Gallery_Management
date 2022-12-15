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

public class DeleteExhibition extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static String msg;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DeleteExhibition frame = new DeleteExhibition(msg);
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
	private JTextField date;
	private JTextField textField_1;
	public DeleteExhibition(String msg) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeleteExhibition = new JLabel("DELETE EXHIBITION");
		lblDeleteExhibition.setForeground(Color.WHITE);
		lblDeleteExhibition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteExhibition.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblDeleteExhibition.setBounds(33, 64, 715, 42);
		contentPane.add(lblDeleteExhibition);

		JLabel lblDate = new JLabel("Exhibition ID");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblDate.setBounds(138, 172, 161, 29);
		contentPane.add(lblDate);

		date = new JTextField();
		date.setColumns(10);
		date.setBounds(345, 172, 302, 22);
		contentPane.add(date);

		JLabel label_2 = new JLabel("Confirm Gallery ID");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label_2.setBounds(138, 229, 212, 29);
		contentPane.add(label_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(345, 237, 302, 22);
		contentPane.add(textField_1);

		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement smt= connection.prepareStatement("Delete from EXHIBITIONS where Exhibition_ID=?");
					smt.setString(1,date.getText());
					if(msg.equals(textField_1.getText())){
						smt.execute();
						JOptionPane.showMessageDialog(null,"Exhibition Deleted");
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
				Ex G= new Ex(msg);
				G.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setBounds(324, 296, 89, 23);
		contentPane.add(button);

		JLabel label = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}

}
