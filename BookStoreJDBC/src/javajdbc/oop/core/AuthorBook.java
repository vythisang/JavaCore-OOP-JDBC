package javajdbc.oop.core;

public class AuthorBook {

	private Author author;
	private Book book;
	private double revenueShare;
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getRevenueShare() {
		return revenueShare;
	}
	public void setRevenueShare(double revenueShare) {
		this.revenueShare = revenueShare;
		
	}
	public AuthorBook(Author author, Book book, double revenueShare) {
		super();
		this.author = author;
		this.book = book;
		this.revenueShare = revenueShare;
	}
	public AuthorBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthorBook [author=" + author + ", book=" + book + ", revenueShare=" + revenueShare + "]";
	}
	
	
}
