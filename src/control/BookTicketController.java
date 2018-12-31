package control;

import data.Order;
import data.Ticket;
import data.Train;
import dbconnector.QueryInterface;
import discount.Children;
import discount.Discount;
import discount.Elderly;
import discount.NeedLove;
import service.OrderService;
import service.OrderServiceInterface;
import service.TrainService;
import service.TrainServiceInterface;

public class BookTicketController {
	
	public QueryInterface query;
	public TrainServiceInterface trainService;
	public OrderServiceInterface orderService;
	
	
	public BookTicketController(TrainServiceInterface trainService, OrderServiceInterface orderService) {
		this.trainService = trainService;
		this.orderService = orderService;
	}
	
	public BookTicketController(QueryInterface query, TrainServiceInterface trainService, OrderServiceInterface orderService) {
		this.query = query;
		this.trainService = trainService;
		this.orderService = orderService;
	}
	
	public Order bookTicket(Train train, String uid, String start, String end, int cartType, int seatPrefer, int[] ticketTypes) {
		setupTrainCart(train);
		Order order = new Order(uid);
		
		int standardT = ticketTypes[0];
		int childrenT = ticketTypes[1];
		int elderlyT = ticketTypes[2];
		int needLoveT = ticketTypes[3];
		int studentT = ticketTypes[4];
		
		if(standardT != 0)
			makeSingleBooking(train, order, uid, start, end, cartType, seatPrefer, trainService.checkEarlyBird(train, standardT), standardT);
		if(childrenT != 0)
			makeSingleBooking(train, order, uid, start, end, cartType, seatPrefer, new Children(), childrenT);
		if(elderlyT != 0)
			makeSingleBooking(train, order, uid, start, end, cartType, seatPrefer, new Elderly(), elderlyT);
		if(needLoveT != 0)
			makeSingleBooking(train, order, uid, start, end, cartType, seatPrefer, new NeedLove(), needLoveT);
		if(studentT != 0)
			makeSingleBooking(train, order, uid, start, end, cartType, seatPrefer, train.getUniversityDiscount(), studentT);
		
		return order;
	}
	
	private void makeSingleBooking(Train train, Order order, String uid, String start, String end, int cartType, int seatPrefer, Discount discount, int num){
		
		for(int i=0; i<num; i++) {
			String seatNumber = trainService.bookSeat(train, cartType, seatPrefer);
			Ticket ticket = new Ticket(train, start, end, cartType, seatNumber, discount);
//			query.addTicket(order.getOrderNumber(), uid, ticket);
			orderService.addTicket(order, ticket);
		}
//		if(discount instanceof EarlyBird) 
//			query.updateEarlyBird(train, discount.getDiscount(), num);
//		query.updateSeatLeft(train, cartType, num);
	}
	
	private void setupTrainCart(Train train) {
		trainService.initCartList(train);
//		String[] unavailableSeatList = query.getUnavailableSeatList(train);  // {"0104E", "0312A", "0601B" , ... } 
//		for(String seatNum : unavailableSeatList)
//			trainService.setUnavailableSeat(train, seatNum);
	}
	
	
	
	public static void main(String[] args) {

		String[] trainATimetable = {"1400", "1413", "1421", "1454", "1527", "1535", "1550", "1612", "1627", "1643", "1702", "1720"};
		Train trainA = new Train("928", "2018/12/25", 0, 0, 13, 1, trainATimetable);
    	
		String uid = "c37102001";
		String startStation = "台北";
		String endStation = "桃園";
		int cartType = Ticket.CartStandard;
		int seatPrefer = Ticket.SeatAisle;
		int[] ticketTypes = {2, 1, 1, 1, 2};
		
		
		BookTicketController bookingHelper = new BookTicketController(new TrainService(), new OrderService());
		Order myorder = bookingHelper.bookTicket(trainA, uid, startStation, endStation, cartType, seatPrefer, ticketTypes);
		
		for(Ticket ticket : myorder.getTicketList()) {
			System.out.println("車票代號: " + ticket.getTicketNumber());
			System.out.println("車次: " + ticket.getTid());
			System.out.println("日期: " + ticket.getDate());
			System.out.println("起站: " + ticket.getStart() + "(" + ticket.getStime() + ")");
			System.out.println("迄站: " + ticket.getEnd() + "(" + ticket.getEtime()+ ")");
			System.out.println("座位號碼: " + ticket.getSeatNum());
			System.out.println("票種: " + ticket.getDiscountType().getName());
			System.out.println("價格: " + ticket.getPrice());
			System.out.println();
		}
		System.out.println("訂單總額:" + myorder.getTotalPrice());
		
		TrainService ts = new TrainService();
		System.out.println("剩下標準車廂座位: " + ts.getStdSeatNumber(trainA));
	}
	
	
	
}
