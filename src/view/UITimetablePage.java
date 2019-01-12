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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import control.CheckTimetableController;
import data.Train;
import dbconnector.QueryTest;

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
		
		JLabel icon = new JLabel("");
		icon.setBounds(-5, 0, 249, 85);
		icon.setIcon(new ImageIcon(getClass().getResource("..\\img\\logo.jpg")));
		contentPane.add(icon);
		
		JLabel label_date = new JLabel("\u67E5\u8A62\u65E5\u671F");
		label_date.setHorizontalAlignment(SwingConstants.RIGHT);
		label_date.setBounds(380, 95, 60, 21);
		contentPane.add(label_date);

		JLabel showDate = new JLabel("New label");
		showDate.setHorizontalAlignment(SwingConstants.RIGHT);
		showDate.setBounds(450, 95, 121, 21);
		showDate.setText(date);
		contentPane.add(showDate);
		
		JLabel label_1 = new JLabel("\u5317\u4E0A");
		label_1.setBounds(10, 95, 60, 21);
		contentPane.add(label_1);
		
		// list train 
		CheckTimetableController timeWatcher = new CheckTimetableController(new QueryTest());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 566, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8ECA\u6B21", "\u5357\u6E2F", "\u53F0\u5317", "\u677F\u6A4B", "\u6843\u5712", "\u65B0\u7AF9", "\u82D7\u6817", "\u53F0\u4E2D", "\u5F70\u5316", "\u96F2\u6797", "\u5609\u7FA9", "\u53F0\u5357", "\u5DE6\u71DF"
			}
		){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		int count = table.getColumnCount();
		for (int i = 0; i < count; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		Train[] trainList = timeWatcher.checkTimetable(date);
		for (int i = 0; i < trainList.length; i++){
			Train train = trainList[i];
			String Nangang = train.getTimetable("南港");
			String Taipei = train.getTimetable("台北");
			String Banqiao = train.getTimetable("板橋");
			String Taoyuan = train.getTimetable("桃園");
			String HsinChu = train.getTimetable("新竹");
			String Miaoli = train.getTimetable("苗栗");
			String Taichung = train.getTimetable("台中");
			String Changhua = train.getTimetable("彰化");
			String Yunlin = train.getTimetable("雲林");
			String Chiayi = train.getTimetable("嘉義");
			String Tainan = train.getTimetable("台南");
			String Zuoying = train.getTimetable("左營");
			
			Object row[] = {train.getTid(), Nangang, Taipei, Banqiao, Taoyuan, HsinChu, Miaoli, Taichung, Changhua, Yunlin, Chiayi, Tainan, Zuoying};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(row);
		}
		
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		
	}
}
