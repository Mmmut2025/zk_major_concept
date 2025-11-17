package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.zkoss.zk.ui.util.Clients;

import model.Book;

public class BookDao {
	public void saveDataIntoDB(Book book) {
		
		String query = "insert into Book (book_id,book_title,book_author,description,price) values(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = MyConnection.getMyConnection().prepareStatement(query);
			ps.setInt(1, book.getBook_id());
			ps.setString(2, book.getBook_title());
			ps.setString(3, book.getBook_author());
			ps.setString(4, book.getDescription());
			ps.setDouble(5, book.getPrice());
			
			int rows = ps.executeUpdate();
			if(rows>0) {
				Clients.showNotification("Data Added successfully", true);
				System.out.println("data added successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error come in book dao class saveDataIntoBook method" + e.getMessage());
			Clients.showNotification("Error come in saveDataIntoDb"+e.getMessage(), true);
		}	
	}
}
