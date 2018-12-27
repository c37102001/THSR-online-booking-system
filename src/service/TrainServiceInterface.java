package service;

import data.Train;

public interface TrainServiceInterface {
	
	public void initCartList(Train train);
	
	public double getEarlyBirdDiscount(Train train, int number);
	
	public int getStdSeatNumber(Train train);
	
	public int getBusSeatNumber(Train train);
	
	public void setUnavailableSeat(Train train, String seatNum);
	
	public void setTimeTable(Train train, String[] time);
	
	public String getTimeTable(Train train, String station);
	
	public String bookSeat(Train train, int seatType, int seatPrefer);
	
}
