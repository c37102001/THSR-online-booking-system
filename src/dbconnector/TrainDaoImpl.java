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
import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;

import data.Train;
import dbconnector.config.DBUtils;
import dbconnector.TrainDao;

public class TrainDaoImpl implements TrainDao {
    private QueryRunner runner = null;
    public TrainDaoImpl(){
        runner = new QueryRunner();
    }
    //要join 兩張表才能查到要查的東西 filter那邊
//    Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, 
//    		double universityDiscount, String[] time)
    
    //方法：添加 應該是不會用到 除了一開始的讀json檔 >>>>可直接parse看看
//    @Override
//    public void add(Train t) throws SQLException {
//        String sql = "insert into dailyTrain(name,age,description)values(?,?,?)";
//        runner.update(DBUtils.getConnection(), sql, t.getName(), t.getAge(),t.getDescription());
//    }

    //方法：修該車班的剩餘座位們
//    @Override
//    public void update(Train t) throws SQLException {
//        String sql = "update dailyTrain set std_left=?,std_aisle_left=?,std_window_left=?,"
//        				+ "bus_left=?,bus_aisle_left=?,bus_window_left=?,"
//        				+ "early65_left=?, early80_left=?,early90_left=? where id=?";
//        runner.update(DBUtils.getConnection(), sql, 
//        		t.stdSeat(),t.stdAisle,t.stdwindow,t.busSeat,t.busAisle,t.busWindow,t.early65(),t.early80(),t.early90,
//        		t.getId());
//    }

//    //方法：根据id删除数据库中的某条记录 應該不會用到
//    @Override
//    public void delete(int id) throws SQLException {
////        String sql = "delete from dailyTrain where id=?";
////        runner.update(DBUtils.getConnection(), sql, id);
//    }
    
    
     //方法：使用BeanHandler查询一个对象 這裡還要改 詳細地把座位們都生出來的計畫？ 也有可能在別的table    
    @Override
    public Train findByTrain(int trainId, SimpleDateFormat date) throws SQLException {
        String sql = "select train_id as trainID,date as departureDate,std_left as stdSeat,"
        		+ "bus_left as busSeat,early65_left as early65,early80_left as early80,early90_left as early90,"
        		+ "student_discount as discount from dailyTrain where train_id=? and date=?";
        Train t = runner.query(DBUtils.getConnection(), sql, new BeanHandler<Train>(Train.class), trainId, date);
        return t;
    }
  //方法：使用一般方法查询多個 (String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, double universityDiscount, String[] time)
    @Override
    public List<Train> searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty) throws SQLException {
        //把參數轉換成我需要的格式
	    	Date parseDate = null;
			try {
				parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		date = parseDate.toString();
		
		String cartCode;
		
		if (cartType == 0) {
			cartCode = "std_left";
		} else {
			cartCode = "bus_left";
		}
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Train t = null;
        List<Train> trains = new ArrayList<Train>();
        String sql = "select d.`train_id` as tid,d.`date`,d.`early65_lef`t as early65,d.`early80_left` as early80,"
        		+ "d.`early90_left` as early90,d.`student_discount` as universityDiscount,t.`Nangang`,t.`Taipei`,"
        		+ "t.`Banciao`,t.`Taoyuan`,t.`Hsinchu`,t.`Miaoli`,t.`Taichung`,t.`Changhua`,t.`Yunlin`,t.`Chiayi`,"
        		+ "t.`Tainan`,t.`Zuoying` from dailyTrain as d left join timeTable as t on d.train_id = t.train_id "
        		+ "where date = ? and t."+startStation+" > t."+endStation+" and t."+startStation+" > ? and t."+endStation+" > ? and d."+cartCode+" >=? ORDER BY t."+startStation+" ASC";
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
            throw new SQLException("search failed");
        }finally{
            DBUtils.close(rs, ps, conn);
        }
        return trains;	
    		
    		
    }

    //方法：更新
    @Override
    public void updateEarlyBird(Train t, double earlyBirdDiscount, int num) throws SQLException {
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
        
        runner.update(DBUtils.getConnection(), sql, t.getTid(), date);
    }
    
    //方法：更新
    @Override
    public void updateSeatLeft(Train t, int cartType, int num) throws SQLException {
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
	    runner.update(DBUtils.getConnection(), sql, t.getTid(), date);
    }

}