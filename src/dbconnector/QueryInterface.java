package dbconnector;
import java.sql.SQLException;

import data.Ticket;
import data.Train;

public interface QueryInterface {
	
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty);

	// {"0104E", "0312A", "0601B" , ... } 
	public String[] getUnavailableSeatList(Train train);	//tid, date

	public void addTicket(String orderNumber, String uid, Ticket ticket);

	public void updateEarlyBird(Train train, double earlyBirdDiscount, int num);

	public void updateSeatLeft(Train train, int cartType, int num);

	public Ticket[] getOrderTicket(String uid, String orderNumber);

	public void deleteTicket(Ticket ticket);

	public Train[] checkTimetable(String date);
	
}
