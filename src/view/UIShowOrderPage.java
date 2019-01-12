package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UIShowOrderPage extends JFrame {

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
					UIShowOrderPage frame = new UIShowOrderPage();
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
	public UIShowOrderPage(String uid, String orderNum) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 360, 592);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
		icon.setBounds(-5, 0, 249, 85);
		contentPane.add(icon);
		
		JLabel label_uid = new JLabel("\u4F7F\u7528\u8005ID");
		label_uid.setBounds(30, 95, 60, 21);
		contentPane.add(label_uid);
		
		JLabel showUid = new JLabel("New label");
		showUid.setBounds(100, 95, 85, 21);
		contentPane.add(showUid);
		
		JLabel label_orderNum = new JLabel("\u8A02\u55AE\u7DE8\u865F");
		label_orderNum.setBounds(30, 135, 60, 21);
		contentPane.add(label_orderNum);
		
		JLabel showOrderNum = new JLabel("New label");
		showOrderNum.setBounds(100, 135, 85, 21);
		contentPane.add(showOrderNum);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 334, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btn = new JButton("test");
		btn.setBounds(10, 513, 334, 40);
		contentPane.add(btn);
	}
}
