package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.zkoss.zk.ui.util.Clients;

public class MyConnection {
	private static Connection con = null;
	
	public MyConnection() {
		
	}
	
	public static Connection getMyConnection() throws SQLException {
		if(con == null || con.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root123");
				System.out.println("connection establish fghj");
				//Clients.showNotification("Established connection. ", true);

			} catch (ClassNotFoundException e) {
				Clients.showNotification("Failed to established connection. " + e.getMessage(), true);
			}
		}
		return con;
	}
}
