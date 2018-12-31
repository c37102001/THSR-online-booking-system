package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public Train[] getTrainList(String date, String startStation, String endStation, String startTime, int cartType, int[] ticketTypes) {
		Train[] trainList;
		int ticketQty=0;
		for(int ticketNum : ticketTypes)
			ticketQty += ticketNum;
		trainList = query.searchTrain(date, startStation, endStation, startTime, cartType, ticketQty);
		
		sortTrainByTime(trainList, startStation);
		
		return trainList;
	}
	public Train[] getTrainListTest(String date, String startStation, String endStation, String startTime, int cartType, int[] ticketTypes) {
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 7, 4, 2, 0.85, trainATimetable);
		String[] trainBTimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainB = new Train("928", "2018/12/25", 0, 0, 13, 1, trainBTimetable);
		String[] trainCTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainC = new Train("724", "2018/12/25", 0, 0, 0, 1, trainCTimetable);
		String[] trainDTimetable = {"1100", "1113", "1131", "1204", "1237", "1245", "1300", "1322", "1327", "1345", "1453", "1500"};
		Train trainD = new Train("1001", "2018/12/25", 0, 0, 1, 0.5, trainDTimetable);
		String[] trainETimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainE = new Train("72", "2018/12/25", 7, 4, 2, 0.85, trainETimetable);
		String[] trainFTimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainF = new Train("9487", "2018/12/25", 0, 0, 13, 1, trainFTimetable);
		String[] trainGTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainG = new Train("6666", "2018/12/25", 0, 0, 0, 1, trainGTimetable);
		String[] trainHTimetable = {"1100", "1113", "1131", "1204", "1237", "1245", "1300", "1322", "1327", "1345", "1453", "1500"};
		Train trainH = new Train("777", "2018/12/25", 0, 0, 1, 0.5, trainHTimetable);
		String[] trainITimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainI = new Train("4567", "2018/12/25", 7, 4, 2, 0.85, trainITimetable);
		String[] trainJTimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainJ = new Train("1234", "2018/12/25", 0, 0, 13, 1, trainJTimetable);
		String[] trainKTimetable = {"1100", "1113", "1121", "1154", "1227", "1235", "1250", "1312", "1327", "1343", "1402", "1420"};
		Train trainK = new Train("432", "2018/12/25", 0, 0, 0, 1, trainKTimetable);
		String[] trainLTimetable = {"1100", "1113", "1131", "1204", "1237", "1245", "1300", "1322", "1327", "1345", "1453", "1500"};
		Train trainL = new Train("101", "2018/12/25", 0, 0, 1, 0.5, trainLTimetable);

		
		Train[] trainList = {trainA, trainB, trainC, trainD, trainE, trainF, trainG, trainH, trainI, trainJ, trainK, trainL};
		
		sortTrainByTime(trainList, startStation);
		return trainList;
	}
	
	private void sortTrainByTime(Train[] trainList, String startStation) {

		Arrays.sort(trainList, new Comparator<Train>() {
			@Override
			public int compare(Train train1, Train train2) {
				int startTime1 = Integer.parseInt(train1.getTimetable(startStation));
				int startTime2 = Integer.parseInt(train2.getTimetable(startStation));
				if(startTime1 > startTime2) 
					return 1;
				else if(startTime1 < startTime2) 
					return -1;
				else
					return 0;
			}
	    });
	}
	
	public int getTotalPrice(Train train, String startStation, String endStation, int cartType, int[] ticketTypes) {
		int totalPrice = 0;
		int standardT = ticketTypes[0];
		int childrenT = ticketTypes[1];
		int elderlyT = ticketTypes[2];
		int needLoveT = ticketTypes[3];
		int studentT = ticketTypes[4];
		
		totalPrice += Price.getPrice(startStation, endStation, cartType, new Children()) * childrenT;
		totalPrice += Price.getPrice(startStation, endStation, cartType, new Elderly()) * elderlyT;
		totalPrice += Price.getPrice(startStation, endStation, cartType, new NeedLove()) * needLoveT;
		totalPrice += Price.getPrice(startStation, endStation, cartType, train.getUniversityDiscount()) * studentT;
		
		if(standardT != 0 && trainService.checkEarlyBird(train, standardT) instanceof EarlyBird)
			totalPrice += Price.getPrice(startStation, endStation, cartType, trainService.checkEarlyBird(train, standardT)) * standardT;
		else
			totalPrice += Price.getPrice(startStation, endStation, cartType, new Standard()) * standardT;
		
		return totalPrice;
	}
	
	public String totalTimeCulculator(Train train, String startStation, String endStation) {
		
		String startTime = train.getTimetable(startStation);
		String endTime = train.getTimetable(endStation);
		
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
	
	public String checkEarlyBird(Train train, int standardT) {
		String earlyBirdDiscount = "";
		if(standardT != 0 && trainService.checkEarlyBird(train, standardT) instanceof EarlyBird)
			earlyBirdDiscount = ", " + new TrainService().checkEarlyBird(train, standardT).getName();
		else
			earlyBirdDiscount = "";
		return earlyBirdDiscount;
	}
	
	public String checkStudent(Train train, int studentT) {
		String studentDiscount = "";
		if(studentT != 0 && train.getUniversityDiscount() instanceof Student)
			studentDiscount = ", " + train.getUniversityDiscount().getName();
		else
			studentDiscount = "";
		return studentDiscount;
	}
	
	public static void main(String[] args) {
		
		String startStation = "台北";
		String endStation = "桃園";
		String date = "2018/12/25";
		String startTime = "0600";
		int cartType = Ticket.CartStandard;
		
		int standard = 2;
		int children = 1;
		int elderly = 1;
		int needlove = 1;
		int student = 2;
		int[] ticketTypes = {standard, children, elderly, needlove, student};
		
		SearchTrainController searchMan = new SearchTrainController(new TrainService());
		Train[] sortedTrainList = searchMan.getTrainListTest(date, startStation, endStation, startTime, cartType, ticketTypes);
		
		for(Train train : sortedTrainList) {
			System.out.println(
					"車號: " + train.getTid() + 
					", 從 " + startStation +"("+ train.getTimetable(startStation) + 
					") 到 " + endStation +"("+ train.getTimetable(endStation) + 
					"), 總價:" + searchMan.getTotalPrice(train, startStation, endStation, cartType, ticketTypes) + 
					", 行車時間:" + searchMan.totalTimeCulculator(train, startStation, endStation) + 
					searchMan.checkEarlyBird(train, standard) +
					searchMan.checkStudent(train, student));
		}
	}
}
