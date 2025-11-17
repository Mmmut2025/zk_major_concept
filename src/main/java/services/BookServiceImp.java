package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.util.Clients;

import model.Book;
import dao.MyConnection;

public class BookServiceImp implements BookService{
	
	List<Book> bookList = new ArrayList<>();
	public BookServiceImp() {
		
		try {
			System.out.println("ab operation karo");
			Statement st = MyConnection.getMyConnection().createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM Book");
			
			while(rs.next()) {
				bookList.add(new Book(rs.getInt("book_id"),rs.getString("book_title"),rs.getString("book_author"),rs.getString("description"),rs.getDouble("price"),rs.getString("image")));
			}
			
			System.out.println(bookList);
			
		} catch (SQLException e1) {
			Clients.showNotification("Inside the book service imp "+e1.getMessage(), true);
			System.out.println("something issume in book service implementation"+e1.getMessage());
			e1.printStackTrace();
		}
	}

	@Override
	public List<Book> findAll() {
		return bookList;
	}

	@Override
	public List<Book> search(String keyword) {
		List<Book> filteredBook = new ArrayList<>();
		if(keyword==null|| keyword.equals("")) {
			filteredBook = bookList;
		}
		else {
			List<Book> books = findAll();
			for(Book book:books) {
				if(book.getBook_title().contains(keyword)){
					filteredBook.add(book);
				}
			}
		}
		return filteredBook;
	}
	
	public static void main(String[] args) {
		BookServiceImp bs = new BookServiceImp();
		System.out.println(bs.search("Rich"));
		
	}
}
