package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import other.GroupSpinner;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.toedter.calendar.JDateChooser;

import data.Ticket;

import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UIMainPage extends JFrame {
	
	private JPanel contentPane;
	private JTextField userID;
	private final ButtonGroup buttonGroup_Seat = new ButtonGroup();
	private final ButtonGroup buttonGroup_Cart = new ButtonGroup();
	private JTextField uid_order;
	private JTextField orderNumber;
	private JComboBox<String> startStation = new JComboBox<String>();
	private JComboBox<String> endStation = new JComboBox<String>();
	//private ActionHandler boxHandler = new ActionHandler();
	private ItemHandler boxItem = new ItemHandler();
	
	// variables that need to be passed to UIOrderPage;
	private String uid, startStn, endStn, date, time;
	private int cartType, seatPrefer, adultNum, elderNum, studentNum, kidNum, priorNum;
			
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMainPage frame = new UIMainPage();
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
	public UIMainPage() {
		setTitle("\u9AD8\u9435\u8A02\u7968\u7CFB\u7D71");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 350, 585);
		setResizable(false);
		
		/**
		 * OrderPanel
		 */
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
		lblNewLabel_5.setBounds(-5, 0, 249, 85);
		contentPane.add(lblNewLabel_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 90, 344, 466);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u8A02\u7968", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4F7F\u7528\u8005ID");
		lblNewLabel.setBounds(30, 30, 60, 21);
		panel.add(lblNewLabel);
		
		userID = new JTextField();
		userID.setBounds(100, 30, 121, 21);
		panel.add(userID);
		userID.setColumns(10);
		
		JLabel label = new JLabel("\u8D77\u8A16\u7AD9");
		label.setBounds(30, 80, 60, 21);
		panel.add(label);
		
		//JComboBox<String> startStation = new JComboBox<String>();
		startStation.setModel(new DefaultComboBoxModel<String>(new String[] {"\u5357\u6E2F", "\u53F0\u5317", "\u677F\u6A4B", "\u6843\u5712", "\u65B0\u7AF9", "\u82D7\u6817", "\u53F0\u4E2D", "\u5F70\u5316", "\u96F2\u6797", "\u5609\u7FA9", "\u53F0\u5357", "\u5DE6\u71DF"}));
		startStation.setBounds(100, 80, 60, 21);
		startStation.setSelectedIndex(1);
		//startStation.addActionListener(boxHandler);
		startStation.addItemListener(boxItem);
		panel.add(startStation);
		
		JLabel label_1 = new JLabel("\u81F3");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(160, 80, 61, 21);
		panel.add(label_1);
		
		//JComboBox<String> endStation = new JComboBox<String>();
		endStation.setModel(new DefaultComboBoxModel<String>(new String[] {"\u5357\u6E2F", "\u53F0\u5317", "\u677F\u6A4B", "\u6843\u5712", "\u65B0\u7AF9", "\u82D7\u6817", "\u53F0\u4E2D", "\u5F70\u5316", "\u96F2\u6797", "\u5609\u7FA9", "\u53F0\u5357", "\u5DE6\u71DF"}));
		endStation.setBounds(220, 80, 60, 21);
		endStation.setSelectedIndex(6);
		//endStation.addActionListener(boxHandler);
		endStation.addItemListener(boxItem);
		panel.add(endStation);
		
		JLabel lblNewLabel_1 = new JLabel("\u6642\u9593");
		lblNewLabel_1.setBounds(30, 130, 60, 21);
		panel.add(lblNewLabel_1);
		
		Date current = new Date();
		Date max = new Date();
		Calendar daycount = Calendar.getInstance();
		daycount.setTime(current);
		daycount.add(Calendar.DATE, 27);
		max = daycount.getTime();
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setSelectableDateRange(current, max);
		dateChooser.setDate(current);
		dateChooser.setBounds(100, 130, 121, 21);
		panel.add(dateChooser);
		
		JSpinner timePicker = new JSpinner();
		timePicker.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de = new JSpinner.DateEditor(timePicker, "HH:mm");
		timePicker.setEditor(de);
		timePicker.setBounds(240, 130, 60, 21);
		panel.add(timePicker);
		
		JLabel label_2 = new JLabel("\u8ECA\u5EC2\u7A2E\u985E");
		label_2.setBounds(30, 180, 60, 21);
		panel.add(label_2);
		
		JRadioButton stdCart = new JRadioButton("\u6A19\u6E96");
		stdCart.setSelected(true);
		buttonGroup_Cart.add(stdCart);
		stdCart.setBounds(100, 180, 60, 21);
		panel.add(stdCart);
		
		JLabel label_seatPrefer = new JLabel("\u5EA7\u4F4D\u504F\u597D");
		label_seatPrefer.setBounds(30, 230, 60, 21);
		panel.add(label_seatPrefer);
		
		JRadioButton seatNone = new JRadioButton("\u7121");
		seatNone.setSelected(true);
		buttonGroup_Seat.add(seatNone);
		seatNone.setBounds(100, 230, 60, 21);
		panel.add(seatNone);
		
		JRadioButton seatAisle = new JRadioButton("\u8D70\u9053");
		buttonGroup_Seat.add(seatAisle);
		seatAisle.setBounds(170, 230, 60, 21);
		panel.add(seatAisle);
		
		JRadioButton seatWindow = new JRadioButton("\u9760\u7A97");
		buttonGroup_Seat.add(seatWindow);
		seatWindow.setBounds(240, 230, 60, 21);
		panel.add(seatWindow);
		
		JLabel label_ticket = new JLabel("\u8ECA\u7968\u5F35\u6578");
		label_ticket.setBounds(30, 280, 60, 21);
		panel.add(label_ticket);
		
		
		// Create Group Spinner
		GroupSpinner group = new GroupSpinner(10);
		
		// Create ChangeListener of Ticket Spinner
		ChangeListener spinnerChange = new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if (group.getGroupValue() > 1) {
					seatNone.setSelected(true);
					label_seatPrefer.setVisible(false);
					seatNone.setVisible(false);
					seatAisle.setVisible(false);
					seatWindow.setVisible(false);
				}
				else {
					label_seatPrefer.setVisible(true);
					seatNone.setVisible(true);
					seatAisle.setVisible(true);
					seatWindow.setVisible(true);
				}
			}
		};
		
		// Ticket: adult
		JLabel label_adult = new JLabel("\u5168\u7968");
		label_adult.setBounds(100, 280, 40, 21);
		panel.add(label_adult);
		
		JSpinner adult = new JSpinner(group.createGroupModel(1, 0, 10, 1));
		adult.addChangeListener(spinnerChange);
		adult.setBounds(145, 280, 35, 21);
		panel.add(adult);
		
		// Ticket: elder
		JLabel label_elder = new JLabel("\u656C\u8001");
		label_elder.setBounds(100, 310, 40, 21);
		panel.add(label_elder);
		
		JSpinner elder = new JSpinner(group.createGroupModel(0, 0, 10, 1));
		elder.addChangeListener(spinnerChange);
		elder.setBounds(145, 310, 35, 21);
		panel.add(elder);
		
		// Ticket: student
		JLabel label_student = new JLabel("\u5927\u5B78\u751F");
		label_student.setBounds(100, 340, 40, 21);
		panel.add(label_student);
		
		JSpinner student = new JSpinner(group.createGroupModel(0, 0, 10, 1));
		student.addChangeListener(spinnerChange);
		student.setBounds(145, 340, 35, 21);
		panel.add(student);
		
		// Ticket: kid
		JLabel label_kid = new JLabel("\u5B69\u7AE5");
		label_kid.setBounds(220, 280, 40, 21);
		panel.add(label_kid);
		
		JSpinner kid = new JSpinner(group.createGroupModel(0, 0, 10, 1));
		kid.addChangeListener(spinnerChange);
		kid.setBounds(265, 280, 35, 21);
		panel.add(kid);
		
		// Ticket: prior
		JLabel label_prior = new JLabel("\u611B\u5FC3");
		label_prior.setBounds(220, 310, 40, 21);
		panel.add(label_prior);
		
		JSpinner prior = new JSpinner(group.createGroupModel(0, 0, 10, 1));
		prior.addChangeListener(spinnerChange);
		prior.setBounds(265, 310, 35, 21);
		panel.add(prior);
		
		JRadioButton busCart = new JRadioButton("\u5546\u52D9");
		busCart.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					label_student.setVisible(false);
					student.setVisible(false);
					student.setValue(0);
				} else {
					label_student.setVisible(true);
					student.setVisible(true);
				}
			}
		});
		buttonGroup_Cart.add(busCart);
		busCart.setBounds(170, 180, 60, 21);
		panel.add(busCart);
		
		// when the btn onclick, go to UIOrderPage
		JButton searchCandidateBtn = new JButton("\u8ECA\u6B21\u67E5\u8A62");
		searchCandidateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uid = userID.getText();
				startStn = startStation.getSelectedItem().toString();
				endStn = endStation.getSelectedItem().toString();
				DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				date = dateformat.format(dateChooser.getDate());
				DateFormat timeformat = new SimpleDateFormat("HH:mm");
				time = timeformat.format(timePicker.getValue());

				if (stdCart.isSelected()) {
					cartType = Ticket.CartStandard;
				} else {
					cartType = Ticket.CartBusiness;
				}

				if (seatNone.isSelected()) {
					seatPrefer = Ticket.SeatNoPrefer;
				} else if (seatAisle.isSelected()) {
					seatPrefer = Ticket.SeatAisle;
				} else {
					seatPrefer = Ticket.SeatWindow;
				}

				adultNum = (int)adult.getValue();
				elderNum = (int)elder.getValue();
				studentNum = (int)student.getValue();
				kidNum = (int)kid.getValue();
				priorNum = (int) prior.getValue();
				int[] ticketTypes = {adultNum, kidNum, elderNum, priorNum, studentNum};
				
				// UIMainPage
				UIOrderPage orderpage = new UIOrderPage(uid, startStn, endStn, date, time, cartType, seatPrefer, ticketTypes);
				orderpage.setVisible(true);
			}
		});
		searchCandidateBtn.setBounds(10, 390, 319, 35);
		panel.add(searchCandidateBtn);
		
		/*
		 * SearchOrder Panel
		 */
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u8A02\u55AE\u67E5\u8A62", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblid = new JLabel("\u4F7F\u7528\u8005ID");
		lblid.setBounds(30, 30, 60, 21);
		panel_1.add(lblid);
		
		uid_order = new JTextField();
		uid_order.setBounds(100, 30, 121, 21);
		panel_1.add(uid_order);
		uid_order.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8A02\u55AE\u7DE8\u865F");
		label_3.setBounds(30, 80, 60, 21);
		panel_1.add(label_3);
		
		orderNumber = new JTextField();
		orderNumber.setBounds(100, 80, 121, 21);
		panel_1.add(orderNumber);
		orderNumber.setColumns(10);
		
		JButton searchOrderBtn = new JButton("\u67E5\u8A62\u8A02\u55AE");
		searchOrderBtn.setBounds(10, 390, 319, 35);
		panel_1.add(searchOrderBtn);
		
		/*
		 * SearchTimetable Panel
		 */
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u9AD8\u9435\u6642\u523B\u8868", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\u9078\u64C7\u65E5\u671F");
		lblNewLabel_3.setBounds(30, 30, 60, 21);
		panel_2.add(lblNewLabel_3);
		
		JButton showTimetableBtn = new JButton("\u67E5\u8A62");
		showTimetableBtn.setBounds(10, 390, 319, 35);
		panel_2.add(showTimetableBtn);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(100, 30, 121, 21);
		panel_2.add(dateChooser_1);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (startStation.getSelectedIndex() == endStation.getSelectedIndex()){
				startStation.setSelectedIndex(0);
				endStation.setSelectedIndex(11);
				JOptionPane.showMessageDialog(null, "�z��ܤ��_/�W�����ۦP�A�Э��s��J�C", "�_�W����J���~�I", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private class ItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if (e.getStateChange() == ItemEvent.SELECTED) {
				//prevItem = e.getItem();
				if (startStation.getSelectedIndex() == endStation.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "�z��ܤ��_/�W�����ۦP�A�Э��s��J�C", "�_�W����J���~�I", JOptionPane.INFORMATION_MESSAGE);
					startStation.setSelectedIndex(1);
					endStation.setSelectedIndex(6);
				}
			}
		}
	}
}
