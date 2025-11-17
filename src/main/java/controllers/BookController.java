package controllers;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.theme.Themes;

import dao.BookDao;
import model.Book;
import services.BookServiceImp;

public class BookController extends SelectorComposer<Window>{
	@Wire Label lb1 , lb2 , lb3;
	@Wire Listbox listbox;
	@Wire Textbox searchBox;
	@Wire Image img;
	@Wire Intbox txtId;
	@Wire Textbox txtTitle , txtAuthor , txtDesc;
	@Wire Doublebox txtPrice; 
	
	@Wire Combobox themeId;
	
	List<Book> books;
	ListModelList<Book> listModel;
	

	@Listen("onSelect=#themeId")
	public void chageTheme() {
		Themes.setTheme(Executions.getCurrent(), themeId.getSelectedItem().getValue());
		Executions.sendRedirect("");
	}
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		
		books = new BookServiceImp().findAll();
		listModel = new ListModelList<Book>(books);
		listbox.setModel(listModel);
	}
	
	@Listen("onClick=#searchBtn")
	public void searchBook() {
		List<Book> searchedBook = new BookServiceImp().search(searchBox.getValue());
		
		listModel = new ListModelList<Book>(searchedBook);
		listbox.setModel(listModel);
	}
	
	@Listen("onSelect=#listbox")
	public void getSelectedData() {
		Listitem selectedItem = listbox.getSelectedItem();
		Book book = (Book)selectedItem.getValue();
		System.out.println(book);
		lb1.setValue(book.getBook_title());
		lb2.setValue(book.getDescription());
		lb3.setValue("₹"+ book.getPrice()+"");
		img.setSrc(book.getPreview());
	}
	
	@Listen("onClick=#save")
	public void saveData() {
		Book book = new Book();
		book.setBook_id(txtId.getValue());
		book.setBook_title(txtTitle.getText());
		book.setPrice(txtPrice.getValue());
		book.setBook_author(txtAuthor.getText());
		book.setDescription(txtDesc.getText());
		
		BookDao bd = new BookDao();
		bd.saveDataIntoDB(book);
		
		listModel.add(book);
	}
}
