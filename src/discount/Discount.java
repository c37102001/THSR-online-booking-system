package discount;

public abstract class Discount implements DiscountInterface{
	public String name;
	public double discount;
	

	public String getName() {
		return this.name;
	}

	public double getDiscount() {
		return this.discount;
	}
}
