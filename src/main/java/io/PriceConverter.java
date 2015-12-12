package io;

import com.thoughtworks.xstream.converters.SingleValueConverter;

import javafx.beans.property.SimpleDoubleProperty;

public class PriceConverter implements SingleValueConverter {

	private SimpleDoubleProperty doubleProp;

	public PriceConverter() {
		doubleProp = new SimpleDoubleProperty();
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(SimpleDoubleProperty.class);
	}

	public String toString(Object obj) {
		doubleProp = (SimpleDoubleProperty) obj;
		return doubleProp.getValue().toString();
	}

	public Object fromString(String str) {
		doubleProp.setValue(Double.parseDouble(str));
		return doubleProp;
	}
}
