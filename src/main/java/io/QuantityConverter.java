package io;

import com.thoughtworks.xstream.converters.SingleValueConverter;

import javafx.beans.property.SimpleIntegerProperty;

/*
 * Prüft, ob (de)serialisiertes Objekt konvertierbar ist
 * Konvertiert SimpleIntegerProperty price <-> Int price
 *
 */
public class QuantityConverter implements SingleValueConverter {

	private SimpleIntegerProperty intProp;

	public QuantityConverter() {
		intProp = new SimpleIntegerProperty();
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(SimpleIntegerProperty.class);
	}

	@Override
	public String toString(Object obj) {
		intProp = (SimpleIntegerProperty) obj;
		return intProp.getValue().toString();
	}

	@Override
	public Object fromString(String str) {
		intProp.setValue(Integer.parseInt(str));
		return intProp;
	}

}
