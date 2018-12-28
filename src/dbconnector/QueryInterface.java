package dbconnector;
import data.Train;

public interface QueryInterface {
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty);
}
