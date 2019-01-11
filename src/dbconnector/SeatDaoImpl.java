package dbconnector;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import data.Seat;
import dbconnector.config.DBUtils;


//Seat(char row, int column, boolean window, boolean aisle, boolean available)
public class SeatDaoImpl implements SeatDao {
    private QueryRunner runner = null;
    public SeatDaoImpl(){
        runner = new QueryRunner();
    }
	
	// 更新方法 應該不用
	public void add(Seat s) throws SQLException{
		
	}

    // 更新方法 應該不用
    public void update(Seat s) throws SQLException{
    	
    }

    // 删除方法 應該不用
    public void delete(int id) throws SQLException{
    	
    }

    // 查找方法
    public Seat findById(int id) throws SQLException{
        String sql = "select substring(seat, 3, 2) as row, substring(seat, -1) as column,business from allSeat where id=?";
        Seat s = runner.query(DBUtils.getConnection(), sql, new BeanHandler<Seat>(Seat.class),id);
        return s;
    }
    
    // 查找方法
    public List<Seat> findByCart(int cart) throws SQLException{
        String sql = "select substring(seat, 3, 2) as row, substring(seat, -1) as column,side from allSeat where seat like ?";
        List<Seat> seats = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Seat>(Seat.class), cart+'%');
        return seats;
    	
    }

    // 查找所有
    public List<Seat> findAll() throws SQLException{
        String sql = "select seat,side,business from allSeat";
        List<Seat> seats = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Seat>(Seat.class));
        return seats;
    	
    }

    // 查询有几条记录
    public long seatCount() throws SQLException{
        String sql = "select count(id) from allSeat";
        return runner.query(DBUtils.getConnection(),sql, new ScalarHandler<Long>());
    }
    
    // 查询有几条记录 byCart
    public long seatCountByCart(int cart) throws SQLException{
        String sql = "select count(id) from allSeat where seat like ?";
        return runner.query(DBUtils.getConnection(),sql, new ScalarHandler<Long>(), cart+'%');
    }

}
