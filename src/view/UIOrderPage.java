package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;


import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class UIOrderPage extends JFrame {
	private JPanel contentPane;
	private JTable table;
	/**
	 * @wbp.nonvisual location=104,239
	 */
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIOrderPage frame = new UIOrderPage();
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
	public UIOrderPage(String uid, String startStn, String endStn, String date, String time, int cartType, int seatPrefer, int adultNum, int elderNum, int studentNum, int kidNum, int priorNum, int ticketSum) {
		
		System.out.println("uid: " + uid);
		System.out.println("startStn: " + startStn);
		System.out.println("endStn: " + endStn);
		System.out.println("time: " + date + " " + time);
		if (cartType == 0)
			System.out.println("cartType: 標準");
		else
			System.out.println("cartType: 商務");
		if (seatPrefer == 0)
			System.out.println("seatPrefer: 無");
		else if (seatPrefer == 1)
			System.out.println("seatPrefer: 靠走道");
		else
			System.out.println("seatPrefer: 靠窗");
		System.out.println("adult: " + adultNum);
		System.out.println("elder: " + elderNum);
		System.out.println("student: " + studentNum);
		System.out.println("kid: " + kidNum);
		System.out.println("prior: " + priorNum);
		System.out.println("total: " + ticketSum + " 張");
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 360, 592);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\JAVA\\OOAD1071\\TicketOrderSystem\\img\\logo.jpg"));
		lblNewLabel_5.setBounds(-5, 0, 249, 85);
		contentPane.add(lblNewLabel_5);
		
		JButton btnCancel = new JButton("\u53D6\u6D88\u8A02\u7968");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 506, 157, 37);
		contentPane.add(btnCancel);
		
		JButton btnOrder = new JButton("\u78BA\u8A8D\u8A02\u7968");
		btnOrder.setEnabled(false);
		btnOrder.setBounds(177, 506, 157, 37);
		contentPane.add(btnOrder);
		
		JLabel label = new JLabel("\u9078\u64C7\u65E5\u671F");
		label.setBounds(20, 95, 60, 21);
		contentPane.add(label);
		
		JLabel label_date = new JLabel("New label");
		label_date.setBounds(90, 95, 121, 21);
		label_date.setText(date);
		contentPane.add(label_date);
		
		JLabel label_tid = new JLabel("\u8ECA\u6B21");
		label_tid.setHorizontalAlignment(SwingConstants.CENTER);
		label_tid.setBounds(30, 130, 60, 21);
		contentPane.add(label_tid);
		
		JLabel label_start = new JLabel("\u8D77\u7AD9");
		label_start.setHorizontalAlignment(SwingConstants.CENTER);
		label_start.setBounds(90, 130, 60, 21);
		label_start.setText(startStn);
		contentPane.add(label_start);
		
		JLabel label_1 = new JLabel("\u2192");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(150, 130, 40, 21);
		contentPane.add(label_1);
		
		JLabel label_end = new JLabel("\u8FC4\u7AD9");
		label_end.setHorizontalAlignment(SwingConstants.CENTER);
		label_end.setBounds(190, 130, 60, 21);
		label_end.setText(endStn);
		contentPane.add(label_end);
		
		JLabel label_driveTime = new JLabel("\u884C\u8ECA\u6642\u9593");
		label_driveTime.setHorizontalAlignment(SwingConstants.CENTER);
		label_driveTime.setBounds(250, 130, 60, 21);
		contentPane.add(label_driveTime);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 161, 309, 335);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tid", "startTime", "arrow", "arriveTime", "time"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton getTrainBtn = new JButton("New button");
		getTrainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TID = "test";
				String startTime = "11:20";
				String arror = "→";
				String arriveTime = "13:20";
				String drivingTime = "2:00";
				
				Object[] row = {TID, startTime, arror, arriveTime, drivingTime};
	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				model.addRow(row);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				//table.setDefaultRenderer(String.class, centerRenderer);
				int count = table.getColumnCount();
				for (int i = 0; i < count; i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
				table.setRowSelectionAllowed(true);
				table.setRowHeight(30);
			}
		});
		getTrainBtn.setBounds(250, 70, 0, 0);
		getTrainBtn.doClick();
		contentPane.add(getTrainBtn);
	}
}
