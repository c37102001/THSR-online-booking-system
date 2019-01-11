package dbconnector;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import data.Train;

public interface TrainDao {
    // 添加方法
//    public void add(Train t) throws SQLException;

    // 更新方法
//    public void update(Train t) throws SQLException;

    // 删除方法
//    public void delete(int id) throws SQLException;

    // 查找方法
    public Train findByTrain(int trainId, SimpleDateFormat date) throws SQLException;
    
    // 查找方法
    public List<Train> searchTrain(String date, String startStation, String endStation, String startTime, int cartType, int ticketQty) throws SQLException;

    // 更新車班剩餘早鳥
    public void updateEarlyBird(Train t, double earlyBirdDiscount, int num) throws SQLException;
    
	 // 更新車班剩餘票
	public void updateSeatLeft(Train t, int cartType, int num) throws SQLException;

}