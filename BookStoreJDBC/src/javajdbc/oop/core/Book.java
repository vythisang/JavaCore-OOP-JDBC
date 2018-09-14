package javajdbc.oop.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javajdbc.oop.dao.AuthorDao;
import javajdbc.oop.dao.CategoryDao;

public class Book {

	private long id;
	private String name;
	private double price;
	private long soldNumber;
	private ArrayList<Author> author;
	private Category category;
	private static AuthorDao authorDao = new AuthorDao();
	private static CategoryDao categoryDao = new CategoryDao();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getSoldNumber() {
		return soldNumber;
	}
	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}
	public ArrayList<Author> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Book(long id, String name, double price, long soldNumber, ArrayList<Author> author, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.soldNumber = soldNumber;
		this.author = author;
		this.category = category;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(long id, String name, double price, long soldNumber,Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.soldNumber = soldNumber;
		this.category = category;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", soldNumber=" + soldNumber + ", author="
				+ author + ", category=" + category + "]";
	}
	
	public Book(ResultSet resultSet)throws SQLException{
		this.setId(resultSet.getLong("id"));
		this.setName(resultSet.getString("name"));
		this.setPrice(resultSet.getDouble("price"));
		this.setSoldNumber(resultSet.getLong("sold_number"));
		this.setCategory(categoryDao.findCategoryOfBook(resultSet.getLong("category_id")));
		ArrayList<Author> authorList = authorDao.findAuthorOfBook(resultSet.getLong("id"));
		this.setAuthor(authorList);
	}
	
	
}
