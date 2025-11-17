package services;

import java.util.*;
import model.Book;

public interface BookService {
	public List<Book> findAll();
	public List<Book> search(String keyword);
}
