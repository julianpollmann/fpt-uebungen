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
	private model.ProductList productList;
	private long id;
	private String name;
	private double price;
	private int quantity;

/*
	public static void main(String[] args) throws SQLException {
		JDBCConnector jd = new JDBCConnector();
		jd.read();
	}
*/

	public JDBCConnector() {

		maxResults = 10;
		// Datenbanktreiber laden
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Status/Metainformationen zur DB anzeigen
	 */
	private static void getStatusInformation(Connection con) throws SQLException {

		DatabaseMetaData dmd = con.getMetaData();
		ResultSet rs = dmd.getTables(null, null, null, new String[]{"TABLE"});

		System.out.println("+++++++++++++ DB Metadata +++++++++++");
		System.out.println("URL: " + dmd.getURL());
		System.out.println("Username: " + dmd.getUserName());
		System.out.print("Tabellen: ");
		while(rs.next()) {
			System.out.println(rs.getString(3));
		};
		System.out.println("++++++++++++++++++++++++++++++++++++");
	}

	private long insert(String name, double price, int quantity) {
		try(Connection con = DriverManager.getConnection(
				"jdbc:postgresql://java.is.uni-due.de/ws1011",
				"ws1011",
				"ftpw10"
		); PreparedStatement prstmt = con.prepareStatement(
				"INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
		)) {
			prstmt.setString(1, name);
			prstmt.setDouble(2, price);
			prstmt.setInt(3, quantity);
			prstmt.executeUpdate();
			try(ResultSet rs = prstmt.getGeneratedKeys()) {
				if(rs.next()) {
					return id = rs.getLong("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void insert(Product product) {
		product.setId(insert(product.getName(), product.getPrice(), product.getQuantity()));
	}

	public Product read(long productId) {
		try(Connection con = DriverManager.getConnection(
				"jdbc:postgresql://java.is.uni-due.de/ws1011",
				"ws1011",
				"ftpw10"
		); PreparedStatement prstmt = con.prepareStatement(
				"SELECT id,name,price,quantity FROM products WHERE id=?"
		)) {

			product = new Product();
			prstmt.setLong(1, productId);
			prstmt.setMaxRows(maxResults);
			System.out.println(prstmt);
			try (ResultSet rs = prstmt.executeQuery()) {
				if(rs.next()) {
					System.out.println(rs.getString(2));

					product.setId(rs.getLong(1));
					product.setName(rs.getString(2));
					product.setPrice(rs.getDouble(3));
					product.setQuantity(rs.getInt(4));

					return product;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public model.ProductList read() throws SQLException {
		try(Connection con = DriverManager.getConnection(
				"jdbc:postgresql://java.is.uni-due.de/ws1011",
				"ws1011",
				"ftpw10"
		); PreparedStatement prstmt = con.prepareStatement(
				"SELECT id,name,price,quantity FROM products order by id desc limit 100"
				)) {

			productList = new model.ProductList();
			prstmt.setMaxRows(maxResults);
			System.out.println(prstmt);
			try (ResultSet rs = prstmt.executeQuery()) {

				while(rs.next()) {
					Product product = new Product();
					product.setId(rs.getLong(1));
					product.setName(rs.getString(2));
					product.setPrice(rs.getDouble(3));
					product.setQuantity(rs.getInt(4));

					productList.add(product);
					//System.out.println(product.getName());
				}
				return productList;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
