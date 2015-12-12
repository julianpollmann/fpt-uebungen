package io;

import com.thoughtworks.xstream.converters.SingleValueConverter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class NameConverter implements SingleValueConverter {

	private SimpleStringProperty stringProp;

	public NameConverter () {
		stringProp = new SimpleStringProperty();
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(SimpleStringProperty.class);
	}

	@Override
	public String toString(Object obj) {
		stringProp = (SimpleStringProperty) obj;
		return stringProp.getValue().toString();
	}

	@Override
	public Object fromString(String str) {
		stringProp.setValue(str);
		return stringProp;
	}

}
