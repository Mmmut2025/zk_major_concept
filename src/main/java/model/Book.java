package model;

public class Book {
	private int book_id;
	private String book_title;
	private String book_author;
	private String description;
	private double price;
	private String preview;
	
	
	public Book(int book_id, String book_title, String book_author, String description, double price,String preview) {
		super();
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.description = description;
		this.price = price;
		this.preview=preview;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the book_id
	 */
	public int getBook_id() {
		return book_id;
	}
	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
	/**
	 * @return the book_title
	 */
	public String getBook_title() {
		return book_title;
	}
	/**
	 * @param book_title the book_title to set
	 */
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	/**
	 * @return the book_author
	 */
	public String getBook_author() {
		return book_author;
	}
	/**
	 * @param book_author the book_author to set
	 */
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	/**
	 * @return the preview
	 */
	public String getPreview() {
		return preview;
	}
	/**
	 * @param preview the preview to set
	 */
	public void setPreview(String preview) {
		this.preview = preview;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_title=" + book_title + ", book_author=" + book_author
				+ ", description=" + description + ", price=" + price + ", preview=" + preview + "]";
	}
}
