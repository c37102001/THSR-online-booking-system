package data;

import java.util.ArrayList;
import java.util.Random;

public class Order {
	private String uid;
	private int orderNumber;
	private int totalPrice;
	private ArrayList<Ticket> ticketList;
    
    public Order(String uid) {
    	this.orderNumber = Math.abs(new Random().nextInt());
    	this.uid = uid;
    	this.totalPrice = 0;
    	this.ticketList = new ArrayList<Ticket>();
    }
    
    public String getUid() {
		return uid;
	}
    
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public ArrayList<Ticket> getTicketList(){
		return ticketList;
	}
}
