package io.db;

import java.io.IOException;

import fpt.com.Product;
import model.ProductList;
import fpt.com.db.AbstractDatabaseStrategy;

public class JDBCStrategy extends AbstractDatabaseStrategy {

	@Override
	public Product readObject() throws IOException {
		JDBCConnector jdbc = new JDBCConnector();
		return null;
	}

	public ProductList readList() throws IOException {
		JDBCConnector jdbc = new JDBCConnector();
		return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void open() throws IOException {
		// TODO Auto-generated method stub

	}

}
