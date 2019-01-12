package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.Ticket;
import data.Train;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	public UITicketPage(String uid, String date, String startStn, String endStn, Train train, int cartType, int[] ticketTypes) {
		
		String tid = train.getTid();
		String startTime = train.getTimetable(startStn);
		String endTime = train.getTimetable(endStn);
		
		System.out.println("uid: " + uid);
		System.out.println("date: " + date);
		System.out.println("tid: " + tid);
		System.out.println("start: " + startStn + " " + startTime);
		System.out.println("end: " + endStn + " " + endTime);
		System.out.println("cart: " + cartType);
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
		
		JLabel label = new JLabel("\u8A02\u7968\u8CC7\u8A0A");
		label.setBounds(20, 95, 60, 21);
		contentPane.add(label);
		
		JLabel label_userid = new JLabel("\u4F7F\u7528\u8005ID");
		label_userid.setBounds(30, 135, 60, 21);
		contentPane.add(label_userid);
		
		JLabel showUserID = new JLabel("<dynamic>");
		showUserID.setBounds(90, 135, 85, 21);
		showUserID.setText(uid);
		contentPane.add(showUserID);
		
		JLabel label_date = new JLabel("\u642D\u4E58\u65E5\u671F");
		label_date.setBounds(180, 135, 60, 21);
		contentPane.add(label_date);
		
		JLabel showDate = new JLabel("New label");
		showDate.setBounds(240, 135, 85, 21);
		showDate.setText(date);
		contentPane.add(showDate);
		
		JLabel label_tid = new JLabel("\u8ECA\u6B21");
		label_tid.setBounds(30, 170, 60, 21);
		contentPane.add(label_tid);
		
		JLabel showTid = new JLabel("<dynamic>");
		showTid.setBounds(90, 170, 85, 21);
		showTid.setText(tid);
		contentPane.add(showTid);
		
		JLabel label_discount = new JLabel("\u9069\u7528\u512A\u60E0");
		label_discount.setBounds(180, 170, 60, 21);
		contentPane.add(label_discount);
		
		JLabel showDiscount = new JLabel("New label");
		showDiscount.setBounds(240, 170, 85, 21);
		contentPane.add(showDiscount);
		
		JLabel label_cart = new JLabel("\u8ECA\u5EC2");
		label_cart.setBounds(30, 205, 60, 21);
		contentPane.add(label_cart);
		
		JLabel showCart = new JLabel("<dynamic>");
		showCart.setBounds(90, 205, 85, 21);
		if (cartType == Ticket.CartStandard)
			showCart.setText("標準");
		else
			showCart.setText("商務");
		contentPane.add(showCart);
		
		JButton btnConfirm = new JButton("\u78BA\u8A8D\u8A02\u7968");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "userID: " + uid + "\nordernumber: wait for controller" + "\n欲查詢車票詳細資訊，請至訂單查詢頁面。";
				JOptionPane.showMessageDialog(null, message, "已成功訂票！", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		btnConfirm.setBounds(10, 513, 334, 40);
		contentPane.add(btnConfirm);
		
	}
}
