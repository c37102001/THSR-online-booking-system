package dbconnector;
import data.Train;

public interface QueryInterface {
	public Train[] searchTrain(String date, String startStation, String endStation, String stime, int cartType, int seatPrefer, 
																													int ticketQty);
}
