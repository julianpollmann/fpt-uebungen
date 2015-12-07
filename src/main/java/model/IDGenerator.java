package model;

public class IDGenerator {

	public static long generateID(ProductList pList) throws Exception {
		for (int i=1; i<1000000; i++) {
			if (pList.findProductById(i) == null) {
				System.out.print(" " +i);
				return i;
			}
		}
		throw new Exception("ID Overflow");
	}

}
