package dbconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import data.Station;
import data.Ticket;
import data.Train;
import dbconnector.config.DBUtils;

public class TrainDaoImpl extends QueryAdapter {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public Train[] searchTrain(String date, String startStation, String endStation, String startTime, int cartType,
			int ticketQty) {
		date = date.replace('/', '-');
		startStation = Station.getEngName(startStation);
		endStation = Station.getEngName(endStation);
		String cartCode = (cartType == Ticket.CartStandard) ? "std_left" : "bus_left";

		
		Train t = null;
		List<Train> trains = new ArrayList<Train>();
		String sql = "select d.`train_id` as tid,d.`date`,d.`early65_left` as early65,d.`early80_left` as early80,"
				+ "d.`early90_left` as early90,d.`student_discount` as universityDiscount,t.`Nangang`,t.`Taipei`,"
				+ "t.`Banciao`,t.`Taoyuan`,t.`Hsinchu`,t.`Miaoli`,t.`Taichung`,t.`Changhua`,t.`Yunlin`,t.`Chiayi`,"
				+ "t.`Tainan`,t.`Zuoying` from dailyTrain as d left join timeTable as t on d.train_id = t.train_id "
				+ "where date = ? and t." + startStation + " < t." + endStation + " and t." + startStation
				+ " > ? and t." + endStation + " > ? and d." + cartCode + " >=? ORDER BY t." + startStation + " ASC";

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, startTime);
			ps.setString(3, startTime);
			ps.setInt(4, ticketQty);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Train(String tid, String date, int earlyBird65, int earlyBird80, int
				// earlyBird90,
				// double universityDiscount, String[] time)
				String[] timeString = new String[] { rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18) };
				t = new Train(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getDouble(6), timeString);
				trains.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return trains.toArray(new Train[0]);
	}

	public String[] getUnavailableSeatList(Train train) {
		List<String> bookedSeats = new ArrayList<String>();

		String sql = "SELECT tickets.`seat` FROM tickets WHERE train_id = ? AND date = ?";

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, train.getTid());
			ps.setString(2, train.getDate().replace('/', '-'));
			rs = ps.executeQuery();
			while (rs.next()) {
				bookedSeats.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}

		return bookedSeats.toArray(new String[0]);
	}

	public void addTicket(String orderNumber, String uid, Ticket ticket) {

		String sql = "INSERT INTO `thsr`.`tickets` (`tid`, `uid`, `code`, `train_id`, `date`, `start`, `end`,"
				+ " `start_time`, `end_time`, `type`, `seat`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ticket.getTicketNumber());
			ps.setString(2, uid);
			ps.setString(3, orderNumber);
			ps.setString(4, ticket.getTid());
			ps.setString(5, ticket.getDate().replace('/', '-'));
			ps.setString(6, ticket.getStart());
			ps.setString(7, ticket.getEnd());
			ps.setString(8, ticket.getStime());
			ps.setString(9, ticket.getEtime());
			ps.setString(10, ticket.getDiscountType().getName());
			ps.setString(11, ticket.getSeatNum());
			ps.setInt(12, ticket.getPrice());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
	}

	@Override
	public void updateEarlyBird(Train train, double earlyBirdDiscount, int num) {
		String earlyCode="";

		switch (Double.toString(earlyBirdDiscount)) {
		case "0.65":
			earlyCode = "early65_left = early65_left - " + num;
			break;
		case "0.8":
			earlyCode = "early80_left = early80_left - " + num;
			break;
		case "0.9":
			earlyCode = "early90_left = early90_left - " + num;
			break;
		}

		String sql = "update dailyTrain set " + earlyCode + " where train_id=? and date=?";
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, train.getTid());
			ps.setString(2, train.getDate().replace('/', '-'));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
	}

	@Override
	public void updateSeatLeft(Train train, int cartType, int num) {
		
		String seatType = (cartType == Ticket.CartStandard) ? ("std_left = std_left - " + num) : ("bus_left = bus_left - " + num);
		
		String sql = "update dailyTrain set " + seatType + " where train_id=? and date=?";
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, train.getTid());
			ps.setString(2, train.getDate().replace('/', '-'));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
	}
}