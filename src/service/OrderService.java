package service;

import data.Order;
import data.Ticket;

public class OrderService implements OrderServiceInterface{

	public void addTicket(Order order, Ticket ticket) {
        order.getTicketList().add(ticket);
        updateTotalPrice(order);
    }
    
    public void deleteTicket(Order order, int deleteNum) {
        for(int i=0; i<deleteNum; i++) {
        	order.getTicketList().remove(0);
        	updateTotalPrice(order);
        }
    }
    
    private void updateTotalPrice(Order order) {
    	int newTotalPrice = 0;
    	for(Ticket ticket : order.getTicketList()) 
    		newTotalPrice += ticket.getPrice();
    	order.setTotalPrice(newTotalPrice);
    }
}
