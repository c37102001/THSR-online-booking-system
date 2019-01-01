package control;

import data.Train;
import dbconnector.QueryInterface;
import dbconnector.QueryTest;

public class CheckTimetableController {

	private QueryInterface query;
	
	CheckTimetableController(QueryInterface query){
		this.query = query;
	}
	
	public Train[] checkTimetable(String date) {
		Train[] trainList = query.checkTimetable(date);
		return trainList;
	}
	
	public static void main(String[] args) {
		CheckTimetableController timeWatcher = new CheckTimetableController(new QueryTest());
		String date = "2018/12/25";
		timeWatcher.checkTimetable(date);
	}
}
