package dbconnector;

import data.Ticket;
import data.Train;

public class QueryTest implements QueryInterface{

	@Override
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType,
			int ticketQty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getUnavailableSeatList(Train train) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTicket(String orderNumber, String uid, Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEarlyBird(Train train, double earlyBirdDiscount, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSeatLeft(Train train, int cartType, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket[] getOrderTicket(String uid, String orderNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Train[] checkTimetable(String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
