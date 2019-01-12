package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class UITimetablePage extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITimetablePage frame = new UITimetablePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public UITimetablePage(String date) {
		
		System.out.println("Timetable page!");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 592, 592);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-5, 0, 249, 85);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u67E5\u8A62\u65E5\u671F");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(385, 50, 60, 21);
		contentPane.add(label);

		JLabel label_date = new JLabel("New label");
		label_date.setHorizontalAlignment(SwingConstants.RIGHT);
		label_date.setBounds(455, 50, 121, 21);
		label_date.setText(date);
		contentPane.add(label_date);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 566, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8ECA\u6B21", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("\u5317\u4E0A");
		label_1.setBounds(10, 95, 60, 21);
		contentPane.add(label_1);
		
		
	}
}
