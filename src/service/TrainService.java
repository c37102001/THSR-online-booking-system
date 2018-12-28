package service;

import data.Cart;
import data.Station;
import data.Ticket;
import data.Train;
import discount.*;

public class TrainService implements TrainServiceInterface{
	
	@Override
	public void initCartList(Train train) {
		for(int cartNum=1; cartNum<=Train.TotalCartNum; cartNum++) {
			train.getCartList().add(new Cart(cartNum));
		}
	}
	
	
	public Discount getEarlyBirdDiscount(Train train, int number) {
		if(train.getEarlyBird65() > number) return new EarlyBird65();
		else if(train.getEarlyBird80() > number) return new EarlyBird80();
		else if(train.getEarlyBird90() > number) return new EarlyBird90();
		else return new Standard();
	}
	
	public void updateEarlyBirdDiscount(Train train, Discount discount) {
		if(discount instanceof EarlyBird65) 
			train.setEarlyBird65(train.getEarlyBird65()-1);
		if(discount instanceof EarlyBird80) 
			train.setEarlyBird80(train.getEarlyBird80()-1);
		if(discount instanceof EarlyBird90) 
			train.setEarlyBird90(train.getEarlyBird90()-1);
	}
	
	
	public int getStdSeatNumber(Train train) {
		int seatNumber = 0;
		for(Cart cart : train.getCartList()) {
			if(cart.getCartNumber() == Train.BusCartNum) 
				continue;
			seatNumber += cart.getTotalSeatNum();
		}
		return seatNumber;
	}
	
	
	public int getBusSeatNumber(Train train) {
		int seatNumber = 0;
		for(Cart cart : train.getCartList()) {
			if(cart.getCartNumber() == Train.BusCartNum) 
				seatNumber = cart.getTotalSeatNum();
		}
		return seatNumber;
	}
	
	
	public void setUnavailableSeat(Train train, String seatNum) {
		for(Cart cart : train.getCartList()) {
			if(cart.getCartNumber() != Integer.parseInt(seatNum.substring(0, 2))) 
				continue;
			cart.setUnavailableSeat(seatNum);
		}
	}
	

	// Update time table 
	public void setTimeTable(Train train, String[] time) {
		for(int i=0; i<Station.CHI_NAME.length; i++) {
			train.getTimeTable().put(Station.CHI_NAME[i], time[i]);
		}
	} 
	

	// Show time table
	public String getTimeTable(Train train, String station) {
		return train.getTimeTable().get(station);
	}


	public String bookSeat(Train train, int cartType, int seatPrefer) {
		
		if(cartType == Ticket.CartBusiness) {
			for(Cart cart : train.getCartList()) {
				if(cart.getCartNumber() != Train.BusCartNum) continue;
				if(seatPrefer == Ticket.SeatWindow && cart.getWindowSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else if(seatPrefer == Ticket.SeatAisle && cart.getAisleSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else 
					return cart.bookSeat();
			}
		}
		
		else {
			for(Cart cart : train.getCartList()) {
				if(cart.getCartNumber() == Train.BusCartNum) 
					continue;
				if(seatPrefer == Ticket.SeatWindow && cart.getWindowSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else if(seatPrefer == Ticket.SeatAisle && cart.getAisleSeatNum()>0) 
					return cart.bookSeat(seatPrefer);
				else 
					return cart.bookSeat();
			}
		}
		return "";
	}

	public static void main(String[] args) {
    	Train train = new Train("1072", "2018/12/25", 5, 10, 15, new Student85());
    	TrainService trainService = new TrainService();
    	System.out.println("Train ID: " + train.getTid());
    	System.out.println("Date: " + train.getDate());
    	
    	trainService.initCartList(train);
    	System.out.println("Std seat number: " + trainService.getStdSeatNumber(train));
    	System.out.println("Bus seat number: " + trainService.getBusSeatNumber(train));
    	
    	trainService.setUnavailableSeat(train, "0102A");
    	System.out.println("Std seat number: " + trainService.getStdSeatNumber(train));
    	System.out.println("Bus seat number: " + trainService.getBusSeatNumber(train));
    	
    	trainService.setUnavailableSeat(train, "0101C");
    	System.out.println("Std seat number: " + trainService.getStdSeatNumber(train));
    	System.out.println("Bus seat number: " + trainService.getBusSeatNumber(train));
    	
    	trainService.setUnavailableSeat(train, "0101D");
    	System.out.println("Std seat number: " + trainService.getStdSeatNumber(train));
    	System.out.println("Bus seat number: " + trainService.getBusSeatNumber(train));
    	
    	System.out.println(trainService.bookSeat(train, Ticket.CartStandard, Ticket.SeatWindow));
    	System.out.println(trainService.bookSeat(train, Ticket.CartStandard, Ticket.SeatAisle));
    	System.out.println(trainService.bookSeat(train, Ticket.CartBusiness, Ticket.SeatWindow));
    	System.out.println("Std seat number: " + trainService.getStdSeatNumber(train));
    	System.out.println("Bus seat number: " + trainService.getBusSeatNumber(train));
    	
    	for(int i=0; i<10; i++) {
    		System.out.println(trainService.bookSeat(train, Ticket.CartStandard, Ticket.SeatNoPrefer));
    	}
    	
    	String[] trainATimetable = {"0800", "0810", "0815", "0835", "0855", "0910", "0940", "1005", "1020", "1030", "1035", "1050"};
    	trainService.setTimeTable(train, trainATimetable);
    	System.out.println(trainService.getTimeTable(train, "оч╢щ"));
    }
	
}
