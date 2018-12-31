package control;

import data.Order;
import data.Ticket;
import data.Train;
import dbconnector.QueryInterface;
import discount.Children;
import discount.Discount;
import discount.EarlyBird;
import discount.Elderly;
import discount.NeedLove;
import service.OrderServiceInterface;
import service.TrainServiceInterface;

public class BookTicketController {
	
	public QueryInterface query;
	public TrainServiceInterface trainService;
	public OrderServiceInterface orderService;
	
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
			query.addTicket(order.getOrderNumber(), uid, ticket);
			orderService.addTicket(order, ticket);
		}
		if(discount instanceof EarlyBird) 
			query.updateEarlyBird(train, discount.getDiscount(), num);
		query.updateSeatLeft(train, cartType, num);
	}
	
	private void setupTrainCart(Train train) {
		trainService.initCartList(train);
		String[] unavailableSeatList = query.getUnavailableSeatList(train);  // {"0104E", "0312A", "0601B" , ... } 
		for(String seatNum : unavailableSeatList)
			trainService.setUnavailableSeat(train, seatNum);
	}
	
	
	/*
	public static void main(String[] args) {

		String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
		Train trainA = new Train("1072", "2018/12/25", 5, 10, 15, new Student85(), trainATimetable);
		
    	
		String uid = "c37102001";
		String startStation = "еxе_";
		String endStation = "оч╢щ";
		int cartType = Ticket.CartStandard;
		int seatPrefer = Ticket.SeatAisle;
		Discount discount = new EarlyBird80();
		int ticketQty = 3;
		
		
		BookTicketController bookingHelper = new BookTicketController(new TrainService(), new OrderService());
		Order myorder = bookingHelper.bookTicket(trainA, uid, startStation, endStation, cartType, seatPrefer, discount, ticketQty);
		
		for(Ticket ticket : myorder.getTicketList()) {
			System.out.println("Number: " + ticket.getTicketNumber());
			System.out.println("Train ID: " + ticket.getTid());
			System.out.println("Date: " + ticket.getDate());
			System.out.println("Start from " + ticket.getStart() + " at " + ticket.getStime());
			System.out.println("Stop at " + ticket.getEnd() + " at " + ticket.getEtime());
			System.out.println("Seat: " + ticket.getSeatNum());
			System.out.println("Discount Type: " + ticket.getDiscountType().getName());
			System.out.println("Price: " + ticket.getPrice());
			System.out.println();
		}
		
		TrainService ts = new TrainService();
		System.out.println(ts.getStdSeatNumber(trainA));
	}
	*/
}
