package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import data.Ticket;
import data.Train;
import dbconnector.QueryInterface;
import discount.Children;
import discount.Discount;
import discount.EarlyBird;
import discount.Elderly;
import discount.NeedLove;
import discount.Standard;
import discount.Student;
import discount.Student50;
import discount.Student85;
import service.TrainService;
import service.TrainServiceInterface;

public class SearchTrainController {
	
	public QueryInterface query;
	public TrainServiceInterface trainService;
	
	//TODO delete this test constructor
	public SearchTrainController(TrainServiceInterface trainService) {
		this.trainService = trainService;
	}
	
	public SearchTrainController(QueryInterface query, TrainServiceInterface trainService) {
		this.query = query;
		this.trainService = trainService;
	}
	
	public ArrayList<Object> displayTrainList(String date, String startStation, String endStation, String startTime, int cartType, int[] ticketTypes) {
		Train[] trainList;
		int ticketQty = ticketTypes.length;
		trainList = query.searchTrain(date, startStation, endStation, startTime, cartType, ticketQty);
		
		int standardT = ticketTypes[0];
		int studentT = ticketTypes[4];
		
		sortTrainByTime(trainList, startStation);
		String[] trainInfoList = getTrainInfo(trainList, startStation, endStation, standardT, studentT);
		
		ArrayList<Object> resultList = new ArrayList<Object>();
		resultList.add(trainInfoList);
		resultList.add(trainList);
		return resultList;
	}
	
	private void sortTrainByTime(Train[] trainList, String startStation) {

		Arrays.sort(trainList, new Comparator<Train>() {
			@Override
			public int compare(Train train1, Train train2) {
				int startTime1 = Integer.parseInt(trainService.getStationTime(train1, startStation));
				int startTime2 = Integer.parseInt(trainService.getStationTime(train2, startStation));
				if(startTime1 > startTime2) 
					return 1;
				else if(startTime1 < startTime2) 
					return -1;
				else
					return 0;
			}
	    });
	}
	
	private String[] getTrainInfo(Train[] trainList, String startStation, String endStation, int standardT, int studentT) {
		
		String[] trainInfoList = new String[trainList.length];
		
		for(int i=0; i<trainList.length; i++) {
			Train train = trainList[i];
			String trainInfo = String.format("車號:%s, 從 %s(%s) 到 %s(%s)", train.getTid(), 
					startStation, trainService.getStationTime(train, startStation),
					endStation, trainService.getStationTime(train, endStation));
			
			if(standardT != 0 && trainService.checkEarlyBird(train, standardT) instanceof EarlyBird) 
				trainInfo += ", " + trainService.checkEarlyBird(train, standardT).getName();
			
			if(studentT != 0 && train.getUniversityDiscount() instanceof Student)
				trainInfo += ", " + train.getUniversityDiscount().getName();
			
			trainInfoList[i] = trainInfo;
		}
		return trainInfoList;
	}

	
	public ArrayList<Object> displayTrainListTest(Train[] trainList, String startStation, String endStation, int cartType, int[] ticketTypes) {
		
		int standardT = ticketTypes[0];
		int studentT = ticketTypes[4];
		
		sortTrainByTime(trainList, startStation);
		String[] trainInfoList = getTrainInfo(trainList, startStation, endStation, standardT, studentT);
		
		ArrayList<Object> resultList = new ArrayList<Object>();
		resultList.add(trainInfoList);
		resultList.add(trainList);
		return resultList;
	}
	
	public static void main(String[] args) {
		
		String uid = "c37102001";
		String startStation = "台北";
		String endStation = "桃園";
		String date = "2018/12/25";
		String startTime = "0600";
		int cartType = Ticket.CartStandard;
		int seatPrefer = Ticket.SeatAisle;
		
		int standard = 2;
		int children = 3;
		int elderly = 0;
		int needlove = 0;
		int student = 3;
		int[] ticketTypes = {standard, children, elderly, needlove, student};
		
		//-------------------------------------
		int ticketQty = 8;
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 7, 4, 2, new Student85(), trainATimetable);
		String[] trainBTimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainB = new Train("928", "2018/12/25", 0, 0, 13, new Standard(), trainBTimetable);
		String[] trainCTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainC = new Train("724", "2018/12/25", 0, 0, 0, new Standard(), trainCTimetable);
		String[] trainDTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainD = new Train("1001", "2018/12/25", 0, 0, 1, new Student50(), trainDTimetable);
		
		Train[] trainList = {trainA, trainB, trainC, trainD};
		
		SearchTrainController searchMan = new SearchTrainController(new TrainService());
		ArrayList<Object> result = searchMan.displayTrainListTest(trainList, startStation, endStation, Ticket.CartStandard, ticketTypes);
		String[] trainInfoList = (String[]) result.get(0);
		Train[] trainResultList = (Train[]) result.get(1);
		for(int i=0; i<trainInfoList.length; i++) {
			String trainInfo = trainInfoList[i];
			System.out.println(trainInfo);
			//System.out.println(trainResultList[i].getTid());
		}
	}
}
