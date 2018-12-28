package service;

import data.Train;
import discount.Discount;

public interface TrainServiceInterface {
	
	public void initCartList(Train train);
	
	public Discount getEarlyBirdDiscount(Train train, int number);
	
	public void updateEarlyBirdDiscount(Train train, Discount discount);
	
	public int getStdSeatNumber(Train train);
	
	public int getBusSeatNumber(Train train);
	
	public void setUnavailableSeat(Train train, String seatNum);
	
	public String getStationTime(Train train, String station);
	
	public String bookSeat(Train train, int cartType, int seatPrefer);
	
}
