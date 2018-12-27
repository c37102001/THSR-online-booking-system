package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Train{
	

	private String tid; 
	private String date;
	private int earlyBird65;
	private int earlyBird80;
	private int earlyBird90;
	private double universityDiscount;
	private ArrayList<Cart> cartList;
	private HashMap<String, String> timeTable;
	public static final int BusCartNum = 6;
	public static final int TotalCartNum = 9;
	
	
	public Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, double universityDiscount){
		this.tid = tid;
		this.date = date;
		this.earlyBird65 = earlyBird65;
		this.earlyBird80 = earlyBird80;
		this.earlyBird90 = earlyBird90;
		this.universityDiscount = universityDiscount;
		cartList = new ArrayList<Cart>();
		timeTable = new HashMap<String, String>();
	}

	public String getTid() {
		return tid;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getEarlyBird65() {
		return earlyBird65;
	}
	
	public int getEarlyBird80() {
		return earlyBird80;
	}
	
	public int getEarlyBird90() {
		return earlyBird90;
	}

	public double getUniversityDiscount() {
		return universityDiscount;
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	public HashMap<String, String> getTimeTable() {
		return timeTable;
	}
	
}
