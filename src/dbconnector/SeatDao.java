package dbconnector;

import java.sql.SQLException;
import java.util.List;

import data.Seat;

public interface SeatDao {
    // 添加方法
    public void add(Seat s) throws SQLException;

    // 更新方法
    public void update(Seat s) throws SQLException;

    // 删除方法
    public void delete(int id) throws SQLException;

    // 查找方法
    public Seat findById(int id) throws SQLException;
    
    // 查找方法
    public List<Seat> findByCart(int cart) throws SQLException;

    // 查找所有
    public List<Seat> findAll() throws SQLException;

    // 查询有几条记录
    public long seatCount() throws SQLException;
    
    // 查询有几条记录
    public long seatCountByCart(int cart) throws SQLException;

}