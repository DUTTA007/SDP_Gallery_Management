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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class CustomerArtworkList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	int intpID;
	int intexhID;
	String exhID;
	String pID;

	java.sql.Connection connection = Connection.Dbconnection();
	public static String msg = null;

	private void setVisiblePaintings(int paintingTypeId) {
		try {
			PreparedStatement allPaintingStmt = connection.prepareStatement(String.format("""
							SELECT
							PAINTING_ID,
							PAINTING_TITLE,
							PAINTING_DATE,
							PAINTING_PRICE_EURO as 'Price (Euro)',
							FNAME as 'Artist',
							NATIONALITY as 'From',
							PAINTING_TYPE_NAME as 'Type'
							FROM painting
							JOIN users USING (USER_ID)
							JOIN nationality USING (NATIONALITY_ID)
							JOIN painting_type USING (PAINTING_TYPE_ID)
							WHERE PAINTING_PURCHASE_FLG = 0
							%s
					""", paintingTypeId < 0 ? "" : "AND PAINTING_TYPE_ID = ? "));
			if (paintingTypeId > -1) {
				allPaintingStmt.setInt(1, paintingTypeId);
			}
			table.setModel(DbUtils.resultSetToTableModel(allPaintingStmt.executeQuery()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerArtworkList frame = new CustomerArtworkList(msg);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public CustomerArtworkList(String msg) throws SQLException {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGallery = new JLabel("Art Works");
		lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
		lblGallery.setForeground(Color.WHITE);
		lblGallery.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblGallery.setBounds(134, 11, 496, 42);
		contentPane.add(lblGallery);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 79, 577, 371);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setShowVerticalLines(false);

		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setVisible(false);
				dispose();
				Customer C = new Customer(msg);
				C.setVisible(true);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_4.setBounds(20, 520, 100, 30);
		contentPane.add(button_4);
		Image img = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
		var paintingTypes = connection.prepareStatement("SELECT * FROM painting_type").executeQuery();
		int btnHeight = 120;
		while (paintingTypes.next()) {
			var typeName = paintingTypes.getString("PAINTING_TYPE_NAME");
			var typeId = paintingTypes.getInt("PAINTING_TYPE_ID");
			JButton btnPaintingType = new JButton(typeName);
			btnPaintingType.setBackground(UIManager.getColor("Button.darkShadow"));
			btnPaintingType.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisiblePaintings(typeId);
				}
			});
			btnPaintingType.setBounds(20, btnHeight, 120, 23);
			contentPane.add(btnPaintingType);
			btnHeight += 33;
		}

		JButton btnAll = new JButton("all");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisiblePaintings(-1);
			}
		});
		btnAll.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAll.setBackground(UIManager.getColor("Button.darkShadow"));
		btnAll.setBounds(20, 79, 128, 30);
		contentPane.add(btnAll);

		// initialize
		setVisiblePaintings(-1);

		JButton btnNewButton = new JButton("Add to basket");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow > -1) {
					String paintingId = String.valueOf(table.getValueAt(selectedRow, 0));
					int intPAINTING_ID = Integer.parseInt(paintingId);

					try {
						PreparedStatement smt4 = connection.prepareStatement("""
								Select
								PAINTING_TYPE_ID
								, EXHIBITION_ID
								from painting a
								left join
								exhibition b
								on a.PAINTING_ID = b.PAINTING_ID
								where a.PAINTING_ID = ?;
								""");
						smt4.setInt(1, intPAINTING_ID);

						ResultSet rs4 = smt4.executeQuery();

						while (rs4.next()) {
							pID = rs4.getString("PAINTING_TYPE_ID");
							exhID = rs4.getString("EXHIBITION_ID");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						PreparedStatement allPaintingStmt;
						PreparedStatement FlagUpdate;
						if (exhID == null) {
							allPaintingStmt = connection.prepareStatement(String.format("" + "INSERT INTO purchase" + "(PAINTING_TYPE_ID"
											+ ", USER_ID" + ", EXHIBITION_ID" + ", PAINTING_ID) " + "VALUES (?,?,?,?)"));
							
							allPaintingStmt.setString(1, pID);
							allPaintingStmt.setString(2, msg);
							allPaintingStmt.setString(3, exhID);
							allPaintingStmt.setInt(4, intPAINTING_ID);
							
							FlagUpdate = connection.prepareStatement("call artgallery.PURCHASE_FLG(?)");
							FlagUpdate.setString(1, pID);
							
						}
						else {
							allPaintingStmt = connection.prepareStatement(String.format("" + "INSERT INTO purchase" + "(PAINTING_TYPE_ID"
											+ ", USER_ID" + ", PAINTING_ID) " + "VALUES (?,?,?)"));
														allPaintingStmt.setString(1, pID);
							allPaintingStmt.setString(2, msg);
							allPaintingStmt.setInt(3, intPAINTING_ID);
							
							FlagUpdate = connection.prepareStatement("call artgallery.PURCHASE_FLG(?)");
							FlagUpdate.setString(1, pID);
							
						}
						allPaintingStmt.executeUpdate();
						FlagUpdate.executeUpdate();
						JOptionPane.showMessageDialog(null, "The selected Art work added to your basket");

						// table.setModel(DbUtils.resultSetToTableModel(allPaintingStmt.executeQuery()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setBounds(605, 476, 148, 42);
		contentPane.add(btnNewButton);

		JLabel label = new JLabel("");
		label.setBackground(SystemColor.windowBorder);
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 784, 561);
		contentPane.add(label);
	}
}
