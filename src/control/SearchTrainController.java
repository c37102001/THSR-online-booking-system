package control;

import data.Ticket;
import data.Train;
import discount.*;
import service.OrderServiceInterface;
import service.TrainService;
import service.TrainServiceInterface;
import dbconnector.QueryInterface;

public class SearchTrainController {
	
	public QueryInterface query;
	public TrainServiceInterface trainService;
	
	public SearchTrainController(QueryInterface query, TrainServiceInterface trainService) {
		this.query = query;
		this.trainService = trainService;
	}
	
	public void DisplayTrainList(String date, String startStation, String endStation, String stime, int cartType, int seatPrefer, int ticketQty) {
		Train[] trainList;
		trainList = query.searchTrain(date, startStation, endStation, stime, cartType, seatPrefer, ticketQty);
	}
	

	public static void main(String[] args) {
		
		String uid = "c37102001";
		String startStation = "еxе_";
		String endStation = "оч╢щ";
		String date = "2018/12/25";
		String stime = "0600";
		int cartType = Ticket.CartStandard;
		int seatPrefer = Ticket.SeatAisle;
		Discount discount = new Standard();
		int ticketQty = 3;
		
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 5, 10, 15, new Student85(), trainATimetable);
		
	}

}
