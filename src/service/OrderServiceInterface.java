package service;

import data.Order;
import data.Ticket;

public interface OrderServiceInterface {
	public void addTicket(Order order, Ticket ticket);
	public void deleteTicket(Order order, int deleteNum);
}
