package control;

import data.Order;
import data.Ticket;
import dbconnector.QueryInterface;
import dbconnector.TrainDaoImpl;

public class CheckOrderController {
	private QueryInterface query;
	
	public CheckOrderController(QueryInterface query) {
		this.query = query;
	}
	
	public Order checkOrder(String uid, String orderNumber) {
		Ticket[] orderTickets = query.getOrderTicket(uid, orderNumber);
		
		Order order = new Order(uid, orderNumber);
		for(Ticket ticket : orderTickets)
			order.addTicket(ticket);
		return order;
	}
	
	public Order deleteTicket(Order order, Ticket[] tickets) {
		for(Ticket ticket : tickets) {
			order.deleteTicket(ticket);
			query.deleteTicket(ticket);
		}
		return order;
	}

	public static void main(String[] args) {
		CheckOrderController checkBro = new CheckOrderController(new TrainDaoImpl());
		
		String uid = "f";
		String orderNumber = "1912815423";
		
		Order myOrder = checkBro.checkOrder(uid, orderNumber);
		myOrder.showTicketDetails();
//		
//		Ticket[] selectedTickets = {myOrder.getTicketList().get(0)};
//		myOrder = checkBro.deleteTicket(myOrder, selectedTickets);
//		System.out.println();
//		myOrder.showTicketDetails();
		
	}
}
