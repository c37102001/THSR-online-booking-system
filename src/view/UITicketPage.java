package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UITicketPage extends JFrame {

	private JPanel contentPane;

	/*
	 * Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITicketPage frame = new UITicketPage();
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
	public UITicketPage(String uid, String date, String tid, String startStn, String startTime, String endStn, String endTime, int[] ticketTypes) {
		
		System.out.println("uid: " + uid);
		System.out.println("date: " + date);
		System.out.println("tid: " + tid);
		System.out.println("start: " + startStn + " " + startTime);
		System.out.println("end: " + endStn + " " + endTime);
		System.out.println("adultNum: " + ticketTypes[0]);
		System.out.println("kidNum: " + ticketTypes[1]);
		System.out.println("elderNum: " + ticketTypes[2]);
		System.out.println("priorNum: " + ticketTypes[3]);
		System.out.println("studentNum: " + ticketTypes[4]);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 360, 592);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(-5, 0, 249, 85);
		lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
		contentPane.add(lblNewLabel_5);
		
		JButton btnConfirm = new JButton("\u78BA\u8A8D\u8A02\u7968");
		btnConfirm.setBounds(10, 513, 334, 40);
		contentPane.add(btnConfirm);
		
		JLabel label = new JLabel("\u8A02\u4F4D\u660E\u7D30");
		label.setBounds(20, 95, 60, 21);
		contentPane.add(label);
		
	}
}
