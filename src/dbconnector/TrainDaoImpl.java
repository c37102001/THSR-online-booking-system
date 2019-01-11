package dbconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;

import data.Ticket;
import data.Train;
import dbconnector.config.DBUtils;

public class TrainDaoImpl extends QueryAdapter {
	private QueryRunner runner = null;
	public TrainDaoImpl(){
		runner = new QueryRunner();
	}

	@Override
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty){
		date = date.replace('/', '-');

		String cartCode;

		if (cartType == Ticket.CartStandard) {
			cartCode = "std_left";
		} else {
			cartCode = "bus_left";
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Train t = null;
		List<Train> trains = new ArrayList<Train>();
		String sql = "select d.`train_id` as tid,d.`date`,d.`early65_left` as early65,d.`early80_left` as early80,"
				+ "d.`early90_left` as early90,d.`student_discount` as universityDiscount,t.`Nangang`,t.`Taipei`,"
				+ "t.`Banciao`,t.`Taoyuan`,t.`Hsinchu`,t.`Miaoli`,t.`Taichung`,t.`Changhua`,t.`Yunlin`,t.`Chiayi`,"
				+ "t.`Tainan`,t.`Zuoying` from dailyTrain as d left join timeTable as t on d.train_id = t.train_id "
				+ "where date = ? and t."+startStation+" < t."+endStation+" and t."+startStation+" > ? and t."+endStation+" > ? and d."+cartCode+" >=? ORDER BY t."+startStation+" ASC";
		
		
		try{
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, startTime);
			ps.setString(3, startTime);
			ps.setInt(4, ticketQty);
			rs = ps.executeQuery();
			while(rs.next()){
				//Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, 
				//double universityDiscount, String[] time)
				String[] timeString = new String[]{rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
						rs.getString(17), rs.getString(18)};
				t = new Train(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6), timeString);
				trains.add(t);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, ps, conn);
		}
		return trains.toArray(new Train[0]);	
	}

	@Override
	public void updateEarlyBird(Train t, double earlyBirdDiscount, int num) {
		String earlyCode ;
		String numberLeft = Integer.toString(num);	
		Date parseDate = null;
		try {
			parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(t.getDate());
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		String date = parseDate.toString();

		switch (Double.toString(earlyBirdDiscount)) { 
		case "0.65" :
			earlyCode = "early65_left=" + numberLeft;
			break; 
		case "0.8" : 
			earlyCode = "early80_left=" + numberLeft;
			break; 
		case "0.9": 
			earlyCode = "early90_left=" + numberLeft; 
			break;  
		default: 
			earlyCode = "";
		} 

		String sql = "update dailyTrain set "
				+ earlyCode
				+ " where train_id=? and date=?";

		try {
			runner.update(DBUtils.getConnection(), sql, t.getTid(), date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSeatLeft(Train t, int cartType, int num) {
		String cartCode ;

		String numberLeft = Integer.toString(num);

		Date parseDate = null;
		try {
			parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(t.getDate());
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		String date = parseDate.toString();

		switch (cartType) { 
		case 0 :
			cartCode = "std_left=" + numberLeft;
			break; 
		case 1 : 
			cartCode = "bus_left=" + numberLeft;
			break; 
		default: 
			cartCode = "";
		} 

		String sql = "update dailyTrain set "
				+ cartCode
				+ " where train_id=? and date=?";
		try {
			runner.update(DBUtils.getConnection(), sql, t.getTid(), date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}