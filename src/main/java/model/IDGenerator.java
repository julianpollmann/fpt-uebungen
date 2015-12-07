package model;

public class IDGenerator {

	private static long id;

	public static long generateID(ProductList pList){
		id = (long) (Math.random()*1000000);
		while (pList.findProductById(id) != null) {
			id = (long) (Math.random()*1000000);
		}
		return id;
	}

}
