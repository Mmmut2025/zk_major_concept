package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.zkoss.zk.ui.util.Clients;



public class UserDao {
	public boolean submitData(int id , String name , String email, String pass) {
		MyConnection conn =new MyConnection();
		String query = "insert into emp values(?,?,?,?)";
		
		try {
			PreparedStatement st = conn.getMyConnection().prepareStatement(query);
			st.setInt(1, id);
			st.setString(2, name);
			st.setString(3, email);
			st.setString(4, pass);

			int row = st.executeUpdate();
			if(row>0) {
				return true;
			}
		} catch (SQLException e) {
			Clients.showNotification("User Dao Error" + e.getMessage(), true);
			e.printStackTrace();
		}
		return false;
	}
}
