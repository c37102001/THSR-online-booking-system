package data;
import java.util.ArrayList;

public class Cart {
	
	private static final int SeatRowTotal  = 15;
	private static final char[] SeatColumnTotal = {'A', 'B', 'C', 'D', 'E'}; 
    private int cartNumber;
    private ArrayList<Seat> seatList;
	
    
	public Cart(int cartNumber) {
    	this.cartNumber = cartNumber;
    	seatList = new ArrayList<Seat>();
    	initCartSeats();
    }
    
	
    private void initCartSeats() {
    	for(int row=1; row<=SeatRowTotal; row++) {
    		for(int col=0; col<SeatColumnTotal.length; col++) {
    			seatList.add(new Seat(cartNumber, row, SeatColumnTotal[col], true));
    		}
    	}
    }
    
    
    public int getCartNumber() {
    	return cartNumber;
    }
    

    public void setUnavailableSeat(String seatNum) {
    	for(Seat seat : seatList) {
    		if(seat.getSeatNumber().equals(seatNum)) seat.setAvailableStatus(false); ;
    	}
    }
    
    
    public int getTotalSeatNum() {
    	int totalNum = 0;
    	for(Seat seat : seatList) {
    		if(seat.getAvailableStatus() == true && seat.getAvailableStatus() == true) totalNum++ ;
    	}
    	return totalNum;
    }
    
    
    public int getWindowSeatNum() {
    	int windowNum = 0;
    	for(Seat seat : seatList) {
    		if(seat.getSeatType() == Ticket.SeatWindow && seat.getAvailableStatus() == true) windowNum++ ;
    	}
    	return windowNum;
    }
    
    
    public int getAisleSeatNum() {
    	int aisleNum = 0;
    	for(Seat seat : seatList) {
    		if(seat.getSeatType() == Ticket.SeatAisle && seat.getAvailableStatus() == true) aisleNum++ ;
    	}
    	return aisleNum;
    }
    
    //book seat without preference
    public String bookSeat() {
    	for(Seat seat : seatList) {
    		if(seat.getAvailableStatus() == true) {
    			seat.setAvailableStatus(false);
    			return  seat.getSeatNumber();
    		}
    	}
    	return "";
    }
    
  //book seat with preference
    public String bookSeat(int seatPrefer) {
    	for(Seat seat : seatList) {
    		if(seat.getSeatType() == seatPrefer && seat.getAvailableStatus() == true) {
    			seat.setAvailableStatus(false);
    			return seat.getSeatNumber();
    		}
    	}
    	return "";
    }
    
    
    public static void main(String[] args) {
    	Cart cart = new Cart(1);
    	System.out.println("Total left: " + cart.getTotalSeatNum());
    	System.out.println("Window left: " + cart.getWindowSeatNum());
    	System.out.println("Aisle left: " + cart.getAisleSeatNum());
    	
    	System.out.println("Book seat " + cart.bookSeat(Ticket.SeatWindow));
    	System.out.println("Book seat " + cart.bookSeat(Ticket.SeatWindow));
    	System.out.println("Book seat " + cart.bookSeat(Ticket.SeatAisle));
    	System.out.println("Book seat " + cart.bookSeat());
    	System.out.println("Book seat " + cart.bookSeat());
    	System.out.println("Book seat " + cart.bookSeat());
    	
    	System.out.println("Total left: " + cart.getTotalSeatNum());
    	System.out.println("Window left: " + cart.getWindowSeatNum());
    	System.out.println("Aisle left: " + cart.getAisleSeatNum());
    }
}
