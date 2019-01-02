package dbconnector;

import data.Ticket;
import data.Train;
import discount.Standard;

public class QueryTest implements QueryInterface{

	@Override
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType,
			int ticketQty) {
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
		return trainList;
	}

	@Override
	public String[] getUnavailableSeatList(Train train) {
		String[] unavailableSeatList = {"0101C", "0101D", "0102A", "0601C", "0602A"};
		return unavailableSeatList;
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
		
		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 7, 4, 2, 0.85, trainATimetable);
		
		Ticket t1 = new Ticket(null, trainA, "板橋", "台中", Ticket.CartStandard, "0213B", new Standard());
		Ticket t2 = new Ticket(null, trainA, "板橋", "台中", Ticket.CartStandard, "0213C", new Standard());
		Ticket t3 = new Ticket(null, trainA, "板橋", "台中", Ticket.CartStandard, "0213D", new Standard());
		
		Ticket[] tickets = {t1, t2, t3};
		return tickets;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Train[] checkTimetable(String date) {
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
		return trainList;
	}

}
