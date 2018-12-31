package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import data.Price;
import data.Ticket;
import data.Train;
import dbconnector.QueryInterface;
import discount.Children;
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
		int ticketQty=0;
		for(int ticketNum : ticketTypes)
			ticketQty += ticketNum;
		trainList = query.searchTrain(date, startStation, endStation, startTime, cartType, ticketQty);
		
		sortTrainByTime(trainList, startStation);
		String[][] trainInfoList = getTrainInfo(trainList, startStation, endStation, cartType, ticketTypes);
		
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
	
	private String[][] getTrainInfo(Train[] trainList, String startStation, String endStation, int cartType, int[] ticketTypes) {
		
		int standardT = ticketTypes[0];
		int childrenT = ticketTypes[1];
		int elderlyT = ticketTypes[2];
		int needLoveT = ticketTypes[3];
		int studentT = ticketTypes[4];
		
		String[][] result = new String[7][];
		
		String[] idList = new String[trainList.length];
		String[] startTimeList = new String[trainList.length];
		String[] endTimeList = new String[trainList.length];
		String[] totalTimeList = new String[trainList.length];
		String[] earlyBirdList = new String[trainList.length];
		String[] studentList = new String[trainList.length];
		String[] priceList = new String[trainList.length];
		
		for(int i=0; i<trainList.length; i++) {
			Train train = trainList[i];
			int totalPrice = 0;
			totalPrice += Price.getPrice(startStation, endStation, cartType, new Children()) * childrenT;
			totalPrice += Price.getPrice(startStation, endStation, cartType, new Elderly()) * elderlyT;
			totalPrice += Price.getPrice(startStation, endStation, cartType, new NeedLove()) * needLoveT;
			
			idList[i] = train.getTid();
			
			startTimeList[i] = trainService.getStationTime(train, startStation);
			
			endTimeList[i] = trainService.getStationTime(train, endStation);
			
			totalTimeList[i] = totalTimeCulculator(startTimeList[i], endTimeList[i]);
			
			if(standardT != 0 && trainService.checkEarlyBird(train, standardT) instanceof EarlyBird) {
				earlyBirdList[i] = trainService.checkEarlyBird(train, standardT).getName();
				totalPrice += Price.getPrice(startStation, endStation, cartType, trainService.checkEarlyBird(train, standardT)) * standardT;
			}
			else{
				earlyBirdList[i] = null;
				totalPrice += Price.getPrice(startStation, endStation, cartType, new Standard()) * standardT;
			}
			
			if(studentT != 0 && train.getUniversityDiscount() instanceof Student) {
				studentList[i] = train.getUniversityDiscount().getName();
				totalPrice += Price.getPrice(startStation, endStation, cartType, train.getUniversityDiscount()) * studentT;
			}
			else{
				studentList[i] = null;
				totalPrice += Price.getPrice(startStation, endStation, cartType, new Standard()) * studentT;
			}
			
			priceList[i] = "" + totalPrice;
		}
		
		result[0] = idList;
		result[1] = startTimeList;
		result[2] = endTimeList;
		result[3] = totalTimeList;
		result[4] = earlyBirdList;
		result[5] = studentList;
		result[6] = priceList;
		return result;
	}
	
	private String totalTimeCulculator(String startTime, String endTime) {
		startTime = String.format("%04d", Integer.parseInt(startTime));
		endTime = String.format("%04d", Integer.parseInt(endTime));
		
		SimpleDateFormat format = new SimpleDateFormat("HHmm");
		long difference = 0;
		try {
			difference = format.parse(endTime).getTime() - format.parse(startTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int hours = (int) (difference/ (60 * 60 * 1000) % 24);
		int mins = (int) (difference / (60 * 1000) % 60);
		String totalTime = String.format("%d:%d", hours, mins);
		return totalTime;
	}
	
	public ArrayList<Object> displayTrainListTest(Train[] trainList, String startStation, String endStation, int cartType, int[] ticketTypes) {
		
		sortTrainByTime(trainList, startStation);
		String[][] trainInfoList = getTrainInfo(trainList, startStation, endStation, cartType, ticketTypes);
		
		ArrayList<Object> resultList = new ArrayList<Object>();
		resultList.add(trainInfoList);
		resultList.add(trainList);
		return resultList;
	}
	
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
		int[] ticketTypes = {standard, children, elderly, needlove, student};
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 7, 4, 2, 0.85, trainATimetable);
		String[] trainBTimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainB = new Train("928", "2018/12/25", 0, 0, 13, 1, trainBTimetable);
		String[] trainCTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainC = new Train("724", "2018/12/25", 0, 0, 0, 1, trainCTimetable);
		String[] trainDTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainD = new Train("1001", "2018/12/25", 0, 0, 1, 0.5, trainDTimetable);
		
		Train[] trainList = {trainA, trainB, trainC, trainD};
		
		SearchTrainController searchMan = new SearchTrainController(new TrainService());
		ArrayList<Object> result = searchMan.displayTrainListTest(trainList, startStation, endStation, Ticket.CartStandard, ticketTypes);
		String[][] trainInfoList = (String[][]) result.get(0);
		for(String[] infos : trainInfoList) {
			for(String info : infos) {
				System.out.println(info);
			}
			System.out.println();
		}
	}
}
