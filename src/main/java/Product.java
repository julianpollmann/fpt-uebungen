import javafx.beans.value.ObservableValue;

public class Product implements fpt.com.Product {

	String Name = null;
	int pId;
	int pPrice;
	int pQuantity;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrice(double price) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQuantity(int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public ObservableValue<String> nameProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
