package data;

import java.util.ArrayList;
import java.util.Random;

public class Order {
	private String uid;
	private String orderNumber;
	private ArrayList<Ticket> ticketList;
    
    public Order(String uid) {
    	this.orderNumber = Integer.toString(Math.abs(new Random().nextInt()));
    	this.uid = uid;
    	this.ticketList = new ArrayList<Ticket>();
    }
    
    public Order(String uid, String orderNumber) {
    	this.orderNumber = orderNumber;
    	this.uid = uid;
    	this.ticketList = new ArrayList<Ticket>();
    }
    
    public String getUid() {
		return uid;
	}
    
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public int getTotalPrice() {
    	int totalPrice = 0;
    	for(Ticket ticket : ticketList) 
    		totalPrice += ticket.getPrice();
    	return totalPrice; 
    }
	
	public void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }
	
	public void deleteTicket(Ticket deleteTicket) {
		if(ticketList.remove(deleteTicket))
			System.out.printf("delete ticket number: %s successfully", deleteTicket.getTicketNumber());
		else
			System.out.println("delete error: ticket not found");
	}
	
	public ArrayList<Ticket> getTicketList(){
		return ticketList;
	}
	
	public void showTicketDetails() {
		for(Ticket ticket : ticketList) {
			System.out.println("�����N��: " + ticket.getTicketNumber());
			System.out.println("����: " + ticket.getTid());
			System.out.println("���: " + ticket.getDate());
			System.out.println("�_��: " + ticket.getStart() + "(" + ticket.getStime() + ")");
			System.out.println("����: " + ticket.getEnd() + "(" + ticket.getEtime()+ ")");
			System.out.println("�y�츹�X: " + ticket.getSeatNum());
			System.out.println("����: " + ticket.getDiscountType().getName());
			System.out.println("����: " + ticket.getPrice());
			System.out.println();
		}
	}
}
