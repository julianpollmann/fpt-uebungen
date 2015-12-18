package io.db;

import java.sql.*;

import fpt.com.ProductList;
import model.Product;

public class JDBCConnector {

	private Connection con;
	private DatabaseMetaData dmd;
	private ResultSet rs;
	private PreparedStatement prstmt;
	private int maxResults;
	private Product product;
	private ProductList productlist;
	private long id;
	private String name;
	private double price;
	private int quantity;

	public JDBCConnector() {

		maxResults = 10;

		// Datenbanktreiber laden
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connect();
	}

	/*
	 * Verbindet mit DB + führt Queries aus
	 */
	private void connect() {
		// Verbindung aufbauen
		try(Connection con = DriverManager.getConnection(
				"jdbc:postgresql://java.is.uni-due.de/ws1011",
				"ws1011",
				"ftpw10"
		)) {
			// Queries ausführen
			getStatusInformation(con);
			read(20);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Status/Metainformationen zur DB anzeigen
	 */
	private static void getStatusInformation(Connection con) throws SQLException {

		DatabaseMetaData dmd = con.getMetaData();
		ResultSet rs = dmd.getTables(null, null, "%", null);

		System.out.println("DB Metadata ++++++++++++++++++++++++");
		System.out.println("URL: " + dmd.getURL());
		System.out.println("Username: " + dmd.getUserName());
		System.out.print("Tabellen: ");
		while(rs.next()) {
			System.out.print(rs.getString(3) + ", ");
		};
		System.out.println("++++++++++++++++++++++++++++++++++++");
	}

	private long insert(String name, double price, int quantity) {

		try (PreparedStatement prstmt = con.prepareStatement(
				"INSERT INTO products (product_name, product_price, product_quantity) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
		)) {
			prstmt.setString(0, name);
			prstmt.setDouble(1, price);
			prstmt.setInt(2, quantity);
			prstmt.executeUpdate();
			try(ResultSet rs = prstmt.getGeneratedKeys()) {
				if(rs.next()) {
					return id = rs.getLong("product_id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	private void insert(Product product) {
		product.setId(insert(product.getName(), product.getPrice(), product.getQuantity()));
	}

	private Product read(long productId) {

		product = new Product();

		try (PreparedStatement prstmt = con.prepareStatement(
				"SELECT id,name,price,quantity FROM products WHERE id=?"
		)) {
			prstmt.setLong(0, productId);
			prstmt.setMaxRows(maxResults);
			System.out.println(prstmt);
			try (ResultSet rs = prstmt.executeQuery()) {
				if(rs.next()) {
					System.out.println(rs.getString(2));

					product.setId(rs.getLong(0));
					product.setName(rs.getString(1));
					product.setPrice(rs.getDouble(2));
					product.setQuantity(rs.getInt(3));

					return product;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
