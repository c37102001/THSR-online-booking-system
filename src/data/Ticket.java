package data;

import java.util.Random;

import discount.*;

public class Ticket {
	
	public static final int CartStandard = 1;
	public static final int CartBusiness = 2;
	public static final int SeatWindow = 1;
	public static final int SeatAisle = 2;
	public static final int SeatNoPrefer = 3;
	
	private int ticketNumber;
	private String date;
	private String tid;
	private String startStation;
	private String endStation;
	private String startTime;
	private String endTime;
	private String seatNum;
	private int cartType;
	private Discount discount;
	private int price;
	
	public Ticket(Train train, String startStation, String endStation, int cartType, String seatNum, Discount discount) {
		this.ticketNumber = Math.abs(new Random().nextInt());
		this.date = train.getDate();
		this.tid = train.getTid();
		this.startStation = startStation;
		this.endStation = endStation;
		this.startTime = train.getTimetable(startStation);
		this.endTime = train.getTimetable(endStation);
		this.seatNum = seatNum;
		this.discount = discount;
		this.price = (int) (Price.getPrice(startStation, endStation, cartType, discount));
	}
	
	

	public int getTicketNumber() {
		return ticketNumber;
	}

	public String getDate() {
		return date;
	}

	public String getTid() {
		return tid;
	}

	public String getStart() {
		return startStation;
	}
	public String getEnd() {
		return endStation;
	}

	public String getStime() {
		return startTime;
	}

	public String getEtime() {
		return endTime;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public int getTicketType() {
		return cartType;
	}

	public int getPrice() {
		return price;
	}
	
	public Discount getDiscountType() {
		return discount;
	}
}
