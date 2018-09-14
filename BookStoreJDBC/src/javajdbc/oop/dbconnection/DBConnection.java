package javajdbc.oop.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private Connection conn;
	public static DBConnection db;

	private DBConnection() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "book_store";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "admin1234";
		try {
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url + dbName, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static synchronized DBConnection getDbCon(){
		if(db==null){
			db = new DBConnection();
		}
		return db;
	}
	
	public Connection getConn(){
		return conn;
	}

}
