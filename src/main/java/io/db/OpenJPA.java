package io.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.OpenJPAPersistence;

import fpt.com.SerializableStrategy;
import model.Product;
import model.ProductList;

public class OpenJPA implements SerializableStrategy{

	private int maxResults = 10;
	private EntityManagerFactory factory;
	private EntityManager manager;

	public OpenJPA() {
		this.factory = getWithoutConfig();
		this.manager = factory.createEntityManager();
	}

	//Aus dem Beispiel der VL...
	public EntityManagerFactory getWithoutConfig() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("openjpa.ConnectionURL",
				"jdbc:postgresql://java.is.uni-due.de/ws1011");
		map.put("openjpa.ConnectionDriverName", "org.postgresql.Driver");
		map.put("openjpa.ConnectionUserName", "ws1011");
		map.put("openjpa.ConnectionPassword", "ftpw10");
		map.put("openjpa.RuntimeUnenhancedClasses", "supported");
		map.put("openjpa.jdbc.SynchronizeMappings", "false");

		// find all classes to registrate them
		List<Class<?>> types = new ArrayList<Class<?>>();
		types.add(Product.class);

		if (!types.isEmpty()) {
			StringBuffer buf = new StringBuffer();
			for (Class<?> c : types) {
				if (buf.length() > 0)
					buf.append(";");
				buf.append(c.getName());
			}
			// <class>Product</class>
			map.put("openjpa.MetaDataFactory", "jpa(Types=" + buf.toString() + ")");
		}
		return OpenJPAPersistence.getEntityManagerFactory(map);
	}

	public ProductList read() {
		String statement = "SELECT id,name,price,quantity FROM products order by id desc limit " + maxResults;
		ProductList results = (ProductList) manager.createQuery(statement).getResultList();
		if (!results.isEmpty())
			return results;
		else
			return null;
	}

	private long insert(String name, double price, int quantity) {
		Product product = new Product((long)0, name, price, quantity);

        open().manager.getTransaction().begin();
        open().manager.persist(product);
        open().manager.getTransaction().commit();

       // this.close();

        return  product.getId();
	}

	public void insert(Product product) {
		product.setId(insert(product.getName(), product.getPrice(), product.getQuantity()));

		EntityTransaction trans = open().manager.getTransaction();
		trans.begin();
		open().manager.persist(product);
		trans.commit();
	}

	@Override //Von SerializableStrategy geerbt, aber ist hier äquivalent zu insert.
	public void writeObject(fpt.com.Product product) throws IOException {
		product.setId(insert(product.getName(), product.getPrice(), product.getQuantity()));

		EntityTransaction trans = this.manager.getTransaction();
		trans.begin();
		manager.persist(product);
		trans.commit();
	}


	public void close() {
		if (manager != null)   //Manager schliessen
			{manager.close();}
		if (factory != null)   //Factory schliessen
			{factory.close();}
	}

	@Override
	public fpt.com.Product readObject() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

	}

	public OpenJPA open() {
		OpenJPA ojpa = new OpenJPA();
		return ojpa;
	}
}
