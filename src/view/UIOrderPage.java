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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.SearchTrainController;
import data.Train;
import dbconnector.Query;
import service.TrainService;

public class UIOrderPage extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private String tid, startTime, endTime;
	private Train train;

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

	public UIOrderPage(String uid, String startStn, String endStn, String date,
			String time, int cartType, int seatPrefer, int[] ticketTypes, Train[] trainList) {
		
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

		JButton btnCancel = new JButton("\u53D6\u6D88\u8A02\u7968");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 513, 163, 40);
		contentPane.add(btnCancel);

		JButton btnOrder = new JButton("\u78BA\u8A8D\u8A02\u7968");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//UITicketPage ticketpage = new UITicketPage(uid, date, tid, startStn, startTime, endStn, endTime, ticketTypes);
				UITicketPage ticketpage = new UITicketPage(uid, date, startStn, endStn, train, cartType, ticketTypes);
				ticketpage.setVisible(true);
			}
		});
		btnOrder.setEnabled(false);
		btnOrder.setBounds(181, 513, 163, 40);
		contentPane.add(btnOrder);

		JLabel label = new JLabel("\u9078\u64C7\u65E5\u671F");
		label.setBounds(20, 95, 60, 21);
		contentPane.add(label);

		JLabel label_date = new JLabel("New label");
		label_date.setBounds(90, 95, 121, 21);
		label_date.setText(date);
		contentPane.add(label_date);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 130, 324, 373);
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
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		
		// get available train list
		SearchTrainController searchMan = new SearchTrainController(
				new Query(), new TrainService());
		
		/*
		Train[] trainList = searchMan.searchTrain(date, startStn, endStn, time,
				cartType, ticketTypes);
		
		Train[] trainTest = {};
		*/
		for (int i = 0; i < trainList.length; i++) {
			Train train = trainList[i];
			String TID = train.getTid();
			String StartTime = train.getTimetable(startStn);
			String arror = "→";
			String arriveTime = train.getTimetable(endStn);

			String drivingTime = searchMan.getTotalTime(train, startStn,
					endStn);
			
			Object[] row = { TID, StartTime, arror, arriveTime, drivingTime };

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(row);
			
			String earlyBird = searchMan.checkEarlyBird(train, cartType);
			if (earlyBird == "")
				earlyBird = "無";
			
			String student;
			if (ticketTypes[4] != 0) {
				student = searchMan.checkStudent(train, cartType);
				if (student == "")
					student = "無";
				Object[] discount = { "適用優惠", earlyBird, "、", student };
				model.addRow(discount);
			} else {
				Object[] discount = { "適用優惠", earlyBird };
				model.addRow(discount);
			}
		}

		/*
		 * // when you manage to let user choose a train, just replace
		 * "trainList[0]" in bookticket() with the selected one, // and it's
		 * supposed to return the order/ticket details.
		 * 
		 * BookTicketController bookingHelper = new BookTicketController(new
		 * QueryTest(), new TrainService()); Order myorder =
		 * bookingHelper.bookTicket(trainList[0], uid, startStn, endStn,
		 * cartType, seatPrefer, ticketTypes);
		 * 
		 * for(Ticket ticket : myorder.getTicketList()) {
		 * System.out.println("車票代號: " + ticket.getTicketNumber());
		 * System.out.println("車次: " + ticket.getTid());
		 * System.out.println("日期: " + ticket.getDate());
		 * System.out.println("起站: " + ticket.getStart() + "(" +
		 * ticket.getStime() + ")"); System.out.println("迄站: " + ticket.getEnd()
		 * + "(" + ticket.getEtime()+ ")"); System.out.println("座位號碼: " +
		 * ticket.getSeatNum()); System.out.println("票種: " +
		 * ticket.getDiscountType().getName()); System.out.println("價格: " +
		 * ticket.getPrice()); System.out.println(); }
		 * System.out.println("訂單總額:" + myorder.getTotalPrice()); } });
		 * getTrainBtn.setBounds(250, 70, 0, 0); getTrainBtn.doClick();
		 * contentPane.add(getTrainBtn);
		 */

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()){
							train = trainList[table.getSelectedRow()/2];
							//System.out.println("the selected train: " + train.getTid());
							/*
							tid = (String) table.getValueAt(table.getSelectedRow(),
									0);
							startTime = (String) table.getValueAt(
									table.getSelectedRow(), 1);
							endTime = (String) table.getValueAt(
									table.getSelectedRow(), 3);
							*/
							btnOrder.setEnabled(true);
						}
						
					}
				});
		scrollPane.setViewportView(table);
	}
}
