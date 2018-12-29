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
	
	public void displayTrainList(String date, String startStation, String endStation, String startTime, int cartType, Discount[] ticketTypes) {
		Train[] trainList;
		int ticketQty = ticketTypes.length;
		trainList = query.searchTrain(date, startStation, endStation, startTime, cartType, ticketQty);
		
		int standardT = 0, childrenT = 0, elderlyT = 0, needLoveT = 0, studentT = 0;
		for(Discount discount : ticketTypes) {
			if(discount instanceof Standard) standardT ++;
			else if(discount instanceof Children) childrenT ++;
			else if(discount instanceof Elderly) elderlyT ++;
			else if(discount instanceof NeedLove) needLoveT ++;
			else if(discount instanceof Student) studentT ++;
		}
		
		
		for(Train train : trainList) {
			String discountInfo = "";
			
			if(trainService.checkEarlyBird(train, standardT) instanceof EarlyBird) 
				discountInfo += " " + trainService.checkEarlyBird(train, studentT).getName();
			
			if(train.getUniversityDiscount() instanceof Student)
				discountInfo += " " + train.getUniversityDiscount().getName();
			
		}
		
		
	}
	

	/*
	public static void main(String[] args) {
		
		String uid = "c37102001";
		String startStation = "еxе_";
		String endStation = "оч╢щ";
		String date = "2018/12/25";
		String startTime = "0600";
		int cartType = Ticket.CartStandard;
		int seatPrefer = Ticket.SeatAisle;
		
		int standard = 2;
		int children = 3;
		int elderly = 0;
		int needlove = 0;
		int student = 3;
		
		//-------------------------------------
		Boolean studentDiscount = true;
		int ticketQty = 8;
		
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 5, 10, 15, new Student85(), trainATimetable);
	}
	*/

}
