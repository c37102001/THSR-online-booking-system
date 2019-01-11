package dbconnector;

import java.sql.SQLException;
import java.util.List;

import data.Ticket;

public interface TicketDao {
	
	public void add(Ticket t) throws SQLException;
	
	public void delete(Ticket t) throws SQLException;
	
    // 查找方法
//    public Train findByCode(int trainId, SimpleDateFormat date) throws SQLException;
    
    // 查找方法
    public List<Ticket> getOrderTicket(String uid, String orderNumber) throws SQLException;


}
