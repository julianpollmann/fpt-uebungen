package model;

import exceptions.IdOverflowException;

public class IDGenerator {

	public static long generateID(ProductList pList) throws IdOverflowException {
		for (int i=1; i<1000000; i++) {
			if (pList.findProductById(i) == null) {
				System.out.print(" " +i);
				return i;
			}
		}
		throw new IdOverflowException("Die ID liegt nicht im festgelegten Bereich!");
	}
}