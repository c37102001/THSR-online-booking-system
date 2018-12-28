package data;

import java.util.ArrayList;
import java.util.HashMap;

import discount.Discount;

public class Train{
	

	private String tid; 
	private String date;
	private int earlyBird65;
	private int earlyBird80;
	private int earlyBird90;
	private Discount universityDiscount;
	private ArrayList<Cart> cartList;
	private HashMap<String, String> timeTable;
	public static final int BusCartNum = 6;
	public static final int TotalCartNum = 9;
	
	
	public Train(String tid, String date, int earlyBird65, int earlyBird80, int earlyBird90, Discount universityDiscount){
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
	
	public void setEarlyBird65(int newEarlyBird65) {
		this.earlyBird65 = newEarlyBird65;
	}
	
	public int getEarlyBird80() {
		return earlyBird80;
	}
	
	public void setEarlyBird80(int newEarlyBird80) {
		this.earlyBird80 = newEarlyBird80;
	}
	
	public int getEarlyBird90() {
		return earlyBird90;
	}
	
	public void setEarlyBird90(int newEarlyBird90) {
		this.earlyBird90 = newEarlyBird90;
	}

	public Discount getUniversityDiscount() {
		return universityDiscount;
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	public HashMap<String, String> getTimeTable() {
		return timeTable;
	}
	
}
