package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.BookTicketController;
import control.SearchTrainController;
import data.Order;
import data.Ticket;
import data.Train;
import dbconnector.TrainDaoImpl;
import service.TrainService;

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
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { UIOrderPage frame = new
	 * UIOrderPage(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public UIOrderPage(String uid, String startStn, String endStn, String date, String time, int cartType, int seatPrefer, int[] ticketTypes) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 360, 592);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
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
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UITicketPage ticketpage = new UITicketPage();
				ticketpage.setVisible(true);
			}
		});
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
		
		TableModel tmodel = new DefaultTableModel(new Object[][] {}, new String[] { "車次", startStn, "→", endStn, "行車時間" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable();
		table.setShowGrid(false);

		table.setModel(tmodel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		// table.setDefaultRenderer(String.class, centerRenderer);
		int count = table.getColumnCount();
		for (int i = 0; i < count; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowSelectionAllowed(true);
		table.setRowHeight(30);
		
		// get available train list
		SearchTrainController searchMan = new SearchTrainController(new TrainDaoImpl(), new TrainService());
		Train[] trainList = searchMan.searchTrain(date, startStn, endStn, time, cartType, ticketTypes);

		for (int i = 0; i < trainList.length; i++) {
			Train train = trainList[i];
			String TID = train.getTid();
			String startTime = train.getTimetable(startStn);
			String arror = "→";
			String arriveTime = train.getTimetable(endStn);
			String drivingTime = searchMan.getTotalTime(train, startStn, endStn);

			Object[] row = { TID, startTime, arror, arriveTime, drivingTime };

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(row);
		}
		
		
		// when you manage to let user choose a train, just replace "trainList[0]" in bookticket() with the selected one, 
		// and it's supposed to return the order/ticket details.
		if(trainList.length != 0) {
			BookTicketController bookingHelper = new BookTicketController(new TrainDaoImpl(), new TrainService()); 
			Order myorder = bookingHelper.bookTicket(trainList[0], uid, startStn, endStn, cartType, seatPrefer, ticketTypes);
	
			for(Ticket ticket : myorder.getTicketList()) {
				System.out.println("車票代號: " + ticket.getTicketNumber());
				System.out.println("車次: " + ticket.getTid());
				System.out.println("日期: " + ticket.getDate());
				System.out.println("起站: " + ticket.getStart() + "(" + ticket.getStime() + ")");
				System.out.println("迄站: " + ticket.getEnd() + "(" + ticket.getEtime()+ ")"); 
				System.out.println("座位號碼: " + ticket.getSeatNum()); 
				System.out.println("票種: " + ticket.getDiscountType().getName());
				System.out.println("價格: " + ticket.getPrice()); System.out.println();
			}
			System.out.println("訂單總額:" + myorder.getTotalPrice());
	//		getTrainBtn.setBounds(250, 70, 0, 0); getTrainBtn.doClick();
	//		contentPane.add(getTrainBtn);
		}
		 
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnOrder.setEnabled(true);
			}
			
		});
		scrollPane.setViewportView(table);
	}
}
