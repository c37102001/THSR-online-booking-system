package dbconnector;

import data.Ticket;
import data.Train;

public class QueryAdapter implements QueryInterface {

	@Override
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty) {
		return null;
	}

	@Override
	public String[] getUnavailableSeatList(Train train) {
		return null;
	}

	@Override
	public void addTicket(String orderNumber, String uid, Ticket ticket) {
	}

	@Override
	public void updateEarlyBird(Train train, double earlyBirdDiscount, int num) {
	}

	@Override
	public void updateSeatLeft(Train train, int cartType, int num) {
	}

	@Override
	public Ticket[] getOrderTicket(String uid, String orderNumber) {
		return null;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
	}

	@Override
	public Train[] checkTimetable(String date, int direction) {
		return null;
	}

}
