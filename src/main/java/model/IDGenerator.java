package model;

import java.util.ArrayList;
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
//
//public class IDGenerator {
//
//	private int id;
//	private int maxId;
//	private ArrayList<Integer> idList;
//
//	public IDGenerator() {
//		id = 0;
//		maxId = 999999;
//		idList = new ArrayList<Integer>();
//	}
//
//	/*
//	 * Generiert neue ID
//	 * Beginnt bei 1 und erhöht ID jeweils um 1
//	 * Prüft bei jeder ID, ob diese bereits in idLIst existiert
//	 * Falls ID außerhalb des Bereichs 0-maxId ist,
//	 * werfe IdOverFlowException
//	 */
//	public int generateId() throws IdOverflowException {
//		try {
//			while(checkIdExist(id) || id == 0) {
//				checkIdRange(id);
//				id++;
//			}
//			idList.add(id);
//		} catch (IdOverflowException e) {
//			System.out.println("Die ID ist außerhalb!");
//		}
//		return id;
//	}
//
//	/*
//	 * Prüft, ob ID bereits in IdList vorhanden ist
//	 */
//	private boolean checkIdExist(Integer id) {
//		if(idList.contains(id)) {
//			return true;
//		}
//		return false;
//	}
//
//	/*
//	 * Prüft, oberzeugte ID in vorgegebener Range (0-maxId) liegt
//	 */
//	private void checkIdRange(int index) {
//		if (idList.size() < 0 || idList.size() > maxId) {
//			throw new IdOverflowException();
//		}
//>>>>>>> origin/master
