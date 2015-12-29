package io;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.thoughtworks.xstream.converters.SingleValueConverter;

/*
 * Prüft, ob (de)serialisiertes Objekt konvertierbar ist
 * Konvertiert Long Id <-> String Id
 * Fügt führende 0 bis 6-Stellen hinzu
 */
public class IdConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.equals(Long.class);
	}

	@Override
	public String toString(Object obj) {
		NumberFormat formatter = new DecimalFormat("000000");
		return formatter.format(obj);
		//id = ("000000" + obj.toString()).substring(obj.toString().length()) + ".00";
		//return id;
	}

	@Override
	public Object fromString(String str) {
		while(str.startsWith("0")) {
			str.substring(1);
		}
		//Vorgaengerversion: id = str.replaceFirst ("^0*", "");
		return Long.parseLong(str);
	}

}
