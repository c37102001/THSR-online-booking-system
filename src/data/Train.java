package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Train {
	

	private String tid; 
	private String date;
	private int earlyBird65;
	private int earlyBird80;
	private int earlyBird90;
	private double universityDiscount;
	private ArrayList<Cart> cartList;
	private HashMap<String, String> timeTable;
	private static final int BusCartNum = 6;
	private static final int TotalCartNum = 9;
	
	
	public Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, double universityDiscount){
		this.tid = tid;
		this.date = date;
		this.earlyBird65 = earlyBird65;
		this.earlyBird80 = earlyBird80;
		this.earlyBird90 = earlyBird90;
		this.universityDiscount = universityDiscount;
		cartList = new ArrayList<Cart>();
		timeTable = new HashMap<String, String>();
	}
	
	
	public void initCartList() {
		for(int cartNum=1; cartNum<=TotalCartNum; cartNum++) {
			cartList.add(new Cart(cartNum));
		}
	}
	
	
	public String getID() {
		return tid;
	}

	public String getDate() {
		return date;
	}
	
	public double getUniversityDiscount() {
		return universityDiscount;
	}
	
	public double getEarlyBirdDiscount(int number) {
		if(earlyBird65 > number) return 0.65;
		else if(earlyBird80 > number) return 0.85;
		else if(earlyBird90 > number) return 0.90;
		else return 1.0;
	}
	
	
	public int getStdSeatNumber() {
		int seatNumber = 0;
		for(Cart cart : cartList) {
			if(cart.getCartNumber() == BusCartNum) 
				continue;
			seatNumber += cart.getTotalSeatNum();
		}
		return seatNumber;
	}
	
	
	public int getBusSeatNumber() {
		int seatNumber = 0;
		for(Cart cart : cartList) {
			if(cart.getCartNumber() == BusCartNum) 
				seatNumber = cart.getTotalSeatNum();
		}
		return seatNumber;
	}
	
	
	public void setUnavailableSeat(String seatNum) {
		for(Cart cart : cartList) {
			if(cart.getCartNumber() != Integer.parseInt(seatNum.substring(0, 2))) 
				continue;
			cart.setUnavailableSeat(seatNum);
		}
	}
	

	// Update time table 
	public void addTimeTable(String[] time) {
		for(int i=0; i<Station.CHI_NAME.length; i++) {
			timeTable.put(Station.CHI_NAME[i], time[i]);
		}
	} 

	// Show time table
	public String showTimeTable(String state) {
		return timeTable.get(state);
	}
	
	
	public String bookSeat(int seatType, int seatPrefer) {
		
		if(seatType == Ticket.TicketBusiness) {
			for(Cart cart : cartList) {
				if(cart.getCartNumber() != BusCartNum) continue;
				if(seatPrefer == Ticket.SeatWindow && cart.getWindowSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else if(seatPrefer == Ticket.SeatAisle && cart.getAisleSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else 
					return cart.bookSeat();
			}
		}
		
		else {
			for(Cart cart : cartList) {
				if(cart.getCartNumber() == BusCartNum) 
					continue;
				if(seatPrefer == Ticket.SeatWindow && cart.getWindowSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else if(seatPrefer == Ticket.SeatAisle && cart.getAisleSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else 
					return cart.bookSeat();
			}
		}

		return "";
	}
	
	public static void main(String[] args) {
    	Train train = new Train("1072", "2018/12/25", 5, 10, 15, 0.85);
    	System.out.println("Train ID: " + train.getID());
    	System.out.println("Date: " + train.getDate());
    	
    	train.initCartList();
    	System.out.println("Std seat number: " + train.getStdSeatNumber());
    	System.out.println("Bus seat number: " + train.getBusSeatNumber());
    	
    	train.setUnavailableSeat("0102A");
    	System.out.println("Std seat number: " + train.getStdSeatNumber());
    	System.out.println("Bus seat number: " + train.getBusSeatNumber());
    	
    	train.setUnavailableSeat("0101C");
    	System.out.println("Std seat number: " + train.getStdSeatNumber());
    	System.out.println("Bus seat number: " + train.getBusSeatNumber());
    	
    	train.setUnavailableSeat("0101D");
    	System.out.println("Std seat number: " + train.getStdSeatNumber());
    	System.out.println("Bus seat number: " + train.getBusSeatNumber());
    	
    	System.out.println(train.bookSeat(Ticket.TicketStandard, Ticket.SeatWindow));
    	System.out.println(train.bookSeat(Ticket.TicketStandard, Ticket.SeatAisle));
    	System.out.println(train.bookSeat(Ticket.TicketBusiness, Ticket.SeatWindow));
    	System.out.println("Std seat number: " + train.getStdSeatNumber());
    	System.out.println("Bus seat number: " + train.getBusSeatNumber());
    	
    	for(int i=0; i<10; i++) {
    		System.out.println(train.bookSeat(Ticket.TicketStandard, Ticket.SeatNoPrefer));
    	}
    }
}
