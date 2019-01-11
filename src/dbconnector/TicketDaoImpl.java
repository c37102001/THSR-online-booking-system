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

import data.Ticket;
import dbconnector.config.DBUtils;
import dbconnector.TicketDao;

public class TicketDaoImpl implements TicketDao {
    private QueryRunner runner = null;
    public TicketDaoImpl(){
        runner = new QueryRunner();
    }
	
	public void add(Ticket t) throws SQLException {
		
	}
	
	public void delete(Ticket t) throws SQLException {
		
	}
	
    // 查找方法
//    public Train findByCode(int trainId, SimpleDateFormat date) throws SQLException {}
    
    // 查找方法
    public List<Ticket> getOrderTicket(String uid, String orderNumber) throws SQLException {
    		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket t = null;
        List<Ticket> tickets = new ArrayList<Ticket>();
        String sql = "";
//        try{
//            conn = DBUtils.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, date);
//            ps.setString(2, startTime);
//            ps.setString(3, startTime);
//            ps.setInt(4, ticketQty);
//            rs = ps.executeQuery();
//            while(rs.next()){
//            		//Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, 
//            		//double universityDiscount, String[] time)
//            		String[] timeString = new String[]{rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
//            										rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
//            										rs.getString(17), rs.getString(18)};
//                t = new Train(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6), timeString);
//                trains.add(t);
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//            throw new SQLException("search failed");
//        }finally{
//            DBUtils.close(rs, ps, conn);
//        }
        return tickets;
    }

}
