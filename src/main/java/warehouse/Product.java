package warehouse;

public class Product {

	private String prodName;
	private double prodPrice;
	private int prodQuantity;

	public Product(String prodName, double prodPrice, int prodQuantity) {
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodQuantity = prodQuantity;
	}

	public String getName() {
		return this.prodName;
	}

	public double getPrice() {
		return this.prodPrice;
	}

	public int getQuantity() {
		return this.prodQuantity;
	}
}
